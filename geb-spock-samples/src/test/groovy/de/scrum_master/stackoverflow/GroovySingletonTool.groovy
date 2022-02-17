package de.scrum_master.stackoverflow

import java.lang.reflect.Field
import java.lang.reflect.Modifier

class GroovySingletonTool<T> {
  private Class<T> clazz

  GroovySingletonTool(Class<T> clazz) {
    this.clazz = clazz
  }

  void setSingleton(T instance) {
    // Make 'instance' field non-final
    Field field = clazz.getDeclaredField("instance")
    field.modifiers &= ~Modifier.FINAL
    // Only works if singleton instance was unset before
    field.set(clazz.instance, instance)
  }

  void unsetSingleton() {
    setSingleton(null)
  }

  void reinitialiseSingleton() {
    // Unset singleton instance, otherwise subsequent constructor call will fail
    unsetSingleton()
    setSingleton(clazz.newInstance())
  }
}
