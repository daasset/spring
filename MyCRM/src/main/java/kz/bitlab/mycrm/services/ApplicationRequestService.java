package kz.bitlab.mycrm.services;

import kz.bitlab.mycrm.entities.ApplicationRequest;
import java.util.List;

public interface ApplicationRequestService {
    /*
        Getters
     */
    ApplicationRequest getApplicationRequest(Long id);
    List<ApplicationRequest> getAllApplicationRequests();

    /*
        Modifiers
     */
    ApplicationRequest createApplicationRequest(ApplicationRequest applicationRequest);
    ApplicationRequest updateApplicationRequest(ApplicationRequest applicationRequest);
    void deleteApplicationRequest(ApplicationRequest applicationRequest);



}
