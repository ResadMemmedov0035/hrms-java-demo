package javademos.hrms.business.requests.resume;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguageSkillDto {

	@NotBlank
	private String name;

	@Min(1) @Max(5)
	private byte level;
}