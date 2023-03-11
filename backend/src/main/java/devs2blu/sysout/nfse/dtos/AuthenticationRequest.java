package devs2blu.sysout.nfse.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String userDocIdentification;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

}
