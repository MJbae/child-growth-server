package mj.api;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MqProducer implements CommandLineRunner {

    private static final String topicExchange = "cg_ex";

    private final RabbitTemplate rabbitTemplate;

    public MqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(topicExchange, "cg.log.view.detail", args);
    }

}
