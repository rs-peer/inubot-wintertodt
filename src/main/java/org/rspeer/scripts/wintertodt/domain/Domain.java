package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.rspeer.event.Subscribe;
import org.rspeer.game.adapter.scene.EffectObject;
import org.rspeer.game.event.*;
import org.rspeer.game.scene.Players;
import org.rspeer.game.script.event.ScriptConfigEvent;

@Singleton
public class Domain {

  private final Config config;
  private final Boss boss;
  private final State state;
  private final Timers timers;
  private final Statistics statistics;

  @Inject
  public Domain(Config config, Boss boss, State state, Timers timers, Statistics statistics) {
    this.config = config;
    this.boss = boss;
    this.state = state;
    this.timers = timers;
    this.statistics = statistics;
  }

  @Subscribe
  public void notify(ScriptConfigEvent event) {
    config.initialize(event.getSource());
  }

  @Subscribe
  public void notify(ClientScriptEvent event) {
    boss.updateEnergy(event.getSource());
  }

  @Subscribe
  public void notify(TickEvent event) {
    timers.tick();
    state.tick();
  }

  @Subscribe
  public void notify(ChatMessageEvent event) {
    ChatMessageEvent.Type type = event.getType();
    if (type == ChatMessageEvent.Type.FILTERED || type == ChatMessageEvent.Type.GAME) {
      String text = event.getContents().toLowerCase();
      timers.message(text);
      statistics.message(text);
    }
  }

  @Subscribe
  public void notify(AnimationEvent event) {
    if (event.getSource().equals(Players.self())) {
      timers.animate();
      state.setLastAnimation(event.getCurrent());
    }
  }

  @Subscribe
  public void notify(EffectObjectSpawnEvent event) {
    EffectObject fx = event.getSource();
    //We will be using this to detect snowfall.
  }

  public Config getConfig() {
    return config;
  }

  public Boss getBoss() {
    return boss;
  }

  public State getState() {
    return state;
  }

  public Timers getTimers() {
    return timers;
  }

  public Statistics getStatistics() {
    return statistics;
  }
}
