package org.rspeer.scripts.wintertodt.domain;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import jag.script.RSScriptEvent;
import org.rspeer.event.Subscribe;
import org.rspeer.game.event.*;
import org.rspeer.game.scene.Players;
import org.rspeer.scripts.wintertodt.api.PyromancerEvent;
import org.rspeer.scripts.wintertodt.data.Constant;
import org.rspeer.scripts.wintertodt.domain.config.Config;

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
  public void notify(ClientScriptEvent event) {
    RSScriptEvent src = event.getSource();
    if (src.getScriptId() != Constant.WINT_UPDATE_SCRIPT_ID) {
      return;
    }

    Object[] args = src.getArgs();
    if (args == null || args.length < 2) {
      return;
    }

    boss.update(args);
    state.broadcast(args);
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
      state.message(text);
    }
  }

  @Subscribe
  public void notify(AnimationEvent event) {
    if (event.getSource().equals(Players.self())) {
      timers.animate();
      state.animate(event.getCurrent());
    }
  }

  @Subscribe
  public void notify(PyromancerEvent event) {
    state.update(event.getSource(), config.getGang());
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
