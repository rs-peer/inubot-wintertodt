package org.rspeer.scripts.wintertodt.domain.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public interface ConfigKey {

  String FLETCH = "Fletch";

  String OPEN_CRATES = "Open crates";

  String GANG = "Side";

  String WORLD = "World";

  String FOOD_ID = "Food id";

  String FOOD_AMOUNT = "Food amount";

  String MIN_FOOD_AMOUNT = "Minimum food amount";

  static List<String> names() {
    List<String> values = new ArrayList<>();
    for (Field field : ConfigKey.class.getDeclaredFields()) {
      try {
        values.add((String) field.get(null));
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }
    return values;
  }
}
