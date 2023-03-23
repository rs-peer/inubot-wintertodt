package org.rspeer.scripts.wintertodt.domain.config;

import org.rspeer.scripts.wintertodt.data.GameWorld;
import org.rspeer.scripts.wintertodt.data.Gang;

public interface ConfigDefaults {

  boolean FLETCH = false;

  boolean OPEN_CRATES = true;

  Gang GANG = Gang.WEST;

  GameWorld WORLD = GameWorld.UK;

  int FOOD_ID = 385;

  int FOOD_AMOUNT = 5;

  int MIN_FOOD_AMOUNT = 2;
}
