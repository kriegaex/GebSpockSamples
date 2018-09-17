    package de.scrum_master.stackoverflow

    import spock.lang.Specification

    class NestedServiceTest extends Specification {
      static class Service {
        FraudMigrationOnboardingService fraudMigrationOnboardingService

        void doSomething(String name) {
          println "Doing something"
          fraudMigrationOnboardingService.onboard(name)
        }
      }

      static class FraudMigrationOnboardingService {
        void onboard(String name) {
          println "On-boarding $name"
        }
      }

      static class SuccessCallBack {
        void call(int httpResponse) {
          println "Callback HTTP response = $httpResponse"
        }
      }

      def "Some service test"() {
        given:
        def onboardingService = Spy(FraudMigrationOnboardingService)
        def service = new Service(fraudMigrationOnboardingService: onboardingService)
        def successCallBack = new SuccessCallBack()
        def response = 200

        when:
        service.doSomething("ACME Inc.")

        then:
        1 * service.fraudMigrationOnboardingService.onboard(_) >>
          { merchantId -> successCallBack.call(response) }
      }
    }
