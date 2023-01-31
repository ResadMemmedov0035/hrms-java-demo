package javademos.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javademos.hrms.entities.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Integer> {

	boolean existsByEmail(String email);
	
}
