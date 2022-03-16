package eu.senla.ecabtask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripWayPointDto {

    private Long id;
    private String locality;
    private Double latitude;
    private Double longitude;
}
