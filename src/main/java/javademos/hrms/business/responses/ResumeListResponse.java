package javademos.hrms.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeListResponse {

	int id;
	private String professionalSummary;
	private String linkedinAddress;
	private String webAddress;
	private String profileImage;
}
