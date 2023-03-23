package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.rspeer.commons.logging.Log;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.scripts.wintertodt.api.Items;
import org.rspeer.scripts.wintertodt.data.Constant;
import org.rspeer.scripts.wintertodt.data.Gang;

import java.util.EnumMap;
import java.util.Map;

@Singleton
public class State {

  private final Boss boss;
  private final Timers timers;
  private final Map<Gang, Pyromancer> pyromancers;

  private int lastAnimation;
  private boolean chop;

  @Inject
  public State(Boss boss, Timers timers) {
    this.boss = boss;
    this.timers = timers;
    this.pyromancers = initializePyromancers();
  }

  private Map<Gang, Pyromancer> initializePyromancers() {
    if (this.pyromancers != null) {
      throw new IllegalStateException();
    }

    Map<Gang, Pyromancer> pyromancers = new EnumMap<>(Gang.class);
    for (Gang gang : Gang.values()) {
      pyromancers.put(gang, new Pyromancer(timers, gang));
    }

    return pyromancers;
  }

  void tick() {
    Inventory inv = Inventory.backpack();
    if (inv.isFull()) {
      chop = false;
      return;
    }

    //Time is a limiting factor for reaching our (next) points threshold, so we force burn
    int remaining = Constant.POINTS_THRESHOLD - (boss.getPoints() % Constant.POINTS_THRESHOLD);
    if (boss.getEnergy() < 25 && Items.getStoredPoints(inv) >= remaining) {
      Log.info("Force burning due to time limiting factor");
      chop = false;
      return;
    }

    int count = inv.getCount(Items.BURNABLE);
    if (count == 0) {
      chop = true;
      return;
    }

    //Just force burn
    if (boss.getEnergy() < 10 && count >= 3) {
      Log.info("Boss dying, forcing burn cycle");
      chop = false;
    }
  }

  void animate(int lastAnimation) {
    if (lastAnimation != -1) {
      this.lastAnimation = lastAnimation;
    }
  }

  void broadcast(Object[] args) {
    for (Pyromancer pyromancer : pyromancers.values()) {
      int state = (int) args[pyromancer.getGang().ordinal() + 3];
      pyromancer.update(state);
    }
  }

  public boolean shouldChop() {
    return chop;
  }

  public int getLastAnimation() {
    return lastAnimation;
  }

  public Pyromancer getPyromancer(Gang gang) {
    return pyromancers.get(gang);
  }
}
