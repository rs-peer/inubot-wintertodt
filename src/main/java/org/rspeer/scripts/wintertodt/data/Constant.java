package org.rspeer.scripts.wintertodt.data;

import org.rspeer.commons.math.Range;
import org.rspeer.game.position.Position;
import org.rspeer.game.position.area.Area;
import org.rspeer.game.query.component.ItemQuery;
import org.rspeer.game.query.results.ItemQueryResults;

import java.util.function.Function;

public interface Constant {

  String ROOT = "Bruma root";

  String KINDLING = "Bruma kindling";

  int POINTS_THRESHOLD = 500;

  /**
   * The ID of the wintertodt update client-sided runescript.
   * It is responsible for updating the wintertodt interface, which also displays the energy %.
   * <a href="https://github.com/Joshua-F/cs2-scripts/blob/master/scripts/%5Bclientscript,wint_update%5D.cs2">See more</a>
   */
  int WINT_UPDATE_SCRIPT_ID = 1421;

  /**
   * The ID of the wintertodt region. We use this to determine if we're inside the wintertodt minigame area.
   */
  int WINTERTODT_REGION_ID = 6462;

  /**
   * <a href="https://github.com/Joshua-F/cs2-scripts/blob/master/scripts/%5Bproc,wint_timer_resynch%5D.cs2">See</a>
   */
  int WINTERTODT_TIMER_VARBIT_ID = 7980;

  /**
   * The ID of the snowfall EffectObject.yh
   */
  int SNOWFALL_EFFECT_ID = 502;

  /**
   * A range indicating when run should be re-toggled if it was depleted.
   */
  Range TOGGLE_RUN_AT = Range.of(10, 20);

  /**
   * A range indicating at what health percent food should be consumed.
   * Perhaps this should be in Config and user defined?
   */
  Range EAT_FOOD_AT = Range.of(55, 65);

  Area WAITING_AREA = Area.rectangular(
      new Position(1628, 3984),
      new Position(1632, 3982)
  );

  Function<ItemQuery, ItemQueryResults> FOOD = iq -> iq.actions("Eat", "Drink").results();

  Function<ItemQuery, ItemQueryResults> BURNABLE = iq -> iq.names(Constant.KINDLING, Constant.ROOT).results();
}
