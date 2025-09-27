package SEMAC_BACKEND.SEMAC_BACKEND.controller;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscrito;
// Certifique-se de importar suas exceções se elas estiverem em arquivos separados
import SEMAC_BACKEND.SEMAC_BACKEND.exceptions.ExceptionGlobal.AlunoJaInscritoException;
import SEMAC_BACKEND.SEMAC_BACKEND.exceptions.ExceptionGlobal.PalestraLotadaException;
import SEMAC_BACKEND.SEMAC_BACKEND.service.InscritoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/inscrito")
@RestController
@AllArgsConstructor
public class InscritoController {

    private InscritoService inscritoService;

    @PostMapping
    // O método não precisa de "throws Exception" e não tem try-catch
    public ResponseEntity<Inscrito> cadastro_Inscrito(@Valid @RequestBody Inscrito inscrito) {

        // Apenas chame o serviço. Deixe as exceções acontecerem.
        Inscrito novoInscrito = inscritoService.cadastro_inscrito(inscrito);

        // Se passar daqui, deu tudo certo.
        return ResponseEntity.status(HttpStatus.CREATED).body(novoInscrito);
    }

    // O @ExceptionHandler vai funcionar como o "catch" para o erro de aluno já inscrito
    @ExceptionHandler(AlunoJaInscritoException.class)
    public ResponseEntity<String> handleAlunoJaInscrito(AlunoJaInscritoException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // Retorna 409
    }

    // E este vai funcionar como o "catch" para o erro de palestra lotada
    @ExceptionHandler(PalestraLotadaException.class)
    public ResponseEntity<String> handlePalestraLotada(PalestraLotadaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()); // Retorna 400
    }
}