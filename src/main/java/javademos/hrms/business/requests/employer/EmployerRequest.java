package javademos.hrms.business.requests.employer;

import javax.validation.constraints.NotBlank;

import javademos.hrms.business.requests.common.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class EmployerRequest extends UserRequest {

	@NotBlank
	private String companyName;
	
	@NotBlank
	private String website;
	
	@NotBlank
	private String phoneNumber;
}

