package org.rspeer.scripts.wintertodt.domain.config;

import org.rspeer.game.script.meta.ScriptConfig;
import org.rspeer.scripts.wintertodt.data.GameWorld;
import org.rspeer.scripts.wintertodt.data.Gang;

public class ConfigBuilder {

  private final ScriptConfig config;

  public ConfigBuilder() {
    this.config = new ScriptConfig();
    initDefaults();
  }

  private void initDefaults() {
    config.put(ConfigKey.FLETCH, ConfigDefaults.FLETCH);
    config.put(ConfigKey.OPEN_CRATES, ConfigDefaults.OPEN_CRATES);
    config.put(ConfigKey.GANG, ConfigDefaults.GANG);
    config.put(ConfigKey.WORLD, ConfigDefaults.WORLD);
    config.put(ConfigKey.FOOD_ID, ConfigDefaults.FOOD_ID);
    config.put(ConfigKey.FOOD_AMOUNT, ConfigDefaults.FOOD_AMOUNT);
    config.put(ConfigKey.MIN_FOOD_AMOUNT, ConfigDefaults.MIN_FOOD_AMOUNT);
  }

  public ConfigBuilder fletch(boolean fletch) {
    config.put(ConfigKey.FLETCH, fletch);
    return this;
  }

  public ConfigBuilder crates(boolean crates) {
    config.put(ConfigKey.OPEN_CRATES, crates);
    return this;
  }

  public ConfigBuilder gang(Gang gang) {
    config.put(ConfigKey.GANG, gang);
    return this;
  }

  public ConfigBuilder world(GameWorld world) {
    config.put(ConfigKey.WORLD, world);
    return this;
  }

  public ConfigBuilder food(int id) {
    config.put(ConfigKey.FOOD_ID, id);
    return this;
  }

  public ConfigBuilder foodAmount(int amount) {
    config.put(ConfigKey.FOOD_AMOUNT, amount);
    return this;
  }

  public ConfigBuilder minFoodAmount(int amount) {
    config.put(ConfigKey.MIN_FOOD_AMOUNT, amount);
    return this;
  }

  public ScriptConfig build() {
    return config;
  }
}
