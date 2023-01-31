package javademos.hrms.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_experiences")
public class WorkExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NonNull
	@Column(name = "position_title")
	private String positionTitle;
	
	@NonNull
	@Column(name = "employer")
	private String employer;
	
	@NonNull
	@Column(name = "\"from\"")
	private Date from;
	
	@Column(name = "\"to\"")
	private Date to;
}
