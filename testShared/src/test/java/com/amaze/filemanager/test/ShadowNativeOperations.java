

package com.amaze.filemanager.test;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

import com.amaze.filemanager.fileoperations.filesystem.root.NativeOperations;

import androidx.annotation.Nullable;

@Implements(NativeOperations.class)
public class ShadowNativeOperations {

  @Implementation
  public static boolean isDirectory(@Nullable String path) {
    return path != null && path.startsWith("d");
  }
}
