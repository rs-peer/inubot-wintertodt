package org.rspeer.scripts.wintertodt.task.game.brazier;

import com.google.inject.Inject;
import org.rspeer.game.adapter.scene.Player;
import org.rspeer.game.adapter.scene.SceneObject;
import org.rspeer.game.component.tdi.Skill;
import org.rspeer.game.component.tdi.Skills;
import org.rspeer.game.movement.Movement;
import org.rspeer.game.position.Position;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.data.action.Action;
import org.rspeer.scripts.wintertodt.data.position.Gang;
import org.rspeer.scripts.wintertodt.domain.Domain;
import org.rspeer.scripts.wintertodt.task.game.ActionTask;

@TaskDescriptor(
    name = "Lighting the brazier!",
    blocking = true,
    register = true
)
public class LightBrazierTask extends ActionTask {

  @Inject
  public LightBrazierTask(Domain domain) {
    super(domain);
  }

  @Override
  public Action getAction() {
    return Action.LIGHT;
  }

  @Override
  protected boolean play() {
    if (domain.getState().shouldChop()) {
      return false;
    }

    Player self = Players.self();
    if (self == null || self.isAnimating()) {
      return false;
    }

    Gang gang = domain.getConfig().getGang();
    if (isSpawning(gang.getStep())) {
      //Walk to the brazier as the game is starting
      if (self.isMoving() || gang.getBrazier().distance() <= 2) {
        return false;
      }

      Movement.walkTowards(gang.getStep());
      return true;
    }

    SceneObject object = Province.findBrazier(gang.getBrazier(), "Light");
    if (object != null && object.interact("Light")) {
      //TODO this sleep will not work if the player has 200m firemaking experience.
      //A solution is to wait until I add SkillEvent to the bot, and use sleep(duration) instead of sleepUntil
      //We can then call sleep(0) in the skillevent to halt the sleep
      int xp = Skills.getExperience(Skill.FIREMAKING);
      sleepUntil(() -> Skills.getExperience(Skill.FIREMAKING) != xp, getAction().getDuration());
      return true;
    }

    return false;
  }

  private boolean isSpawning(Position brazier) {
    int lightTicks = getLightTicks(brazier);
    int spawnTicks = domain.getBoss().getRespawnTimer();
    return spawnTicks >= lightTicks;
  }

  private int getLightTicks(Position brazier) {
    //Running 2 tiles is a 1 tick action (or walking 1 tile)
    //To calculate ticks needed to light, we do movement ticks + light action ticks
    return (int) (brazier.distance() / 2) + getAction().getDuration();
  }
}
