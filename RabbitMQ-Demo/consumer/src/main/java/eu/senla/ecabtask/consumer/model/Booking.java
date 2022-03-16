package eu.senla.ecabtask.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking extends ECabEntity {

    @Embedded
    private Passenger passenger;

    @Column(name = "pickup_time")
    private LocalDateTime pickupTime;

    private boolean asap;

    @Column(name = "waiting_time")
    private int waitingTime;

    @Column(name = "number_of_passengers")
    private int numberOfPassengers;

    @Column(precision=8, scale=2)
    private BigDecimal price;

    @Column(precision=3, scale=2)
    private BigDecimal rating;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "last_modified_on")
    private LocalDateTime lastModifiedOn;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private List<TripWaypoint> tripWaypoints;



}
