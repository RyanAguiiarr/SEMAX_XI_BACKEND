package SEMAC_BACKEND.SEMAC_BACKEND.service;

import SEMAC_BACKEND.SEMAC_BACKEND.entities.Inscrito;
import SEMAC_BACKEND.SEMAC_BACKEND.repositories.InscritoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InscritoService {

    private InscritoRepository inscritoRepository;
    private EmailService emailService;


    public Inscrito cadastro_inscrito(Inscrito inscrito) throws Exception{

        try{
            for(var palestra : inscrito.getPalestras()){

                List numero_lotacao = inscritoRepository.findByPalestrasId(palestra.getId());

                if(numero_lotacao.size() >= 120){
                    throw new Exception("palestra atingiu lotação maxima");
                }


                var jaInscrito = inscritoRepository.findByEmailAndPalestra(inscrito.getEmail(), palestra.getId());
                if (jaInscrito.isPresent()) {
                    throw new Exception("O aluno já está inscrito na palestra: " + palestra.getTema());
                }
            }
            emailService.sendEmail(inscrito.getEmail(), "INSCRIÇÃO SEMAC", "Voçê realizou sua inscrição com sucesso na SEMAC XI, te aguardamos em nosso evento !");

            return inscritoRepository.save(inscrito);
        }catch (Exception e){
            throw new Exception("erro ao cadastrar inscrito" + e);
        }
    }



}
