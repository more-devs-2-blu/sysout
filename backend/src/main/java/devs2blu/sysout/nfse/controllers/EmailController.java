package devs2blu.sysout.nfse.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devs2blu.sysout.nfse.dtos.EmailDto;
import devs2blu.sysout.nfse.models.EmailModel;
import devs2blu.sysout.nfse.services.EmailService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/email")
public class EmailController {
	@Autowired
	EmailService emailService;

	@PostMapping
	public ResponseEntity<Object> sendEmail(@RequestBody @Valid EmailDto emailDto) {
		emailService.sendEmail(emailDto);
		var emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		return new ResponseEntity<>(emailModel, HttpStatus.OK);
	}
}
