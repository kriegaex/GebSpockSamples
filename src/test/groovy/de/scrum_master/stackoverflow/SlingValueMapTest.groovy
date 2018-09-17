package de.scrum_master.stackoverflow

import org.apache.sling.api.resource.ValueMap
import org.apache.sling.api.wrappers.ValueMapDecorator
import spock.lang.Specification

class SlingValueMapTest extends Specification {
  def "Real ValueMap"() {
    given:
    Map<String, Object> map = new HashMap<>()
    map.put("prop1", 21)
    map.put("prop2", 92)
    ValueMap valueMap = new ValueMapDecorator(map)

    expect:
    valueMap.get("prop1") == 21
    valueMap.get("foo") == null
    valueMap.get("prop2") == 92
    valueMap.get("bar") == null
  }

  def "Stubbed ValueMap"() {
    given:
    ValueMap valueMap = Stub() {
      get("prop1") >> 21
      get("prop2") >> 92
      get(_) >> null
    }

    expect:
    valueMap.get("prop1") == 21
    valueMap.get("foo") == null
    valueMap.get("prop2") == 92
    valueMap.get("bar") == null
  }
}
