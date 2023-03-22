package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Singleton;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.game.script.meta.ScriptConfig;
import org.rspeer.scripts.wintertodt.data.item.WintertodtItem;
import org.rspeer.scripts.wintertodt.data.locale.GameWorld;
import org.rspeer.scripts.wintertodt.data.position.Gang;

@Singleton
public class Config {

  private boolean fletch = true;
  private boolean openCrates = true;

  private Gang gang = Gang.WEST;
  private GameWorld world = GameWorld.UK;

  private int foodId = 7946;
  private int foodAmount = 5;
  private int minimumFoodAmount = 2;

  public void initialize(ScriptConfig config) {
    fletch = config.getBoolean("Fletch");
    openCrates = config.getBoolean("Open crates");
    gang = config.get("Side");
    world = config.get("World");
    foodId = config.getInteger("Food id");
    foodAmount = config.getInteger("Food amount");
    minimumFoodAmount = config.getInteger("Minimum food amount");
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

    return Inventory.backpack().getCount(iq -> iq.actions("Eat", "Drink").results()) >= minimumFoodAmount;
  }
}
