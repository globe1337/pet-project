package eu.senla.ecabtask.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {

    private Long id;
    private PassengerDto passenger;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pickupTime;

    private boolean asap;
    private int waitingTime;
    private int numberOfPassengers;
    private BigDecimal price;
    private BigDecimal rating;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedOn;

    private List<TripWayPointDto> tripWaypoints;
}
