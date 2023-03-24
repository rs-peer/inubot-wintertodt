package org.rspeer.scripts.wintertodt.api;

import org.rspeer.event.Event;
import org.rspeer.scripts.wintertodt.domain.Pyromancer;

public class PyromancerEvent extends Event<Pyromancer> {

  public PyromancerEvent(Pyromancer source) {
    super(source);
  }
}
