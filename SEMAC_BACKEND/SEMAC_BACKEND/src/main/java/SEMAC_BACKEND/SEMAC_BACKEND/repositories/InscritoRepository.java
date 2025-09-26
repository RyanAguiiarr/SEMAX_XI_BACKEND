package SEMAC_BACKEND.SEMAC_BACKEND.repositories;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InscritoRepository extends JpaRepository<Inscrito, String> {
    Optional<Inscrito> findByemail(String email);

    List<Inscrito> findByPalestrasId(Integer palestraId);

    @Query("SELECT i FROM Inscrito i JOIN i.palestras p WHERE i.email = :email AND p.id = :palestraId")
    Optional<Inscrito> findByEmailAndPalestra(@Param("email") String email, @Param("palestraId") Integer palestraId);
}
