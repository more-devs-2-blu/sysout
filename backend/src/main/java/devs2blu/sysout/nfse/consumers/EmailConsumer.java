package devs2blu.sysout.nfse.consumers;

import devs2blu.sysout.nfse.enums.EmailStatus;
import devs2blu.sysout.nfse.models.EmailModel;
import devs2blu.sysout.nfse.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailConsumer {

	@Autowired
	EmailService emailService;

	@Autowired
	private JavaMailSender emailSender;

	@RabbitListener(queues = "email_queue")
	public void receiveEmail(EmailModel emailModel) {
		emailModel.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(emailModel.getEmailFrom());
			message.setTo(emailModel.getEmailTo());
			message.setSubject(emailModel.getSubject());
			message.setText(emailModel.getText());
			emailSender.send(message);

			emailModel.setEmailStatus(EmailStatus.SENT);
		} catch (MailException e) {
			emailModel.setEmailStatus(EmailStatus.ERROR);
		}
	}

}
