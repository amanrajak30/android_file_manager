

package com.amaze.filemanager.adapters.glide.apkimage;

import com.amaze.filemanager.R;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * @author Emmanuel Messulam <emmanuelbendavid@gmail.com> on 10/12/2017, at 16:12.
 */
public class ApkImageDataFetcher implements DataFetcher<Drawable> {

  private Context context;
  private String model;

  public ApkImageDataFetcher(Context context, String model) {
    this.context = context;
    this.model = model;
  }

  @Override
  public void loadData(Priority priority, DataCallback<? super Drawable> callback) {
    PackageInfo pi = context.getPackageManager().getPackageArchiveInfo(model, 0);
    Drawable apkIcon;
    if (pi != null) {
      pi.applicationInfo.sourceDir = model;
      pi.applicationInfo.publicSourceDir = model;
      apkIcon = pi.applicationInfo.loadIcon(context.getPackageManager());
    } else {
      apkIcon = ContextCompat.getDrawable(context, R.drawable.ic_android_white_24dp);
    }
    callback.onDataReady(apkIcon);
  }

  @Override
  public void cleanup() {
    // Intentionally empty only because we're not opening an InputStream or another I/O resource!
  }

  @Override
  public void cancel() {
    // No cancelation procedure
  }

  @NonNull
  @Override
  public Class<Drawable> getDataClass() {
    return Drawable.class;
  }

  @NonNull
  @Override
  public DataSource getDataSource() {
    return DataSource.LOCAL;
  }
}
