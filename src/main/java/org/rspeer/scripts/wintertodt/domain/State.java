package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.rspeer.game.adapter.component.inventory.Inventory;
import org.rspeer.scripts.wintertodt.api.Items;
import org.rspeer.scripts.wintertodt.data.Constant;
import org.rspeer.scripts.wintertodt.data.Gang;
import org.rspeer.scripts.wintertodt.domain.config.Config;

import java.util.EnumMap;
import java.util.Map;

@Singleton
public class State {

  private final Boss boss;
  private final Timers timers;
  private final Map<Gang, Pyromancer> pyromancers;

  private int lastAnimation;
  private boolean chop;
  private Gang gang;
  private boolean relog;

  @Inject
  public State(Boss boss, Timers timers, Config config) {
    this.boss = boss;
    this.timers = timers;
    this.gang = config.getGang();
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

  void message(String text) {
    if (text.contains("you will be logged out in approximately 5 minutes")) {
      relog = true;
    }
  }


  void update(Pyromancer source, Gang original) {
    if (source.getGang() == original) {
      gang = source.isDead() ? alternate(original) : original;
    }
  }

  private Gang alternate(Gang src) {
    //TODO verify that the alternate gang is not also a dead pyromancer
    //very unlikely but it can happen? can easily just pass in the pyromancer map and work with it
    return switch (src) {
      case WEST, NORTH_EAST -> Gang.EAST;
      case EAST, NORTH_WEST -> Gang.WEST;
    };
  }

  public boolean shouldChop() {
    return chop;
  }

  public int getLastAnimation() {
    return lastAnimation;
  }

  public Gang getGang() {
    return gang;
  }

  public boolean isRelog() {
    return relog;
  }

  public void setRelog(boolean relog) {
    this.relog = relog;
  }
}
