package devs2blu.sysout.nfse.services;

import devs2blu.sysout.nfse.dtos.EmailDto;
import devs2blu.sysout.nfse.producers.EmailProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	EmailProducer emailProducer;

	public void sendEmail(EmailDto emailDto) {
		emailProducer.sendEmail(emailDto);
	}
}
