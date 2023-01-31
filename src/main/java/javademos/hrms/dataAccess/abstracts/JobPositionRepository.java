package javademos.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javademos.hrms.entities.JobPosition;

public interface JobPositionRepository extends JpaRepository<JobPosition, Integer> {

}
