package eu.senla.ecabtask.consumer.service;

import eu.senla.ecabtask.api.service.BookingService;
import eu.senla.ecabtask.consumer.model.Booking;
import eu.senla.ecabtask.consumer.repository.BookingRepository;
import eu.senla.ecabtask.dto.BookingDto;
import eu.senla.ecabtask.exception.BookingNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ConsumerBookingService implements BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper converter;

    @Override
    public void add(BookingDto bookingDto) {
        Booking result = bookingRepository.save(converter.map(bookingDto, Booking.class));
        log.debug("RECORD WAS SAVED: id=" + result.getId());
    }

    @Override
    public void update(BookingDto bookingDto) {
        Booking booking = converter.map(bookingDto, Booking.class);
        if (booking.getId() != null && bookingRepository.existsById(booking.getId())) {
            Booking result = bookingRepository.save(booking);
            log.debug("RECORD WAS UPDATED: id=" + result.getId());
        } else {
            log.error("RECORD WASN'T FOUND: id=" + booking.getId());
            throw new BookingNotFoundException("There is no booking with id=" + booking.getId());
        }
    }

    @Override
    public void delete(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            log.debug("RECORD WAS DELETED: id=" + id);
        } else {
            log.error("Record WASN'T FOUND: id=" + id);
            throw new BookingNotFoundException("There is no booking with id=" + id);
        }
    }
}
