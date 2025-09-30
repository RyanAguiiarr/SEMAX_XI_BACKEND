package SEMAC_BACKEND.SEMAC_BACKEND.repositories;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InscritoRepository extends JpaRepository<Inscrito, String> {

    Optional<Inscrito> findByEmail(String email);

}
