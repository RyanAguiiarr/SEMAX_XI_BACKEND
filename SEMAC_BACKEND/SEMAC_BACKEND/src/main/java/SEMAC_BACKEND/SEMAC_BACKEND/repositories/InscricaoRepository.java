package SEMAC_BACKEND.SEMAC_BACKEND.repositories;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InscricaoRepository extends JpaRepository<Inscricao, String> {

    // Verifica se já existe uma inscrição para um inscrito em uma palestra
    Optional<Inscricao> findByInscritoIdAndPalestraId(String inscritoId, Integer palestraId);

    // Conta quantos já se inscreveram em uma palestra
    Long countByPalestraId(Integer palestraId);
}
