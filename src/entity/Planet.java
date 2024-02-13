package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Planet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPlanet", nullable = false)
    private int idPlanet;

    @Basic
    @Column(name = "namePlanet", nullable = false, length = 255)
    private String namePlanet;

    @Basic
    @Column(name = "radius", nullable = false, precision = 2)
    private BigDecimal radius;

    @Basic
    @Column(name = "core_temperature", nullable = true, precision = 2)
    private BigDecimal coreTemperature;

    @Basic
    @Column(name = "atmosphere", nullable = false)
    private byte atmosphere;

    @Basic
    @Column(name = "life", nullable = false)
    private byte life;

    @Basic
    @Column(name = "idGalaxy", nullable = true)
    private Integer idGalaxy;

}
