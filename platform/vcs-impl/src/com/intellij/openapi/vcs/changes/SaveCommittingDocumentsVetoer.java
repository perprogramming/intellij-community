/*
 * Copyright 2000-2011 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.openapi.vcs.changes;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentSynchronizationVetoer;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.ui.CommitHelper;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

/**
 * @author yole
 */
public class SaveCommittingDocumentsVetoer implements FileDocumentSynchronizationVetoer {
  private final VetoSavingCommittingDocumentsAdapter myAdapter;

  public SaveCommittingDocumentsVetoer(VetoSavingCommittingDocumentsAdapter adapter) {
    myAdapter = adapter;
  }

  public boolean maySaveDocument(@NotNull Document document) {
    final Object beingCommitted = document.getUserData(CommitHelper.DOCUMENT_BEING_COMMITTED_KEY);
    if (beingCommitted == VetoSavingCommittingDocumentsAdapter.SAVE_DENIED) {
      return false;
    }
    if (beingCommitted instanceof Project) {
      boolean allowSave = myAdapter.showAllowSaveDialog(Collections.singletonMap(document, (Project)beingCommitted));
      if (!allowSave) {
        return false;
      }
    }
    return true;
  }

  public boolean mayReloadFileContent(VirtualFile file, @NotNull Document document)  {
    return true;
  }
}
