package org.rspeer.scripts.wintertodt.task.generic;

import com.google.inject.Inject;
import org.rspeer.game.adapter.component.inventory.Bank;
import org.rspeer.game.component.Interfaces;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.api.XGame;
import org.rspeer.scripts.wintertodt.domain.Domain;

@TaskDescriptor(
    name = "Relogging",
    blocking = true,
    priority = 200
)
public class RelogTask extends Task {

  private final Domain domain;

  @Inject
  public RelogTask(Domain domain) {
    this.domain = domain;
  }

  @Override
  public boolean execute() {
    if (!domain.getState().isRelog()) {
      return false;
    }

    if (Province.isInGame() && !domain.getBoss().isRespawning()) {
      return false;
    }

    if (Bank.isOpen()) {
      Interfaces.closeSubs();
      return true;
    }

    if (XGame.logout()) {
      domain.getState().setRelog(false);
      return true;
    }

    return false;
  }
}
