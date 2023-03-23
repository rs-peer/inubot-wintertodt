package org.rspeer.scripts.wintertodt.domain.config;

import org.rspeer.scripts.wintertodt.data.GameWorld;
import org.rspeer.scripts.wintertodt.data.Gang;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface ConfigDefaults {

  boolean FLETCH = false;

  boolean OPEN_CRATES = true;

  Gang GANG = Gang.WEST;

  GameWorld WORLD = GameWorld.UK;

  int FOOD_ID = 385;

  int FOOD_AMOUNT = 5;

  int MIN_FOOD_AMOUNT = 3;

  static List<Object> values() {
    List<Object> values = new ArrayList<>();
    for (Field field : ConfigKey.class.getDeclaredFields()) {
      try {
        values.add(field.get(null));
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
    return values;
  }
}
