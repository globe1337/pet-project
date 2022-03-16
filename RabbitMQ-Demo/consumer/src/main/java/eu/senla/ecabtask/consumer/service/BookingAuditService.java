package eu.senla.ecabtask.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookingAuditService {

    public void logAuditMessage(Message message) {
        log.info("AUDIT MESSAGE: " + message.toString());
    }
}
