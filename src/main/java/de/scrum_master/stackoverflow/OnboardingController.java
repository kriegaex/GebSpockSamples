package de.scrum_master.stackoverflow;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.logging.Logger;

public class OnboardingController {
  private static final Logger LOG = Logger.getLogger("my-logger");

  private OnboardingService onboardingService;

  public ResponseEntity createServiceModel(final String name, final MultipartFile file) throws Exception {
    try {
      final ServiceModelRequestData serviceModelRequestData =
        new ServiceModelRequestData(name, file);
      final ServiceModelDetail createdServiceModel =
        onboardingService.createServiceModel(serviceModelRequestData);
      return new ResponseEntity(createdServiceModel, HttpStatus.OK);
    } catch (MalformedContentException ex) {
      LOG.info("Malformed Content: " + ex);
      return new ResponseEntity(errorMessage(ex), HttpStatus.BAD_REQUEST);
    } catch (ServiceModelNameAlreadyExistsException ex) {
      LOG.info("Service Model Name Already Exists: " + ex);
      return new ResponseEntity(errorMessage(ex), HttpStatus.CONFLICT);
    } catch (ServiceUnavailableException ex) {
      LOG.info("Service Unavailable currently: " + ex);
      return new ResponseEntity(errorMessage(ex), HttpStatus.SERVICE_UNAVAILABLE);
    }
  }

  private ServiceModelDetail errorMessage(Exception ex) {
    return new ServiceModelDetail();
  }
}
