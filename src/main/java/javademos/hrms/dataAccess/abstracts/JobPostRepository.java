package javademos.hrms.dataAccess.abstracts;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javademos.hrms.entities.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

	@Query("From JobPost j Where " + activePostCondition)
	List<JobPost> findAllActives();

	@Query("From JobPost j Where " + activePostCondition)
	List<JobPost> findAllActives(Sort sort);

	@Query("From JobPost j Where " + activePostCondition + " and j.employer.id = :employerId")
	List<JobPost> findAllActivesByEmployerId(int employerId);
	
	@Query("From JobPost j Where " + activePostCondition + " and j.employer.id = :employerId")
	List<JobPost> findAllActivesByEmployerId(int employerId, Sort sort);
	
	@Modifying
	@Transactional
	@Query("Update JobPost j Set j.isActive = :status Where j.id = :id")
	int updateStatusById(int id, boolean status);
	
	static final String activePostCondition = 
			"j.isActive = true and (j.deadline = null or j.deadline > CURRENT_DATE)";
}
