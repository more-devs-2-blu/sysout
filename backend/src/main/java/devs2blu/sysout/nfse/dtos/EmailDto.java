package devs2blu.sysout.nfse.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDto {
	@NotBlank
	private String ownerRef;

	@NotBlank
	@Email
	private String emailFrom;

	@NotBlank
	@Email
	private String emailTo;

	@NotBlank
	private String subject;

	@NotBlank
	private String text;
}
