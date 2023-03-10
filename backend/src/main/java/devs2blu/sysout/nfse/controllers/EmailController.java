package devs2blu.sysout.nfse.controllers;

import devs2blu.sysout.nfse.dtos.EmailDto;
import devs2blu.sysout.nfse.dtos.UserDto;
import devs2blu.sysout.nfse.models.EmailModel;
import devs2blu.sysout.nfse.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping
    public void sendEmail(@RequestBody @Valid EmailDto emailDto) {
        emailService.sendEmail(emailDto);
    }

}
