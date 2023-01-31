package javademos.hrms.business.requests.jobPost;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostRequest {
	
	private int jobPositionId;
	
	private int cityId;
	
	private int employerId;
	
	private int openPositions;
	
	private boolean isActive;

	@NotBlank
	private String details;

	@Nullable
	private Double minSalary;

	@Nullable
	private Double maxSalary;
	
	@NotNull
	private Date createdAt;
	
	private Date deadline;
}
