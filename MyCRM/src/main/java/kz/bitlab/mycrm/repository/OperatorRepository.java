package kz.bitlab.mycrm.repository;

import kz.bitlab.mycrm.entities.Course;
import kz.bitlab.mycrm.entities.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
}
