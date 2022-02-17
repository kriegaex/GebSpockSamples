package de.scrum_master.stackoverflow

import org.bouncycastle.cms.SignerInformation
import spock.lang.Specification

class SignerInformationConsumerTest extends Specification {

  /**
   * Manually subclass signed class in order to avoid security exception for mock
   * being created in the original BouncyCastle package
   *
   * @see https://stackoverflow.com/a/51414672/1082681
   */
  static class SignerInformationMock extends SignerInformation {
    protected SignerInformationMock(SignerInformation baseInfo) {
      super(baseInfo)
    }
  }

  //SignerInformation signerInformation = Spy(SignerInformationMock, useObjenesis: true)
  SignerInformation signerInformation = Mock(SignerInformationMock) {
    getDigestAlgOID() >> "aaa"
  }

  def "should return valid array"() {
    given:
    SignerInformationConsumer signerInformationConsumer = new SignerInformationConsumer()
//    signerInformation.getDigestAlgOID() >> "aaa"
    expect:
    signerInformationConsumer.interact(signerInformation) == "aaa"
  }
}
