/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.pytlus93.AwWatcherNetBeans;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.CreateBucket;
import io.swagger.client.model.Event;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.openide.filesystems.FileObject;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;
import org.openide.windows.OnShowing;
import org.threeten.bp.OffsetDateTime;

/**
 *
 * @author FiVrCz
 */
@OnShowing
public class AwWatcherNetBeans extends ModuleInstall implements Runnable {

   private static final String CLIENT_ID = "aw-watcher-netbeans";
   private static final String HOSTNAME = getComputerName();

   private static final Logger log = Logger.getLogger("AwWatcherNetBeans");
   private static DefaultApi awAPI;
   private static String bucketId;

   private static int frequency;
   private static int pulseTime;

   public static String lastFile = null;
   private static OffsetDateTime actionStart = null;
   private static OffsetDateTime lastTime = null;

   public static CustomDocumentListener documentListener;

   @Override
   public void run() {
      awAPI = new DefaultApi();
      applySettings();

      PropertyChangeListener pcl = new PropertyChangeListener() {
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            JTextComponent jtc = EditorRegistry.lastFocusedComponent();
            if (jtc != null) {
               Document d = jtc.getDocument();
               if (d != null) {
                  CustomDocumentListener listener = new CustomDocumentListener(d);
                  d.addDocumentListener(listener);
                  listener.update();
                  listener.handleChange();
               }
            }
         }
      };

      EditorRegistry.addPropertyChangeListener(pcl);

      //runCycleFileChecking();
   }

   public static void applySettings() {
      Preferences prefs = NbPreferences.forModule(AwWatcherNetBeans.class);

      if (prefs.getBoolean("Debug", false)) {
         awAPI.getApiClient().setBasePath("http://localhost:5666/api");
      } else {
         awAPI.getApiClient().setBasePath("http://localhost:5600/api");
      }

      frequency = prefs.getInt("Frequency", 5);
      pulseTime = prefs.getInt("PulseTime", 20);

      custExts.clear();
      for (String line : prefs.get("CustomFileTypes", "").trim().split("\\r?\\n")) {
         String[] parts = line.trim().split("=", -1);
         if (parts.length == 2) {
            custExts.put(parts[0], parts[1]);
         }
      }

      bucketId = CLIENT_ID + "_" + getComputerName();
      for (int i = 0; i < 5; i++) {
         if (createBucket()) {
            break;
         }
         try {
            Thread.sleep(1000);
         } catch (InterruptedException ex) {
            log.warning(ex.getLocalizedMessage());
         }
      }
   }

