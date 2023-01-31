package javademos.hrms.business.requests.candidate;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import javademos.hrms.business.requests.common.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequest extends UserRequest {

	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@Size(min = 7, max = 7, message = "size must be 7")
	private String identityNumber;
	
	@Past
	private Date birthDate;
}
