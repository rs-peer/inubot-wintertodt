package org.rspeer.scripts.wintertodt.data;

import org.rspeer.commons.math.Range;
import org.rspeer.game.position.Position;
import org.rspeer.game.position.area.Area;

public interface Constant {

  String ROOT = "Bruma root";

  String KINDLING = "Bruma kindling";

  int BROKEN_BRAZIER = 29313;

  int POINTS_THRESHOLD = 500;

  /**
   * Axe IDS
   */

  int BRONZE_AXE = 1351;
  int STEEL_AXE = 1353;
  int MITHRIL_AXE = 1355;
  int ADAMANT_AXE = 1357;
  int RUNE_AXE = 1359;
  int DRAGON_AXE = 6739;


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
   * The ID of the snowfall EffectObject.
   */
  int SNOWFALL_EFFECT_ID = 502;

  /**
   * The ID of the 3x3 AoE snowfall EffectObject
   */
  int DANGEROUS_SNOWFALL_EFFECT_ID = 1311;

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
}
