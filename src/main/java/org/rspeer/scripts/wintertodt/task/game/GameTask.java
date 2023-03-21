package org.rspeer.scripts.wintertodt.task.game;

import org.rspeer.game.script.Task;
import org.rspeer.scripts.wintertodt.data.position.Province;
import org.rspeer.scripts.wintertodt.domain.Domain;

public abstract class GameTask extends Task {

  private final Domain domain;

  protected GameTask(Domain domain) {
    this.domain = domain;
  }

  @Override
  public final boolean execute() {
    return Province.isInGame() && play();
  }

  protected abstract boolean play();
}
