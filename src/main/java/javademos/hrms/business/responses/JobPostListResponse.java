package javademos.hrms.business.responses;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostListResponse {

	private int id;
	private String employerCompany;
	private String positionName;
	private int openPositions;
	private Date createdAt;
	private Date deadline;
}
