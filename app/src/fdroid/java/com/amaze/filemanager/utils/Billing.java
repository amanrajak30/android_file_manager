

package com.amaze.filemanager.utils;

import com.amaze.filemanager.ui.activities.superclasses.BasicActivity;

public class Billing {

  private static final String URL_AUTHOR_2_PAYPAL = "https://www.paypal.me/vishalnehra";

  public Billing(BasicActivity activity) {
    Utils.openURL(URL_AUTHOR_2_PAYPAL, activity);
  }

  public void destroyBillingInstance() {
    // do nothing
  }
}
