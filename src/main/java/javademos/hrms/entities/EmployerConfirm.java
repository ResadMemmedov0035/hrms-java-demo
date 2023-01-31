package javademos.hrms.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "employer_confirms")
public class EmployerConfirm extends Confirm {
	
	public EmployerConfirm(Employer employer, Staff staff) {
		this(true, employer, staff);
	}
	
	public EmployerConfirm(boolean isConfirmed, Employer employer, Staff staff) {
		super(isConfirmed, staff);
		this.employer = employer;
	}

	@NonNull
	@OneToOne
	@JoinColumn(name = "employer_id")
	private Employer employer;
}