//   @SuppressWarnings("SleepWhileInLoop")
//   private void runCycleFileChecking() {
//      while (true) {
//         TopComponent activeTC = TopComponent.getRegistry().getActivated();
//         if (activeTC == null) {
//            continue;
//         }
//         DataObject dataLookup = activeTC.getLookup().lookup(DataObject.class);
//         if (dataLookup == null) {
//            continue;
//         }
//         final FileObject file = dataLookup.getPrimaryFile();
//         if (file == null) {
//            continue;
//         }
//         final Project project = FileOwnerQuery.getOwner(file);
//         sendHeartbeat(OffsetDateTime.now(), file, project, false);
//         try {
//            Thread.sleep(FREQUENCY * 1000);
//         } catch (InterruptedException ignored) {
//         }
//      }
//   }
   private static boolean createBucket() {
      try {
         awAPI.getBucketResource(bucketId);
         return true;
      } catch (ApiException ex) {
         switch (ex.getCode()) {
            case 404:
               CreateBucket cb = new CreateBucket();
               cb.setClient(CLIENT_ID);
               cb.setHostname(HOSTNAME);
               cb.setType("app.editor.activity");
                {
                  try {
                     awAPI.postBucketResource(bucketId, cb);
                     return true;
                  } catch (ApiException ex1) {
                     log.warning(ex1.getLocalizedMessage());
                  }
               }
               break;
            default:
               log.warning(ex.getLocalizedMessage());
         }
      }
      return false;
   }

   public static boolean enoughTimePassed(OffsetDateTime currentTime) {
      return AwWatcherNetBeans.lastTime.plusSeconds(frequency).isBefore(currentTime);
   }

   private static void sendHeartbeat(Event e) throws ApiException {
      awAPI.postHeartbeatResource(bucketId, e, Integer.toString(pulseTime));
   }

   private static String getComputerName() {
      Map<String, String> env = System.getenv();
      if (env.containsKey("COMPUTERNAME")) {
         return env.get("COMPUTERNAME");
      } else if (env.containsKey("HOSTNAME")) {
         return env.get("HOSTNAME");
      } else {
         return "Unknown Computer";
      }
   }

   public static void sendHeartbeat(final OffsetDateTime currentTime, final FileObject file, final Project currentProject) {
      sendHeartbeat(currentTime, file, currentProject, 0);
   }

   public static void sendHeartbeat(final OffsetDateTime currentTime, final FileObject file, final Project currentProject, final int tries) {
      Runnable r = new Runnable() {
         @Override
         public void run() {
            try {
               Event e = new Event();
               e.setTimestamp(currentTime);
               EditorData ed = new EditorData();
               ed.file = file == null ? null : file.getPath();
               ed.project = currentProject == null ? null : ProjectUtils.getInformation(currentProject).getDisplayName();
               ed.language = getLanguage(file);
               if (file == null ? AwWatcherNetBeans.lastFile != null : !file.getPath().equals(AwWatcherNetBeans.lastFile)) {
                  AwWatcherNetBeans.lastFile = file == null ? null : file.getPath();
                  AwWatcherNetBeans.actionStart = currentTime;
                  AwWatcherNetBeans.lastTime = currentTime;
               }
               e.setDuration(BigDecimal.ZERO);
               //e.setDuration(BigDecimal.valueOf(org.threeten.bp.Duration.between(lastTime, currentTime).getSeconds()));
               e.setData(ed);
               AwWatcherNetBeans.sendHeartbeat(e);
            } catch (ApiException e) {
               if (tries < 3) {
                  log.fine(e.toString());
                  try {
                     Thread.sleep(30);
                  } catch (InterruptedException e1) {
                     log.warning(e1.toString());
                  }
                  sendHeartbeat(currentTime, file, currentProject, tries + 1);
                  return;
               } else {
                  log.warning(e.toString());
                  return;
               }
            }
            lastTime = currentTime;
         }
      };
      new Thread(r).start();
   }

   private static String getLanguage(FileObject file) {
      if (file == null) {
         return null;
      }
      String fileName = file.getNameExt().toLowerCase();

      String result = findLanguage(fileName);

      if (result == null && fileName.endsWith(".bak")) {
         fileName = fileName.substring(0, fileName.length() - 4);
         result = findLanguage(fileName);
      }

      return result != null ? result : file.getExt();
   }

   private static String findLanguage(final String inFileName) {
      String fileName = inFileName;
      String result = custExts.getOrDefault(fileName, exts.get(fileName));
      while (result == null) {
         int dotPos = fileName.indexOf(".");
         if (dotPos == -1) {
            break;
         }
         fileName = fileName.substring(dotPos + 1);
         result = custExts.getOrDefault(fileName, exts.get(fileName));
      }
      return result;
   }

   private static final Map<String, String> exts = new HashMap<String, String>();
   private static final Map<String, String> custExts = new HashMap<String, String>();

   static {
      try {
         exts.clear();
         InputStream is = AwWatcherNetBeans.class.getResourceAsStream("/com/github/pytlus93/AwWatcherNetBeans/exts.ini");
         String text = new Scanner(is, "UTF-8").useDelimiter("\\A").next();
         for (String line : text.split("\\n")) {
            String[] parts = line.trim().split("=");
            exts.put(parts[0].trim(), parts[1].trim());
         }
      } catch (Exception ex) {
         Exceptions.printStackTrace(ex);
      }
   }
}
