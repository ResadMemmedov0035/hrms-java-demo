package javademos.hrms.business.abstracts;

import javademos.hrms.core.results.Result;
import javademos.hrms.entities.User;

public interface EmailVerificationService {
		
	Result sendVerificationCodeToUser(User user);
	Result verifyUserByEmail(String email, String code);
}
