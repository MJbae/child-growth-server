package mj.childGrowth;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ViewAggMqProducer implements CommandLineRunner {

    private static final String topicExchange = "cg_ex";

    private final RabbitTemplate rabbitTemplate;

    public ViewAggMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(topicExchange, "cg.agg.view.detail", args);
    }

}
