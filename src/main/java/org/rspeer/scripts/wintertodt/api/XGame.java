package org.rspeer.scripts.wintertodt.api;

import org.rspeer.game.adapter.component.InterfaceComponent;
import org.rspeer.game.component.InterfaceComposite;
import org.rspeer.game.component.Interfaces;

public final class XGame {

  private XGame() {
    throw new IllegalAccessError();
  }

  public static boolean logout() {
    InterfaceComponent logout = Interfaces.query(InterfaceComposite.LOGOUT_TAB, InterfaceComposite.WORLD_SELECT)
        .actions("Logout")
        .results()
        .first();
    return logout != null && logout.interact("Logout");
  }
}

