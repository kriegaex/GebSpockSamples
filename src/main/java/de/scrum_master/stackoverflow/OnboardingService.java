package de.scrum_master.stackoverflow;

public class OnboardingService {
  public ServiceModelDetail createServiceModel(ServiceModelRequestData serviceModelRequestData) throws Exception {
    System.out.println("real method 'createServiceModel'");
    return new ServiceModelDetail();
  }
}
