package javademos.hrms.dataAccess.abstracts;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javademos.hrms.entities.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {

	List<Resume> findAllByCandidateId(int candidateId);
	
	@Modifying
	@Transactional
	@Query("Update Resume r Set r.profileImage = :path Where r.id = :id")
	int updateProfileImage(int id, String path);
}
