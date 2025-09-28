
package com.amaze.filemanager.adapters.glide.apkimage;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * @author Emmanuel Messulam <emmanuelbendavid@gmail.com> on 10/12/2017, at 16:21.
 */
public class ApkImageModelLoaderFactory implements ModelLoaderFactory<String, Drawable> {

  private Context context;

  public ApkImageModelLoaderFactory(Context context) {
    this.context = context;
  }

  @Override
  public ModelLoader<String, Drawable> build(MultiModelLoaderFactory multiFactory) {
    return new ApkImageModelLoader(context);
  }

  @Override
  public void teardown() {}
}
