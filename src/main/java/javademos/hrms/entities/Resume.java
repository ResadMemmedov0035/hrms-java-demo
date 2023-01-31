package javademos.hrms.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resumes")
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NonNull
	@Column(name = "professional_summary")
	private String professionalSummary;
	
	@Column(name = "linkedin_address")
	private String linkedinAddress;
	
	@Column(name = "web_address")
	private String webAddress;
	
	@Column(name = "profile_image")
	private String profileImage;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate; 
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "resume_id", nullable = false)
	@OrderBy("to DESC")
	private Set<Education> educations = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "resume_id", nullable = false)
	@OrderBy("to DESC")
	private Set<WorkExperience> workExperiences = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "resume_id", nullable = false)
	private Set<LanguageSkill> languageSkills = new HashSet<>();
}
