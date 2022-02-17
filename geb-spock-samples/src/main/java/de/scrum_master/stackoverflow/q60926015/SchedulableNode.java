package de.scrum_master.stackoverflow.q60926015;

import java.util.Map;

public class SchedulableNode {
  private Actor actor;

  public SchedulableNode(Actor actor) {
    this.actor = actor;
  }

  public void populateEdgeInfo(Map<Actor, SchedulableNode> knownNodes) {
    populateDestinationInfo(knownNodes);
    populateSourceInfo(knownNodes);
  }

  protected void populateDestinationInfo(Map<Actor, SchedulableNode> knownNodes) {}

  protected void populateSourceInfo(Map<Actor, SchedulableNode> knownNodes) {}
}
