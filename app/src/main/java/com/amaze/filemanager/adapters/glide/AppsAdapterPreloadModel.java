
package com.amaze.filemanager.adapters.glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amaze.filemanager.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * @author Emmanuel Messulam <emmanuelbendavid@gmail.com> on 10/12/2017, at 15:38.
 */
public class AppsAdapterPreloadModel implements ListPreloader.PreloadModelProvider<String> {

  private final Logger LOG = LoggerFactory.getLogger(AppsAdapterPreloadModel.class);

  private Context mContext;
  private RequestBuilder<Drawable> request;
  private List<String> items;
  private boolean isBottomSheet;

  public AppsAdapterPreloadModel(Fragment f, boolean isBottomSheet) {
    request = Glide.with(f).asDrawable().fitCenter();
    this.mContext = f.requireContext();
    this.isBottomSheet = isBottomSheet;
  }

  public void addItem(String item) {
    if (items == null) {
      items = new ArrayList<>();
    }
    items.add(item);
  }

  @NonNull
  @Override
  public List<String> getPreloadItems(int position) {
    if (items == null) return Collections.emptyList();
    else return Collections.singletonList(items.get(position));
  }

  @Nullable
  @Override
  public RequestBuilder getPreloadRequestBuilder(String item) {
    if (isBottomSheet) {
      return request.clone().load(getApplicationIconFromPackageName(item));
    } else {
      return request.clone().load(item);
    }
  }

  public void loadApkImage(String item, AppCompatImageView v) {
    if (isBottomSheet) {
      request.load(getApplicationIconFromPackageName(item)).into(v);
    } else {
      request.load(item).into(v);
    }
  }

  private Drawable getApplicationIconFromPackageName(String packageName) {
    try {
      return mContext.getPackageManager().getApplicationIcon(packageName);
    } catch (PackageManager.NameNotFoundException e) {
      LOG.warn(getClass().getSimpleName(), e);
      return ContextCompat.getDrawable(mContext, R.drawable.ic_broken_image_white_24dp);
    }
  }
}
