/*
 * Copyright 2000-2010 JetBrains s.r.o.
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
package com.intellij.psi.search.scope.packageSet;

import org.jetbrains.annotations.NotNull;

/**
 * @author Konstantin Bulenkov
 */
public abstract class AbstractPackageSet extends PackageSetBase {
  private final String myText;
  private final int myPriority;

  public AbstractPackageSet(@NotNull String text) {
    this(text, 1);
  }

  public AbstractPackageSet(@NotNull String text, int priority) {
    myText = text;
    myPriority = priority;
  }

  @NotNull
  public AbstractPackageSet createCopy() {
    return this;
  }

  public int getNodePriority() {
    return myPriority;
  }

  @NotNull
  @Override
  public String getText() {
    return myText;
  }
}
