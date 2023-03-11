package devs2blu.sysout.nfse.controllers;

import devs2blu.sysout.nfse.dtos.AuthenticationRequest;
import devs2blu.sysout.nfse.dtos.AuthenticationResponse;
import devs2blu.sysout.nfse.dtos.UserDto;
import devs2blu.sysout.nfse.exceptions.RepeatedEmailException;
import devs2blu.sysout.nfse.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto userDto) {
        try {
            AuthenticationResponse newUser = authService.register(userDto);
            return ResponseEntity.ok(newUser);
        } catch (RepeatedEmailException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthenticationResponse(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.authenticate(request));
    }
}
