package kz.bitlab.mycrm.services.impl;

import kz.bitlab.mycrm.entities.ApplicationRequest;
import kz.bitlab.mycrm.repository.ApplicationRequestRepository;
import kz.bitlab.mycrm.services.ApplicationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ApplicationRequestServiceImpl implements ApplicationRequestService {

    private ApplicationRequestRepository applicationRequestRepository;

    @Override
    public ApplicationRequest getApplicationRequest(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cannot get ApplicationRequest with id = null");
        }
        return applicationRequestRepository.findById(id).get();
    }

    @Override
    public List<ApplicationRequest> getAllApplicationRequestsByHandled(boolean handled) {
        return applicationRequestRepository.findByHandled(handled);
    }

    @Override
    public List<ApplicationRequest> getAllApplicationRequestsByUserNameOrCommentOrCourseNameFragment(String fragment) {
        return applicationRequestRepository.findByUserNameContainsIgnoreCaseOrCommentContainsIgnoreCaseOrPhoneContainsIgnoreCaseOrCourse_NameContainsIgnoreCase(
                fragment, fragment, fragment, fragment
        );
    }

    @Override
    public List<ApplicationRequest> getAllApplicationRequests() {
        return applicationRequestRepository.findAll();
    }

    @Override
    public ApplicationRequest createApplicationRequest(ApplicationRequest applicationRequest) {
        if (applicationRequest.getId() != null) {
            throw new IllegalArgumentException("Cannot create ApplicationRequest with existing id");
        }
        return applicationRequestRepository.save(applicationRequest);
    }

    @Override
    public ApplicationRequest updateApplicationRequest(ApplicationRequest applicationRequest) {
        if (applicationRequest.getId() == null) {
            throw new IllegalArgumentException("Cannot update ApplicationRequest without existing id");
        }
        return applicationRequestRepository.save(applicationRequest);
    }

    @Override
    public void deleteApplicationRequest(ApplicationRequest applicationRequest) {
        if (applicationRequest.getId() == null) {
            throw new IllegalArgumentException("Cannot delete ApplicationRequest without existing id");
        }
        applicationRequestRepository.delete(applicationRequest);
    }
}
