package de.scrum_master.stackoverflow

import com.unboundid.ldap.sdk.SearchScope
import org.junit.Rule
import org.zapodot.junit.ldap.EmbeddedLdapRule
import org.zapodot.junit.ldap.EmbeddedLdapRuleBuilder
import spock.lang.Specification
import spock.lang.Unroll

class EmbeddedLDAPServerTest extends Specification {
  static final String BASE_DN = "dc=zapodot,dc=org"

  @Rule
  public EmbeddedLdapRule embeddedLdapRule = EmbeddedLdapRuleBuilder
    .newInstance()
    .usingDomainDsn(BASE_DN)
    .importingLdifs('example.ldif')
    .build()

  @Unroll
  def "Search for #searchFor"() {
    given:
    def connection = embeddedLdapRule.ldapConnection()
    when:
    def searchResult = connection.search(BASE_DN, SearchScope.SUB, "($searchFor)")
    then:
    searchResult.entryCount == entryCount

    where:
    searchFor                        | entryCount
    "objectClass=person"             | 1
    "cn=Sondre Eikanger Kvalo"       | 1
    "ou=people"                      | 1
    "ou=groups"                      | 1
    "objectclass=organizationalUnit" | 2
    "objectclass=top"                | 4
  }
}
