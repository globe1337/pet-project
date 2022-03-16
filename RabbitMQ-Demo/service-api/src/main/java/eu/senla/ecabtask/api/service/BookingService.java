package eu.senla.ecabtask.api.service;

import eu.senla.ecabtask.dto.BookingDto;

public interface BookingService {

    void add(BookingDto bookingDto);

    void update(BookingDto bookingDto);

    void delete(Long id);
}
