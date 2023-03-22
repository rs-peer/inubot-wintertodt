package org.rspeer.scripts.wintertodt;

import org.rspeer.commons.ArrayUtils;
import org.rspeer.commons.StopWatch;
import org.rspeer.game.Game;
import org.rspeer.game.component.tdi.Skill;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskScript;
import org.rspeer.game.script.meta.ScriptMeta;
import org.rspeer.game.script.meta.paint.PaintBinding;
import org.rspeer.game.script.meta.paint.PaintScheme;
import org.rspeer.scripts.wintertodt.domain.Domain;
import org.rspeer.scripts.wintertodt.task.game.FoodTask;
import org.rspeer.scripts.wintertodt.task.game.WaitingAreaTask;
import org.rspeer.scripts.wintertodt.task.game.brazier.*;
import org.rspeer.scripts.wintertodt.task.game.roots.ChopTask;
import org.rspeer.scripts.wintertodt.task.generic.DialogTask;
import org.rspeer.scripts.wintertodt.task.generic.RunTask;
import org.rspeer.scripts.wintertodt.task.prepare.*;

@ScriptMeta(
    name = "Wintertodt K1LLA",
    developer = "Doga, Tupac, Kanye",
    version = 1.02,
    paint = PaintScheme.class,
    regions = -3
)
public class Wintertodt extends TaskScript {

  @PaintBinding("Runtime")
  private final StopWatch runtime = StopWatch.start();

  @PaintBinding("Experience")
  private final Skill[] skills = {
      Skill.FIREMAKING,
      Skill.WOODCUTTING,
      Skill.FLETCHING,
      Skill.CONSTRUCTION
  };

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
        HopTask.class,
        DialogTask.class,
        RunTask.class,
        EnterTask.class,
        BankTask.class,
        ExitTask.class,
        FoodTask.class,
        WaitingAreaTask.class,
        LightBrazierTask.class,
        BurnTask.class,
        RepairTask.class,
        ChopTask.class
    );
  }
}
