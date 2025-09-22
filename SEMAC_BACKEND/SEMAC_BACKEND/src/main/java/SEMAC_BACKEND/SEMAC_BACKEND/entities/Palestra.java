package SEMAC_BACKEND.SEMAC_BACKEND.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Palestra {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true)
    private String tema;

    private String palestrante;

    private String horario_palestra;

    @ManyToMany(mappedBy = "palestras")
    private Set<Inscrito> inscritos = new HashSet<>();

}
