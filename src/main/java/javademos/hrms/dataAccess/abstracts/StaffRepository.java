package javademos.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javademos.hrms.entities.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

}
