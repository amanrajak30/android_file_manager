

package com.amaze.filemanager.asynchronous.asynctasks;

import java.lang.ref.WeakReference;

import com.amaze.filemanager.database.CloudHandler;
import com.amaze.filemanager.ui.activities.MainActivity;

import android.database.Cursor;
import android.os.AsyncTask;

import androidx.annotation.NonNull;

public class CloudLoaderAsyncTask extends AsyncTask<Void, Void, Boolean> {

  private final WeakReference<MainActivity> mainActivity;

  public CloudLoaderAsyncTask(MainActivity mainActivity, CloudHandler unused1, Cursor unused2) {
    this.mainActivity = new WeakReference<>(mainActivity);
  }

  @Override
  @NonNull
  public Boolean doInBackground(Void... voids) {
    return false;
  }

  @Override
  protected void onCancelled() {
    super.onCancelled();
    final MainActivity mainActivity = this.mainActivity.get();
    if (mainActivity != null) {
      mainActivity
          .getSupportLoaderManager()
          .destroyLoader(MainActivity.REQUEST_CODE_CLOUD_LIST_KEY);
      mainActivity
          .getSupportLoaderManager()
          .destroyLoader(MainActivity.REQUEST_CODE_CLOUD_LIST_KEYS);
    }
  }

  @Override
  public void onPostExecute(@NonNull Boolean result) {
    if (result) {
      final MainActivity mainActivity = this.mainActivity.get();
      if (mainActivity != null) {
        mainActivity.getDrawer().refreshDrawer();
        mainActivity.invalidateFragmentAndBundle(null, true);
      }
    }
  }
}
