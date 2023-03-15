package devs2blu.sysout.nfse.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorHandler {

    private String field;
    private String message;
    
}
