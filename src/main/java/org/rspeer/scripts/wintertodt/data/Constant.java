package org.rspeer.scripts.wintertodt.data;

import org.rspeer.commons.math.Range;

public interface Constant {

  /**
   * The ID of the wintertodt update client-sided runescript.
   * It is responsible for updating the wintertodt interface, which also displays the energy %.
   * <a href="https://github.com/Joshua-F/cs2-scripts/blob/master/scripts/%5Bclientscript,wint_update%5D.cs2">See more</a>
   */
  int WINT_UPDATE_SCRIPT_ID = 1421;

  /**
   * A range indicating when run should be re-toggled if it was depleted.
   */
  Range TOGGLE_RUN_AT = Range.of(10, 20);

  /**
   * The ID of the wintertodt region. We use this to determine if we're inside the wintertodt minigame area.
   */
  int WINTERTODT_REGION_ID = 6462;

  /**
   * <a href="https://github.com/Joshua-F/cs2-scripts/blob/master/scripts/%5Bproc,wint_timer_resynch%5D.cs2">See</a>
   */
  int WINTERTODT_TIMER_VARBIT_ID = 7980;
}
