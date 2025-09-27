package SEMAC_BACKEND.SEMAC_BACKEND.service;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscrito;
import SEMAC_BACKEND.SEMAC_BACKEND.exceptions.ExceptionGlobal;
import SEMAC_BACKEND.SEMAC_BACKEND.repositories.InscritoRepository;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InscritoService {

    private InscritoRepository inscritoRepository;
    private EmailService emailService;


    public Inscrito cadastro_inscrito(Inscrito inscrito) { // Não precisa mais de "throws Exception"

        for (var palestra : inscrito.getPalestras()) {

            List<?> numero_lotacao = inscritoRepository.findByPalestrasId(palestra.getId());
            if (numero_lotacao.size() >= 120) {
                // Lança a exceção específica
                throw new ExceptionGlobal.PalestraLotadaException("A palestra '" + palestra.getTema() + "' atingiu a lotação máxima.");
            }

            var jaInscrito = inscritoRepository.findByEmailAndPalestra(inscrito.getEmail(), palestra.getId());
            if (jaInscrito.isPresent()) {
                // Lança a outra exceção específica
                throw new ExceptionGlobal.AlunoJaInscritoException("O aluno já está inscrito na palestra: " + palestra.getTema());
            }
        }

        // Se tudo deu certo, salva no final
        // emailService.sendEmail(...)
        return inscritoRepository.save(inscrito);
    }



}
