package org.rspeer.scripts.wintertodt.task.game;

import org.rspeer.game.script.Task;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.domain.Domain;

public abstract class GameTask extends Task {

  protected final Domain domain;

  protected GameTask(Domain domain) {
    this.domain = domain;
  }

  @Override
  public final boolean execute() {
    return Province.isInGame() && play();
  }

  protected abstract boolean play();
}
