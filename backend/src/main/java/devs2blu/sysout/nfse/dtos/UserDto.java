package devs2blu.sysout.nfse.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UserDto {

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userDocIdentification;
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

    public UserDto() {}

}
