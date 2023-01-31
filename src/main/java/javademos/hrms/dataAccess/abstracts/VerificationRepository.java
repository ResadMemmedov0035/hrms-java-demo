package javademos.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javademos.hrms.entities.Verification;

public interface VerificationRepository extends JpaRepository<Verification, Integer>{
	
	Verification getByUserEmail(String email);
}
