package javademos.hrms.business.responses;

import java.util.HashSet;
import java.util.Set;

import javademos.hrms.entities.Education;
import javademos.hrms.entities.LanguageSkill;
import javademos.hrms.entities.WorkExperience;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeResponse {

	private int id;
	private String professionalSummary;
	private String linkedinAddress;
	private String webAddress;
	private String profileImage;
	private Set<Education> educations = new HashSet<>();
	private Set<WorkExperience> workExperiences = new HashSet<>();
	private Set<LanguageSkill> languageSkills = new HashSet<>();
}
