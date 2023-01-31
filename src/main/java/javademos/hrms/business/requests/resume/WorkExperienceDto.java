package javademos.hrms.business.requests.resume;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceDto {

	@NotBlank
	private String positionTitle;

	@NotBlank
	private String employer;

	@NotNull
	private Date from;

	private Date to;
}