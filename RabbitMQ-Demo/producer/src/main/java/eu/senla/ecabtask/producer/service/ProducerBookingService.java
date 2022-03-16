package eu.senla.ecabtask.producer.service;

import eu.senla.ecabtask.api.service.BookingService;
import eu.senla.ecabtask.dto.BookingDto;
import eu.senla.ecabtask.producer.rabbitconfig.RabbitMQProducer;
import eu.senla.ecabtask.producer.rabbitconfig.RabbitMQProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerBookingService implements BookingService {

    private final RabbitMQProducer rabbitMqProducer;
    private final RabbitMQProperties props;

    @Override
    public void add(BookingDto bookingDto) {
        rabbitMqProducer.sendMessage(props.getAddQueue(), bookingDto);
    }

    @Override
    public void update(BookingDto bookingDto) {
        rabbitMqProducer.sendMessage(props.getEditQueue(), bookingDto);
    }

    @Override
    public void delete(Long id) {
        rabbitMqProducer.sendMessage(props.getDeleteQueue(), id);
    }
}
