package javademos.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javademos.hrms.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	boolean existsByEmail(String email);
}