package de.scrum_master.stackoverflow.q57525767;

public class ByteBuddyProxyGenericImpl<T> implements ByteBuddyProxyGeneric<T> {
  private T target;

  @Override
  public T getTarget() {
    return target;
  }

  @Override
  public void setTarget(T target) {
    this.target = target;
  }
}
