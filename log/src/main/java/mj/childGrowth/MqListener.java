package mj.childGrowth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MqListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "spring-boot")
    public void receiveMessage(final Message message){
        logger.info(message.toString());
    }
}
