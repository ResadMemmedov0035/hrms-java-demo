package javademos.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javademos.hrms.entities.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Integer>{
	
	@Query("From VerificationCode Where user.email=:email Order By createdAt Desc")
	VerificationCode getLatestByUserEmail(String email);
}
