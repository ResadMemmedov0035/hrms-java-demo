package javademos.hrms.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_posts")
public class JobPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "job_position_id")
	private JobPosition jobPosition;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	@Column(name = "open_positions")
	private int openPositions;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@NonNull
	@Column(name = "details")
	private String details;
	
	@Column(name = "min_salary")
	private Double minSalary;

	@Column(name = "max_salary")
	private Double maxSalary;
	
	@NonNull
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "deadline")
	private Date deadline;
}
