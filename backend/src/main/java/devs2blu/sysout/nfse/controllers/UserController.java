package devs2blu.sysout.nfse.controllers;

import devs2blu.sysout.nfse.dtos.UserDto;
import devs2blu.sysout.nfse.services.AuthenticationService;
import devs2blu.sysout.nfse.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<UserDto> getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        UserDto user = userService.getUserInfo(authorizationHeader);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
