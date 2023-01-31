package javademos.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javademos.hrms.entities.EmployerConfirm;

public interface EmployerConfirmRepository extends JpaRepository<EmployerConfirm, Integer>{

	EmployerConfirm getByEmployerId(int employerId);
}
