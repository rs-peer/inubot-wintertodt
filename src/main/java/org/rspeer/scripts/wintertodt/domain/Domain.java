package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.rspeer.event.Subscribe;
import org.rspeer.game.adapter.scene.EffectObject;
import org.rspeer.game.event.*;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.event.ScriptConfigEvent;

@Singleton
public record Domain(Config config, Boss boss, Timers timers) {

  @Inject
  public Domain {

  }

  @Subscribe
  public void notify(ScriptConfigEvent event) {
    config.initialize(event.getSource());
  }

  @Subscribe
  public void notify(ClientScriptEvent event) {
    boss.update(event.getSource());
  }

  @Subscribe
  public void notify(TickEvent event) {
    timers.tick();
  }

  @Subscribe
  public void notify(AnimationEvent event) {
    if (event.getSource().equals(Players.self())) {
      timers.animate();
    }
  }

  @Subscribe
  public void notify(EffectObjectSpawnEvent event) {
    EffectObject fx = event.getSource();
    //We will be using this to detect snowfall.
  }
}
