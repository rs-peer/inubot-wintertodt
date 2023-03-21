package org.rspeer.scripts.wintertodt;

import org.rspeer.commons.ArrayUtils;
import org.rspeer.commons.StopWatch;
import org.rspeer.game.Game;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskScript;
import org.rspeer.game.script.meta.ScriptMeta;
import org.rspeer.game.script.meta.paint.PaintBinding;
import org.rspeer.game.script.meta.paint.PaintScheme;
import org.rspeer.scripts.wintertodt.domain.Domain;
import org.rspeer.scripts.wintertodt.task.generic.DialogTask;
import org.rspeer.scripts.wintertodt.task.generic.RunTask;
import org.rspeer.scripts.wintertodt.task.prepare.EnterTask;

@ScriptMeta(
    name = "Wintertodt KILLA",
    developer = "Doga",
    paint = PaintScheme.class,
    regions = -3
)
public class Wintertodt extends TaskScript {

  @PaintBinding("Runtime")
  private final StopWatch runtime = StopWatch.start();

  private Domain getDomain() {
    return injector.getInstance(Domain.class);
  }

  @Override
  public void initialize() {
    Game.getEventDispatcher().subscribe(getDomain());
  }

  @Override
  public void shutdown() {
    Game.getEventDispatcher().unsubscribe(getDomain());
  }

  @Override
  protected Class<? extends Task>[] tasks() {
    return ArrayUtils.getTypeSafeArray(
        DialogTask.class,
        RunTask.class,
        EnterTask.class
    );
  }
}
