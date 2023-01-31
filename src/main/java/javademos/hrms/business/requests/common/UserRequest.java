package javademos.hrms.business.requests.common;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min = 8, max = 20)
	private String password;
	
	@NotBlank
	@Size(min = 8, max = 20)
	// TODO: Validation - confirmation and original password must be same. Helper project:
	// https://www.aykutbuyukkaya.codes/how-to-validate-passwords-with-constraints-in-java-spring/
	private String confirmPassword;
}
