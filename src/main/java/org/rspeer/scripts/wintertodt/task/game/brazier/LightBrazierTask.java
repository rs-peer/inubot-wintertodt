package org.rspeer.scripts.wintertodt.task.game.brazier;

import com.google.inject.Inject;
import org.rspeer.game.adapter.scene.Player;
import org.rspeer.game.adapter.scene.SceneObject;
import org.rspeer.game.movement.Movement;
import org.rspeer.game.position.Position;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.TaskDescriptor;
import org.rspeer.scripts.wintertodt.api.Province;
import org.rspeer.scripts.wintertodt.data.Action;
import org.rspeer.scripts.wintertodt.data.Gang;
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
    super(domain, Action.LIGHT);
  }

  @Override
  protected boolean play() {
    Player self = Players.self();
    if (self == null) {
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

    //TODO the above code will walk there but not light due to the below condition
    //need to fix it to light at the start of every game
    if (domain.getState().shouldChop() && domain.getBoss().getEnergy() < 99) {
      return false;
    }

    SceneObject object = Province.findBrazier(gang.getBrazier(), "Light");
    if (object != null) {
      if (object.distance() <= 2 && action.isActive(domain)) {
        return true;
      }

      return object.interact("Light");
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
    return (int) (brazier.distance() / 2) + action.getDuration();
  }
}
