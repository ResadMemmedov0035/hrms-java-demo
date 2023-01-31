package javademos.hrms.business.requests.jobPosition;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPositionRequest {
	
	@NotBlank
	String name;
}
