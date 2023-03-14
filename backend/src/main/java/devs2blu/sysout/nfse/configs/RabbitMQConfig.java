package devs2blu.sysout.nfse.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue EmailQueue() {
        return new Queue("email_queue", true);
    }

}
