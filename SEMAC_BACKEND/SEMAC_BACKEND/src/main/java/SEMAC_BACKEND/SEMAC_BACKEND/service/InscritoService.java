package SEMAC_BACKEND.SEMAC_BACKEND.service;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscrito;
import SEMAC_BACKEND.SEMAC_BACKEND.exceptions.ExceptionGlobal;
import SEMAC_BACKEND.SEMAC_BACKEND.repositories.InscritoRepository;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InscritoService {

    private InscritoRepository inscritoRepository;
    private EmailService emailService;


    public Inscrito cadastro_inscrito(Inscrito inscrito) {

        for (var palestra : inscrito.getPalestras()) {

            // Verifica lotação
            List<Inscrito> inscritosNaPalestra = inscritoRepository.findByPalestrasId(palestra.getId());
            if (inscritosNaPalestra.size() >= 120) {
                throw new ExceptionGlobal.PalestraLotadaException(
                        "A palestra '" + palestra.getTema() + "' atingiu a lotação máxima."
                );
            }

            // Verifica se já está inscrito
            var jaInscrito = inscritoRepository.findByEmailAndPalestra(inscrito.getEmail(), palestra.getId());
            if (jaInscrito.isPresent()) {
                throw new ExceptionGlobal.AlunoJaInscritoException(
                        "O aluno já está inscrito na palestra: " + palestra.getTema()
                );
            }
        }

        return inscritoRepository.save(inscrito);
    }



}
