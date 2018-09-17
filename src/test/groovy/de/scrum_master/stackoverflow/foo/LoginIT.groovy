package de.scrum_master.stackoverflow.foo

import geb.spock.GebReportingSpec

class LoginIT extends GebReportingSpec {
  def loginModule = new LoginModule()
  def cfg = new Config()

  def "Test max login attempts"() {
    given: "we are at the login page"
    browser.config.autoClearCookies = false
    to LoginPage

    when: "logging in 4x with wrong credentials"
    (1..4).each {
      loginWithWrongCredentials(it, it < 4)
    }

    then: "we get a 'no more attempts' errorInfoInfo message"
    $('#codi').css('display') == 'none'
    $('#divCodiValidacioError').text().contains('No more attempts')
  }

  def loginWithWrongCredentials(int loginAttempt, boolean checkForWrongPasswordMessage) {
    println "Login attempt ${loginAttempt}"
    loginModule.startLogin(cfg.user.id, cfg.user.phone)
    assert at(LoginValidationPage)
    assert $('div.box_id_header h3').text() == 'Verify your code'
    assert $('#code').css('display').contains('block')
    loginModule.verifyPassword('WRONGPASSWORD')
    assert at(LoginValidationPage)
    if (checkForWrongPasswordMessage) {
      assert $('#codi').css('display').contains('block')
      assert $('#divCodiErrorMsg').text().contains('Wrong password. Try again.')
    }
  }
}
