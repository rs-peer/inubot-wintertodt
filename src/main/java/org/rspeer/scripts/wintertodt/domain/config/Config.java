package org.rspeer.scripts.wintertodt.domain.config;

import com.google.inject.Singleton;
import org.rspeer.commons.logging.Log;
import org.rspeer.game.component.Inventories;
import org.rspeer.game.script.model.ConfigModel;
import org.rspeer.game.script.model.ui.schema.checkbox.CheckBoxComponent;
import org.rspeer.game.script.model.ui.schema.selector.SelectorComponent;
import org.rspeer.game.script.model.ui.schema.text.TextFieldComponent;
import org.rspeer.game.script.model.ui.schema.text.TextInputType;
import org.rspeer.scripts.wintertodt.api.Items;
import org.rspeer.scripts.wintertodt.data.*;

@Singleton
public class Config extends ConfigModel {

  @CheckBoxComponent(name = "Fletch", key = "fletch")
  private boolean fletch;

  @CheckBoxComponent(name = "Open crates", key = "open_crates")
  private boolean openCrates;

  @SelectorComponent(name = "Side", key = "preferred_side", type = Gang.class)
  private Gang gang;

  @SelectorComponent(name = "World", key = "preferred_world", type = GameWorld.class)
  private GameWorld world;

  @TextFieldComponent(name = "Food ID", key = "food_id", inputType = TextInputType.NUMERIC)
  private int foodId;

  @TextFieldComponent(name = "Food withdraw amount", key = "food_amount", inputType = TextInputType.NUMERIC)
  private int foodAmount;

  @TextFieldComponent(name = "Minimum food for kill", key = "min_food_per_kill", inputType = TextInputType.NUMERIC)
  private int minimumFoodAmount;

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
        Log.info("Missing " + item);
        return false;
      }
    }

    return !Inventories.backpack().contains(iq -> iq.nameContains(" crate").results()) && Inventories.backpack().getCount(Items.FOOD) >= getMinimumFoodAmount();
  }
}
