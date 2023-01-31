package javademos.hrms.business.requests.resume;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeRequest {

	private int candidateId;

	@NotBlank
	private String professionalSummary;

	@Nullable
	private String linkedInAddress;

	@Nullable
	private String webAddress;

	@Valid
	private Set<EducationDto> educations;

	@Valid
	private Set<WorkExperienceDto> workExperiences;
	
	@Valid
	private Set<LanguageSkillDto> languageSkills;
}
