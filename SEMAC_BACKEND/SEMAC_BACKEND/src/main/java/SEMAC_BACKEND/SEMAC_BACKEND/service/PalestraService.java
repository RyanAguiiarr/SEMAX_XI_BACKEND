package SEMAC_BACKEND.SEMAC_BACKEND.service;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Palestra;
import SEMAC_BACKEND.SEMAC_BACKEND.repositories.PalestraRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PalestraService {

    private PalestraRepository palestraRepository;



    public Palestra cadastroPalestra(Palestra palestra) throws Exception{

        try{
            if(palestraRepository.existsByTema(palestra.getTema())){
                throw new Exception("palestra já cadastrada");
            }

            Palestra nova_palestra = new Palestra();
            nova_palestra.setHorario_palestra(palestra.getHorario_palestra());
            nova_palestra.setTema(palestra.getTema());
            nova_palestra.setPalestrante(palestra.getPalestrante());

            palestraRepository.save(nova_palestra);

            return nova_palestra;
        }catch (Exception e){
            throw new Exception("falha no cadastro da palestra");

        }
    }
}
