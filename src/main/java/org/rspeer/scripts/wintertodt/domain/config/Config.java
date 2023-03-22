package org.rspeer.scripts.wintertodt.domain.config;

import com.google.inject.Singleton;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.script.meta.ScriptConfig;
import org.rspeer.scripts.wintertodt.data.*;

@Singleton
public class Config {

  private boolean fletch;
  private boolean openCrates;

  private Gang gang;
  private GameWorld world;

  private int foodId;
  private int foodAmount;
  private int minimumFoodAmount;

  /**
   * Initialize with default values
   */
  public Config() {
    initialize(new ConfigBuilder());
  }

  /**
   * ScriptConfigEvent calls this if used
   */
  public void initialize(ScriptConfig config) {
    fletch = config.getBoolean(ConfigKey.FLETCH);
    openCrates = config.getBoolean(ConfigKey.OPEN_CRATES);
    gang = config.get(ConfigKey.GANG);
    world = config.get(ConfigKey.WORLD);
    foodId = config.getInteger(ConfigKey.FOOD_ID);
    foodAmount = config.getInteger(ConfigKey.FOOD_AMOUNT);
    minimumFoodAmount = config.getInteger(ConfigKey.MIN_FOOD_AMOUNT);
  }

  /**
   * Initialize with a provided builder. Currently only used for the default values,
   * but in the future I will use it for quickstart args
   */
  public void initialize(ConfigBuilder builder) {
    initialize(builder.build());
  }

  public boolean isFletch() {
    return fletch;
  }

  public boolean isOpenCrates() {
    return openCrates;
  }

  public Gang getGang() {
    return gang;
  }

  public GameWorld getWorld() {
    return world;
  }

  public int getFoodId() {
    return foodId;
  }

  public int getFoodAmount() {
    return foodAmount;
  }

  public int getMinimumFoodAmount() {
    return minimumFoodAmount;
  }

  public boolean isReady() {
    for (WintertodtItem item : WintertodtItem.values()) {
      if (!item.isPresent(this)) {
        return false;
      }
    }

    return Inventory.backpack().getCount(Constant.FOOD) >= minimumFoodAmount;
  }
}
