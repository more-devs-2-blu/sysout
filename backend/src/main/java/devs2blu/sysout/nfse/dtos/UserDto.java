package devs2blu.sysout.nfse.dtos;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import devs2blu.sysout.nfse.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

	private UUID id;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String email;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String userDocIdentification;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UserRole role;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String city;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String state;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String district;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String cep;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String address;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String complement;

	@NotBlank
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String number;
}
