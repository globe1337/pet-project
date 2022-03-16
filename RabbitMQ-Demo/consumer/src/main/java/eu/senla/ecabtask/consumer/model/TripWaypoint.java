package eu.senla.ecabtask.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trip_waypoint")
public class TripWaypoint extends ECabEntity {

    private String locality;
    private float latitude;
    private float longitude;

}
