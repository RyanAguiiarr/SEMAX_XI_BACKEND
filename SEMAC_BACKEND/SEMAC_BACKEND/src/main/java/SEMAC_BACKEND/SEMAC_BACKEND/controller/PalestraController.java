package SEMAC_BACKEND.SEMAC_BACKEND.controller;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Palestra;
import SEMAC_BACKEND.SEMAC_BACKEND.service.PalestraService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/palestra")
@AllArgsConstructor
public class PalestraController {

    private PalestraService palestraService;

    @PostMapping
    public ResponseEntity<?> cadastro_palestra(@Valid @RequestBody Palestra palestra) throws Exception{
        try {
           var nova_palestra =  palestraService.cadastroPalestra(palestra);

          return ResponseEntity.ok().body("nova palestra criada" + nova_palestra);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
