

package com.cloudrail.si.interfaces;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import com.cloudrail.si.types.CloudMetaData;
import com.cloudrail.si.types.SpaceAllocation;

public class CloudStorage {

  public List<CloudMetaData> getChildren(String str) {
    return Collections.emptyList();
  }

  public void logout() {}

  public void delete(String path) {}

  public void move(String path1, String path2) {}

  public void copy(String path1, String path2) {}

  public boolean exists(String path) {
    return false;
  }

  public void loadAsString(String str) {}

  public SpaceAllocation getAllocation() {
    return new SpaceAllocation();
  }

  public void login() {}

  public String saveAsString() {
    return "";
  }

  public String getUserLogin() {
    return "";
  }

  public void useAdvancedAuthentication() {}

  public void createFolder(String extSyncFolder) {}

  public InputStream download(String path) {
    return null;
  }

  public InputStream getThumbnail(String path) {
    return null;
  }

  public void upload(String extSyncFile, InputStream outStream, long length, boolean b) {}

  public CloudMetaData getMetadata(String str) {
    return null;
  }

  public String createShareLink(String str) {
    return "";
  }
}
