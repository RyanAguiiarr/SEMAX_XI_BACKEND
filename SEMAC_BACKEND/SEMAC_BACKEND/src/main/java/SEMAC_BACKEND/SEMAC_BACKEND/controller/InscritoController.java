package SEMAC_BACKEND.SEMAC_BACKEND.controller;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscrito;
import SEMAC_BACKEND.SEMAC_BACKEND.service.InscritoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/inscrito")
@RestController
@AllArgsConstructor
public class InscritoController {

    private InscritoService inscritoService;

    @PostMapping
    public ResponseEntity cadastro_Inscrito(@Valid @RequestBody Inscrito inscrito) throws Exception{
        try{
            var novo_inscrito = inscritoService.cadastro_inscrito(inscrito);

            return ResponseEntity.ok().body("novo inscrito criado" + novo_inscrito);
        }catch (Exception e){
            throw new Exception(e);
        }
    }
}
