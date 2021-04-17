package ferramong.auth.services;

import ferramong.auth.dtos.LoginDTO;
import ferramong.auth.dtos.LoginReponseDTO;
import ferramong.auth.entities.Dweller;
import ferramong.auth.repositories.SchedulerRepository;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Service
 *      Terá a lógica principal
 *      Não tem a ver com modelagem de dados
 *      Não faz comunicação direta com bd (quem faz é 'repositories')
 *		Não é um estado (por outro lado, 'models' são)
 *		Não modela dados (quem faz isso são os 'models')
 */

@Service
public class AuthenticatorService {

    @Autowired
    private SchedulerRepository schedulerRepository;

    public Boolean validateToken(String token) {
        return false;
    }

    public Boolean renewToken(String token) {
        return false;
    }

    public LoginReponseDTO login(LoginDTO login) {
        LoginReponseDTO reponse = new LoginReponseDTO();
        return reponse;
    }
}
