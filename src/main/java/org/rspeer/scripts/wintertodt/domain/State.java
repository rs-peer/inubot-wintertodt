package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.rspeer.commons.logging.Log;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.scripts.wintertodt.data.Constant;

@Singleton
public class State {

  private final Boss boss;

  private boolean chop;
  private int lastAnimation;

  @Inject
  public State(Boss boss) {
    this.boss = boss;
  }

  void tick() {
    Inventory inv = Inventory.backpack();
    if (inv.isFull()) {
      chop = false;
      return;
    }

    //Time is a limiting factor for reaching our (next) points threshold, so we force burn
    int remaining = Constant.POINTS_THRESHOLD - (boss.getPoints() % Constant.POINTS_THRESHOLD);
    if (boss.getEnergy() < 25 && getStoredPoints(inv) >= remaining) {
      Log.info("Force burning due to time limiting factor");
      chop = false;
      return;
    }

    if (!inv.contains(Constant.BURNABLE)) {
      chop = true;
      return;
    }

    //Just force burn
    if (boss.getEnergy() < 10) {
      Log.info("Boss dying, forcing burn cycle");
      chop = false;
    }
  }

  private int getStoredPoints(Inventory inv) {
    int roots = inv.getCount(iq -> iq.names(Constant.ROOT).results());
    int kindlings = inv.getCount(iq -> iq.names(Constant.KINDLING).results());
    return (roots * 10) + (kindlings * 25);
  }

  public boolean shouldChop() {
    return chop;
  }

  public int getLastAnimation() {
    return lastAnimation;
  }

  public void setLastAnimation(int lastAnimation) {
    if (lastAnimation != -1) {
      this.lastAnimation = lastAnimation;
    }
  }
}
