package javademos.hrms.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "verifications")
public class Verification {

	public Verification(User user) {
		this(true, user);
	}
	
	public Verification(boolean isVerified, User user) {
		this.isVerified = isVerified;
		this.user = user;
		this.verifiedAt = new Date();
	}	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "is_verified")
	private boolean isVerified;
	
	@Column(name = "verified_at")
	private Date verifiedAt;
	
	@NonNull
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
}
