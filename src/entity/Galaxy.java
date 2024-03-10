package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Galaxy {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idGalaxy", nullable = false)
    private int idGalaxy;

    @Basic
    @Column(name = "nameGalaxy", nullable = false, length = 255)
    private String nameGalaxy;
}
