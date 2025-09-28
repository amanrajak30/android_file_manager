

package com.amaze.filemanager.test;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static android.os.Build.VERSION_CODES.P;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowSQLiteConnection;

import com.amaze.filemanager.database.UtilitiesDatabase;
import com.amaze.filemanager.database.UtilsHandler;
import com.amaze.filemanager.database.models.OperationData;
import com.amaze.filemanager.filesystem.ftp.NetCopyClientUtils;
import com.amaze.filemanager.shadows.ShadowMultiDex;
import com.amaze.filemanager.utils.PasswordUtil;

import android.os.Build;
import android.util.Base64;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(AndroidJUnit4.class)
@Config(
    shadows = {ShadowMultiDex.class, ShadowPasswordUtil.class},
    sdk = {LOLLIPOP, P, Build.VERSION_CODES.R})
public class ShadowPasswordUtilTest {

  @Before
  public void setUp() {
    RxJavaPlugins.reset();
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    RxAndroidPlugins.reset();
    RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
  }

  @After
  public void tearDown() {
    ShadowSQLiteConnection.reset();
  }

  @Test
  public void testEncryptDecrypt() throws GeneralSecurityException, IOException {
    String text = "test";
    String encrypted =
        PasswordUtil.INSTANCE.encryptPassword(
            ApplicationProvider.getApplicationContext(), text, Base64.DEFAULT);
    assertEquals(
        text,
        PasswordUtil.INSTANCE.decryptPassword(
            ApplicationProvider.getApplicationContext(), encrypted, Base64.DEFAULT));
  }

  @Test
  public void testWithUtilsHandler() {

    UtilitiesDatabase utilitiesDatabase =
        UtilitiesDatabase.initialize(ApplicationProvider.getApplicationContext());
    UtilsHandler utilsHandler =
        new UtilsHandler(ApplicationProvider.getApplicationContext(), utilitiesDatabase);

    String fingerprint = "00:11:22:33:44:55:66:77:88:99:aa:bb:cc:dd:ee:ff";
    String url = "ssh://test:test@127.0.0.1:22";

    utilsHandler.saveToDatabase(
        new OperationData(
            UtilsHandler.Operation.SFTP,
            NetCopyClientUtils.INSTANCE.encryptFtpPathAsNecessary(url),
            "Test",
            fingerprint,
            null,
            null));

    await()
        .atMost(10, TimeUnit.SECONDS)
        .until(
            () -> {
              assertEquals(
                  fingerprint,
                  utilsHandler.getRemoteHostKey(
                      NetCopyClientUtils.INSTANCE.encryptFtpPathAsNecessary(url)));
              utilitiesDatabase.close();
              return true;
            });
  }
}
