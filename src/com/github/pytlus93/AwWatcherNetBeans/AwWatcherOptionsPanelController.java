/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.pytlus93.AwWatcherNetBeans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

@OptionsPanelController.TopLevelRegistration(
        categoryName = "#OptionsCategory_Name_AwWatcherNetBeans",
        iconBase = "com/github/pytlus93/AwWatcherNetBeans/logo_32.png",
        keywords = "#OptionsCategory_Keywords_AwWatcherNetBeans",
        keywordsCategory = "AwWatcherNetBeans"
)
@org.openide.util.NbBundle.Messages({"OptionsCategory_Name_AwWatcherNetBeans=Activity Watcher", "OptionsCategory_Keywords_AwWatcherNetBeans=activity,watch,activitywatch,aw,watcher"})
public final class AwWatcherOptionsPanelController extends OptionsPanelController {

   private AwWatcherOptionsPanel panel;
   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
   private boolean changed;

   @Override
   public void update() {
      getPanel().load();
      changed = false;
   }

   @Override
   public void applyChanges() {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            getPanel().store();
            AwWatcherNetBeans.applySettings();
            changed = false;
         }
      });
   }

   @Override
   public void cancel() {
      //reset to default
      update();
   }

   @Override
   public boolean isValid() {
      return getPanel().valid();
   }

   @Override
   public boolean isChanged() {
      return changed;
   }

   @Override
   public HelpCtx getHelpCtx() {
      return null;
   }

   @Override
   public JComponent getComponent(Lookup masterLookup) {
      return getPanel();
   }

   @Override
   public void addPropertyChangeListener(PropertyChangeListener l) {
      pcs.addPropertyChangeListener(l);
   }

   @Override
   public void removePropertyChangeListener(PropertyChangeListener l) {
      pcs.removePropertyChangeListener(l);
   }

   private AwWatcherOptionsPanel getPanel() {
      if (panel == null) {
         panel = new AwWatcherOptionsPanel(this);
      }
      return panel;
   }

   void changed() {
      if (!changed) {
         changed = true;
         pcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
      }
      pcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
   }
}
