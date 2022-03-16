package eu.senla.ecabtask.consumer.rabbitlistener;

import eu.senla.ecabtask.api.service.BookingService;
import eu.senla.ecabtask.consumer.service.BookingAuditService;
import eu.senla.ecabtask.dto.BookingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@RequiredArgsConstructor
public class RabbitMQListener {

    private final BookingAuditService auditService;
    private final BookingService bookingService;

    @RabbitListener(queues = "${rabbit.auditQueue}")
    public void receiveAudit(Message msg) {
        auditService.logAuditMessage(msg);
    }

    @RabbitListener(queues = "${rabbit.addQueue}")
    public void receiveAdd(BookingDto bookingDto) {
        bookingService.add(bookingDto);
    }

    @RabbitListener(queues = "${rabbit.editQueue}")
    public void receiveUpdate(BookingDto bookingDto) {
        bookingService.update(bookingDto);
    }

    @RabbitListener(queues = "${rabbit.deleteQueue}")
    public void receiveDelete(Long id) {
        bookingService.delete(id);
    }
}
