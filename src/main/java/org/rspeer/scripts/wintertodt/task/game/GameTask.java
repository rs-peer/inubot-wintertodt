package org.rspeer.scripts.wintertodt.task.game;

import org.rspeer.game.script.Task;
import org.rspeer.scripts.wintertodt.domain.Domain;

public abstract class GameTask extends Task {

  private final Domain domain;

  public GameTask(Domain domain) {
    this.domain = domain;
  }

  @Override
  public final boolean execute() {
    return domain.isInGame() && play();
  }

  public abstract boolean play();
}
