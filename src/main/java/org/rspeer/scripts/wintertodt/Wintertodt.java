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
import org.rspeer.game.script.meta.ui.ScriptOption;
import org.rspeer.game.script.meta.ui.ScriptUI;
import org.rspeer.scripts.wintertodt.data.GameWorld;
import org.rspeer.scripts.wintertodt.data.Gang;
import org.rspeer.scripts.wintertodt.domain.Domain;
import org.rspeer.scripts.wintertodt.domain.config.Config;
import org.rspeer.scripts.wintertodt.domain.config.ConfigBuilder;
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
    developer = "Doga, Tupac, Kanye",
    version = 1.07,
    paint = PaintScheme.class,
    regions = -3
)
@ScriptUI({
    @ScriptOption(name = "Fletch", type = boolean.class),
    @ScriptOption(name = "Open crates", type = boolean.class),
    @ScriptOption(name = "Side", type = Gang.class),
    @ScriptOption(name = "World", type = GameWorld.class),
    @ScriptOption(name = "Food id", type = int.class),
    @ScriptOption(name = "Food amount", type = int.class),
    @ScriptOption(name = "Minimum food amount", type = int.class)
})
public class Wintertodt extends TaskScript {

  @PaintBinding("Runtime")
  private final StopWatch runtime = StopWatch.start();

  @PaintBinding(value = "Kills", rate = true)
  private final IntSupplier kills = () -> getDomain().getStatistics().getKills();

  @PaintBinding(value = "Failed kills", rate = true)
  private final IntSupplier fails = () -> getDomain().getStatistics().getFails();

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
  public void processArgs(String args) {
    if (args == null || args.isEmpty()) {
      return;
    }

    Config config = getDomain().getConfig();
    config.initialize(ConfigBuilder.ofArgs(args));
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
        UITask.class,
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
        ChopTask.class,
        FletchTask.class,
        RelogTask.class
    );
  }
}
