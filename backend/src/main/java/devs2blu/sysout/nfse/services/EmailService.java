package devs2blu.sysout.nfse.services;

import devs2blu.sysout.nfse.enums.emailStatus;
import devs2blu.sysout.nfse.models.EmailModel;
import devs2blu.sysout.nfse.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
	@Autowired
	EmailRepository emailRepository;

	@Autowired
	private JavaMailSender emailSender;

	public void sendEmail(EmailModel emailModel) {
		emailModel.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSender.send(message);

			emailModel.setEmailStatus(emailStatus.SENT);
		} catch (MailException e) {
			emailModel.setEmailStatus(emailStatus.ERROR);
		} finally {
			emailRepository.save(emailModel);
		}
	}
}
