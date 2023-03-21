package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Singleton;
import org.rspeer.game.script.meta.ScriptConfig;
import org.rspeer.scripts.wintertodt.data.position.Gang;

@Singleton
public class Config {

  private boolean fletch;
  private Gang gang;

  public void initialize(ScriptConfig config) {
    fletch = config.getBoolean("Fletch");
    gang = config.get("Side");
  }

  public boolean isFletch() {
    return fletch;
  }

  public Gang getGang() {
    return gang;
  }
}
