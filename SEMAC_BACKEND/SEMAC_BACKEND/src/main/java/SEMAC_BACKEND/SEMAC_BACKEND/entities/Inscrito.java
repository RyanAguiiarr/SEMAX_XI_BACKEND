package SEMAC_BACKEND.SEMAC_BACKEND.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inscrito {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "campo nome_completo não pode estar vazio")
    private String nome_completo;

    @NotBlank(message = "campo email não pode estar vazio")
    @Email(message = "email inválido")
    @Column(unique = true)
    private String email;

    // 🔹 Se quiser acessar inscrições a partir do inscrito
    @OneToMany(mappedBy = "inscrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Inscricao> inscricoes = new HashSet<>();

}
