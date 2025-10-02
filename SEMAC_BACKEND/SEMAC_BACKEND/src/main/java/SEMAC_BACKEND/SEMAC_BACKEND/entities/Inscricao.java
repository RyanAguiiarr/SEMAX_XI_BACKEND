package SEMAC_BACKEND.SEMAC_BACKEND.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Inscrição")
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "inscrito_id", nullable = false)
    private Inscrito inscrito;

    @ManyToOne
    @JoinColumn(name = "palestra_id", nullable = false)
    private Palestra palestra;

    private LocalDateTime dataInscricao = LocalDateTime.now();
}
