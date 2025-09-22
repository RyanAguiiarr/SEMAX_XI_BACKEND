package SEMAC_BACKEND.SEMAC_BACKEND.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Inscrito {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "campo nome_completo não pode estar vazio")
    private String nome_completo;

    @NotBlank(message = "campo email não pode estar vazio")
    @Email
    private String email;

    @ManyToMany
    @JoinTable(
            name = "inscrito_palestra",
            joinColumns = @JoinColumn(name = "inscrito_id"),
            inverseJoinColumns = @JoinColumn(name = "palestra_id")
    )
    private Set<Palestra> palestras = new HashSet<>();




}
