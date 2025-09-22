package SEMAC_BACKEND.SEMAC_BACKEND.repositories;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Palestra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PalestraRepository extends JpaRepository<Palestra, String> {
    boolean existsByTema(String tema);
    ;
}
