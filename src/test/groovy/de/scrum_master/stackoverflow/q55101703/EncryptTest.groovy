package de.scrum_master.stackoverflow.q55101703

import org.junit.runner.RunWith
import org.mockito.exceptions.misusing.MissingMethodInvocationException
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.spockframework.runtime.Sputnik
import spock.lang.FailsWith
import spock.lang.Ignore
import spock.lang.Specification

import java.lang.reflect.Method
import java.sql.Date

import static org.powermock.api.mockito.PowerMockito.*
import static org.mockito.Matchers.*

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Sputnik.class)
@PrepareForTest([UUID.class, Encrypt.class, Date.class])
class EncryptTest extends Specification {
  @FailsWith(MissingMethodInvocationException)
  def "PasswordGenerator"() {
    given:
    final String id = "493410b3-dd0b-4b78-97bf-289f50f6e74f";
    UUID uuid = UUID.fromString(id)
    println uuid

    Method m = method(UUID, "randomUUID")
    println m
    mockStatic(UUID)
    // MissingMethodInvocationException:
    // when() requires an argument which has to be 'a method call on a mock'
    when(UUID.randomUUID()).thenReturn(uuid)
    Encrypt encrypt = new Encrypt()

    when:
    String ret = encrypt.genUUID()

    then:
    ret == id
  }

  def "PasswordGenerator Groovy mock"() {
    given:
    final String id = "493410b3-dd0b-4b78-97bf-289f50f6e74f";
    UUID uuid = UUID.fromString(id)
    println uuid
    Encrypt encrypt = new Encrypt()
    println UUID.randomUUID()
    println UUID.metaClass
    UUID.metaClass.static.randomUUID = { ->
      println "static metaclass method@S"
      uuid
    }
    println UUID.randomUUID()
    println UUID.randomUUID() == uuid

    when:
    String ret = encrypt.genUUID()

    then:
    ret != id
  }

  @FailsWith(IllegalArgumentException)
  def "test"() {
    given:
    def date = new Date(8, 5, 1971)
    mockStatic(Date)
    // IllegalArgumentException at java.sql.Date.valueOf(Date.java:143)
    when(Date.valueOf(anyString())).thenReturn(date)
    Encrypt encrypt = new Encrypt()

    when:
    def ret = encrypt.genDate("1998-12-20")

    then:
    ret == date
  }
}
