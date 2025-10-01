package SEMAC_BACKEND.SEMAC_BACKEND.service;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscricao;
import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscrito;
import SEMAC_BACKEND.SEMAC_BACKEND.entities.Palestra;
import SEMAC_BACKEND.SEMAC_BACKEND.exceptions.ExceptionGlobal;
import SEMAC_BACKEND.SEMAC_BACKEND.repositories.InscricaoRepository;
import SEMAC_BACKEND.SEMAC_BACKEND.repositories.InscritoRepository;
import SEMAC_BACKEND.SEMAC_BACKEND.repositories.PalestraRepository;
import lombok.AllArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InscritoService {

    private final InscricaoRepository inscricaoRepository;
    private final PalestraRepository palestraRepository;
    private final InscritoRepository inscritoRepository;
    private final EmailService emailService;

    public Inscricao cadastrarInscricao(Inscrito inscrito, Integer palestra_id) {
        // 🔹 Verifica lotação
        Long inscritos = inscricaoRepository.countByPalestraId(palestra_id);

        Inscrito inscritoSalvo = inscritoRepository.findByEmail(inscrito.getEmail())
                .orElseGet(() -> inscritoRepository.save(inscrito));

        Optional<Palestra> palestraOpt = palestraRepository.findById(palestra_id.toString());

        Palestra palestra = palestraOpt.orElseThrow(() ->
                new ExceptionGlobal.PalestraNaoEncontradaException(
                        "Palestra com ID " + palestra_id + " não encontrada"
                )
        );
        if (inscritos >= 100) {
            throw new ExceptionGlobal.PalestraLotadaException(
                    "A palestra '" + palestra.getTema() + "' atingiu a lotação máxima."
            );
        }

        // 🔹 Verifica se já está inscrito
        var jaInscrito = inscricaoRepository.findByInscritoIdAndPalestraId(inscritoSalvo.getId(), palestra_id);
        if (jaInscrito.isPresent()) {
            throw new ExceptionGlobal.AlunoJaInscritoException(
                    "O aluno já está inscrito na palestra: " + palestra.getTema()
            );
        }

        // 🔹 Cria inscrição
        Inscricao novaInscricao = new Inscricao();
        novaInscricao.setInscrito(inscritoSalvo);
        novaInscricao.setPalestra(palestra);
        novaInscricao.setDataInscricao(LocalDateTime.now());

        emailService.sendEmail(inscritoSalvo.getEmail(), "INSCRIÇÃO SEMAC XI 2025", "Sua inscrição foi realizada com sucesso na SEMAC XI 2025 , te aguardamos no dia do evento !");

        return inscricaoRepository.save(novaInscricao);
    }

}
