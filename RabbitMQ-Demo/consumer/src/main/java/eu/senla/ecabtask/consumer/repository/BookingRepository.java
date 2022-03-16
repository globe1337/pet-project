package eu.senla.ecabtask.consumer.repository;

import eu.senla.ecabtask.consumer.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
