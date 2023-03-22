package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Singleton;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.scripts.wintertodt.data.Constant;

@Singleton
public class State {

  private boolean chop;
  private int lastAnimation;

  void tick() {
    Inventory inv = Inventory.backpack();
    if (inv.isFull()) {
      chop = false;
    } else if (!inv.contains(iq -> iq.names(Constant.KINDLING, Constant.ROOT).results())) {
      chop = true;
    }
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
