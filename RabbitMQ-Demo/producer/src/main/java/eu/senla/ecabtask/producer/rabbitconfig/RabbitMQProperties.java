package eu.senla.ecabtask.producer.rabbitconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("rabbit")
@Getter
@Setter
public class RabbitMQProperties {

    private String addQueue;
    private String editQueue;
    private String deleteQueue;
    private String auditQueue;

    private String messageExchange;
    private String bookingExchange;
}
