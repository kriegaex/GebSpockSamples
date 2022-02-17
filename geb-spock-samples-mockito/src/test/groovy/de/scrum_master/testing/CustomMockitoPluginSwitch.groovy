package de.scrum_master.testing

import org.mockito.plugins.PluginSwitch

class CustomMockitoPluginSwitch implements PluginSwitch {
  boolean isEnabled(String pluginClassName) {
    boolean excludePowerMockMaker = Arrays.stream(new Exception().stackTrace)
      .filter({ it.className.contains("d20210128.ClassThatLogsTest") })
      .findAny()
      .isPresent()
    boolean enabled = excludePowerMockMaker
      ? !pluginClassName.contains("PowerMockMaker")
      : true
    println "Mockito plugin: $pluginClassName -> enabled: $enabled"
    return enabled
  }
}
