package kz.bitlab.mycrm.repository;

import kz.bitlab.mycrm.entities.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
    List<ApplicationRequest> findByHandled(boolean handled);
    List<ApplicationRequest> findByUserNameContainsIgnoreCase(String userNameFragment);
    List<ApplicationRequest> findByCommentContainsIgnoreCase(String commentFragment);
    List<ApplicationRequest> findByPhoneContainsIgnoreCase(String phoneFragment);
    List<ApplicationRequest> findByCourseNameContainsIgnoreCase(String courseNameFragment);
    List<ApplicationRequest> findByUserNameContainsIgnoreCaseOrCommentContainsIgnoreCaseOrPhoneContainsIgnoreCaseOrCourse_NameContainsIgnoreCase(
            String userNameFragment, String commentFragment, String phoneFragment, String courseNameFragment);


}
