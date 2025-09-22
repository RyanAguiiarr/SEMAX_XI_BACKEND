package SEMAC_BACKEND.SEMAC_BACKEND.service;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscrito;
import SEMAC_BACKEND.SEMAC_BACKEND.repositories.InscritoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InscritoService {

    private InscritoRepository inscritoRepository;

    public Inscrito cadastro_inscrito(Inscrito inscrito) throws Exception{

        try{
            for(var palestra : inscrito.getPalestras()){
                var jaInscrito = inscritoRepository.findByEmailAndPalestra(inscrito.getEmail(), palestra.getId());
                if (jaInscrito.isPresent()) {
                    throw new Exception("O aluno já está inscrito na palestra: " + palestra.getTema());
                }

            }
            return inscritoRepository.save(inscrito);
        }catch (Exception e){
            throw new Exception("erro ao cadastrar inscrito" + e);
        }
    }


}
