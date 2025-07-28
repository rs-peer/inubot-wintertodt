package org.rspeer.scripts.wintertodt;

import org.rspeer.commons.ArrayUtils;
import org.rspeer.commons.StopWatch;
import org.rspeer.event.Service;
import org.rspeer.game.component.tdi.Skill;
import org.rspeer.game.script.Task;
import org.rspeer.game.script.TaskScript;
import org.rspeer.game.script.meta.ScriptMeta;
import org.rspeer.game.script.meta.paint.PaintBinding;
import org.rspeer.game.script.meta.paint.schemes.InfernoPaintScheme;
import org.rspeer.scripts.wintertodt.domain.Domain;
import org.rspeer.scripts.wintertodt.domain.Statistics;
import org.rspeer.scripts.wintertodt.domain.config.Config;
import org.rspeer.scripts.wintertodt.task.UITask;
import org.rspeer.scripts.wintertodt.task.game.WaitingAreaTask;
import org.rspeer.scripts.wintertodt.task.game.brazier.*;
import org.rspeer.scripts.wintertodt.task.game.roots.ChopTask;
import org.rspeer.scripts.wintertodt.task.game.roots.FletchTask;
import org.rspeer.scripts.wintertodt.task.generic.*;
import org.rspeer.scripts.wintertodt.task.prepare.*;

import java.util.function.IntSupplier;

@ScriptMeta(
    name = "Wintertodt K1LLA",
    developer = "Doga",
    version = 1.4,
    paint = InfernoPaintScheme.class,
    model = Config.class
)
public class Wintertodt extends TaskScript {

  @PaintBinding("Runtime")
  private final StopWatch runtime = StopWatch.start();

  @PaintBinding(value = "Kills", rate = true)
  private final IntSupplier kills = () -> injector.getInstance(Statistics.class).getKills();

  @PaintBinding(value = "Failed kills", rate = true)
  private final IntSupplier fails = () -> injector.getInstance(Statistics.class).getFails();

  @PaintBinding("Experience")
  private final Skill[] skills = {
      Skill.FIREMAKING,
      Skill.WOODCUTTING,
      Skill.FLETCHING,
      Skill.CONSTRUCTION
  };

  @Override
  public Class<? extends Task>[] tasks() {
    return ArrayUtils.getTypeSafeArray(
        UITask.class,
        HopTask.class,
        DialogTask.class,
        RunTask.class,
        EnterTask.class,
        BankTask.class,
        PoolTask.class,
        ExitTask.class,
        FoodTask.class,
        WaitingAreaTask.class,
        LightBrazierTask.class,
        BurnTask.class,
        RepairTask.class,
        ChopTask.class,
        FletchTask.class,
        RelogTask.class,
        DropJunkTask.class
    );
  }

  @Override
  public Class<? extends Service>[] getServices() { //this makes it auto register and unregister the classes from the event registry on script start and end
    return ArrayUtils.getTypeSafeArray(
        Domain.class
    );
  }
}
