package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Satellite {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idSatellite", nullable = false)
    private int idSatellite;

    @Basic
    @Column(name = "nameSatellite", nullable = false, length = 255)
    private String nameSatellite;

    @Basic
    @Column(name = "radius", nullable = false, precision = 2)
    private BigDecimal radius;

    @Basic
    @Column(name = "distance_to_planet", nullable = false, precision = 2)
    private BigDecimal distanceToPlanet;

    @Basic
    @Column(name = "idPlanet", nullable = true)
    private Integer idPlanet;

}
