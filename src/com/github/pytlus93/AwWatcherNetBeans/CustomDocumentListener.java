/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.pytlus93.AwWatcherNetBeans;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import org.netbeans.modules.parsing.api.Source;
import org.openide.filesystems.FileObject;
import org.threeten.bp.OffsetDateTime;

/**
 *
 * @author alanhamlett
 */
public class CustomDocumentListener implements DocumentListener {

   private final Document document;

   public CustomDocumentListener(Document d) {
      this.document = d;
   }

   @Override
   public void insertUpdate(DocumentEvent e) {
      this.handleChange();
   }

   @Override
   public void removeUpdate(DocumentEvent e) {
      this.handleChange();
   }

   @Override
   public void changedUpdate(DocumentEvent e) {
      this.handleChange();
   }

   public void update() {
      if (AwWatcherNetBeans.documentListener != null) {
         AwWatcherNetBeans.documentListener.remove();
      }
      AwWatcherNetBeans.documentListener = this;
   }

   public void remove() {
      this.document.removeDocumentListener(this);
   }

   public void handleChange() {
      final FileObject file = this.getFile(document);
      if (file != null) {
         final Project currentProject = this.getProject(file);
         final OffsetDateTime currentTime = OffsetDateTime.now();
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               if ((!file.getPath().equals(AwWatcherNetBeans.lastFile) || AwWatcherNetBeans.enoughTimePassed(currentTime))) {
                  AwWatcherNetBeans.sendHeartbeat(currentTime, file, currentProject);
               }
            }
         });
      }
   }

   private FileObject getFile(Document document) {
      if (document == null) {
         return null;
      }
      Source source = Source.create(document);
      if (source == null) {
         return null;
      }
      FileObject fileObject = source.getFileObject();
      if (fileObject == null) {
         return null;
      }
      return fileObject;
   }

   private Project getProject(FileObject file) {
      if (file == null) {
         return null;
      }
      return FileOwnerQuery.getOwner(file);
   }

}
