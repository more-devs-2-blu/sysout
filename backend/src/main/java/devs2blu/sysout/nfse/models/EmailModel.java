package devs2blu.sysout.nfse.models;

import devs2blu.sysout.nfse.enums.EmailStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "emails")
public class EmailModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID emailId;

	private String ownerRef;

	private String emailFrom;

	private String emailTo;

	private String subject;
	@Column(columnDefinition = "TEXT")

	private String text;

	private LocalDateTime sendDateEmail;

	private EmailStatus emailStatus;
}
