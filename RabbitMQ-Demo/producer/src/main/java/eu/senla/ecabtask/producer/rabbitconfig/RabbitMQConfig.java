package eu.senla.ecabtask.producer.rabbitconfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RabbitMQProperties.class)
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final RabbitMQProperties props;

    @Bean
    ApplicationRunner runner(AmqpAdmin admin) {
        return args -> admin.initialize();
    }

    @Bean
    RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(objectMapper()));
        return rabbitTemplate;
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    @Bean
    Queue addQueue() {
        return new Queue(props.getAddQueue(), false);
    }

    @Bean
    Queue updateQueue() {
        return new Queue(props.getEditQueue(), false);
    }

    @Bean
    Queue deleteQueue() {
        return new Queue(props.getDeleteQueue(), false);
    }

    @Bean
    Queue messageAuditQueue() {
        return new Queue(props.getAuditQueue(), false);
    }

    @Bean
    FanoutExchange messageExchange() {
        return new FanoutExchange(props.getMessageExchange(), false, true);
    }

    @Bean
    DirectExchange bookingExchange() {
        return new DirectExchange(props.getBookingExchange(), false, true);
    }

    @Bean
    Binding addBinding(DirectExchange bookingExchange) {
        return BindingBuilder.bind(addQueue()).to(bookingExchange).with(addQueue().getName());
    }

    @Bean
    Binding updateBinding(DirectExchange bookingExchange) {
        return BindingBuilder.bind(updateQueue()).to(bookingExchange).with(updateQueue().getName());
    }

    @Bean
    Binding deleteBinding(DirectExchange bookingExchange) {
        return BindingBuilder.bind(deleteQueue()).to(bookingExchange).with(deleteQueue().getName());
    }

    @Bean
    Binding auditMessageBinding(FanoutExchange messageExchange) {
        return BindingBuilder.bind(messageAuditQueue()).to(messageExchange);
    }

    @Bean
    Binding messageAddBinding(FanoutExchange messageExchange) {
        return BindingBuilder.bind(bookingExchange()).to(messageExchange);
    }
}
