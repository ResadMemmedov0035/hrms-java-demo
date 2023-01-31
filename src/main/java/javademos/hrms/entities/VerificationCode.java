package javademos.hrms.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "verification_codes")
public class VerificationCode {
	
	public VerificationCode(String code, User user) {
		this.code = code;
		this.user = user;
		this.createdAt = new Date();
	}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Column(name = "code")
	private String code;
	
	@NotNull
	@Column(name = "created_at")
	private Date createdAt;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
