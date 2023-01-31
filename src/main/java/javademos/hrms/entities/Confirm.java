package javademos.hrms.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "confirms")
@Inheritance(strategy = InheritanceType.JOINED)
public class Confirm {
	
	public Confirm(Staff staff) {
		this(true, staff);
	}
	
	public Confirm(boolean isConfirmed, Staff staff) {
		this.isConfirmed = isConfirmed;
		this.staff = staff;
		this.confirmedAt = new Date();
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "is_confirmed")
	private boolean isConfirmed;
	
	@Column(name = "confirmed_at")
	private Date confirmedAt;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;
}
