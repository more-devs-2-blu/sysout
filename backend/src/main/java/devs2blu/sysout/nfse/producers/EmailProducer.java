package devs2blu.sysout.nfse.producers;

import devs2blu.sysout.nfse.dtos.EmailDto;
import devs2blu.sysout.nfse.models.EmailModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendEmail(EmailDto emailDto) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        rabbitTemplate.convertAndSend("email_queue", emailModel);
    }

}
