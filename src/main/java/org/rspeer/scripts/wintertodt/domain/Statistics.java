package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Singleton;

@Singleton
public class Statistics {

  private int kills;
  private int fails;

  public void message(String text) {
    if (text.contains("did not earn enough points")) {
      fails++;
    } else if (text.contains("your subdued wintertodt count")) {
      kills++;
    }
  }

  public int getKills() {
    return kills;
  }

  public int getFails() {
    return fails;
  }
}
