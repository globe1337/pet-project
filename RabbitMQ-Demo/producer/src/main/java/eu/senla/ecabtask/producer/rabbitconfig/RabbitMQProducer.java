package eu.senla.ecabtask.producer.rabbitconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQProperties props;

    public void sendMessage(String queue, Object msg) {
        rabbitTemplate.convertAndSend(props.getMessageExchange(), queue, msg);
        System.out.println("Message Sent");
    }
}
