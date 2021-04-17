package ferramong.auth.services;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ferramong.auth.dtos.LoginDTO;
import ferramong.auth.dtos.LoginReponseDTO;
import ferramong.auth.entities.Dweller;
import ferramong.auth.entities.Token;
import ferramong.auth.repositories.DwellerRepository;
import ferramong.auth.repositories.TokenRepository;

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
    private DwellerRepository dwellerRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public Boolean validateToken(String requestToken) {
        Optional<Token> token = tokenRepository.findByToken(requestToken);
        if (token.isPresent()) {
            return isTokenValid(token.get());
        } else
            return false;
    }

    public Boolean renewToken(String requestToken) {
        Optional<Token> token = tokenRepository.findByToken(requestToken);
        if (token.isPresent() && isTokenValid(token.get())) {
            token.get().setExpiresOn(OffsetDateTime.now().plusHours(2));
            tokenRepository.save(token.get());
            return true;
        } else {
            return false;
        }
    }

    public LoginReponseDTO login(LoginDTO login) {
        Optional<Dweller> dwellerOpt = dwellerRepository.findByCpfAndPassword(login.getCpf(), login.getPassword());
        if(dwellerOpt.isPresent()) {
            Dweller dweller = dwellerOpt.get();
            Token token = Token.builder()
                .id(dweller.getId())
                .token(UUID.randomUUID().toString())
                .createdOn(OffsetDateTime.now())
                .expiresOn(OffsetDateTime.now().plusHours(2))
                .build();
            dweller.setToken(token);
            dwellerRepository.save(dweller);
            return LoginReponseDTO.builder()
                .id(dweller.getId())
                .token(token.getToken())
                .build();
        } else {
            return LoginReponseDTO.builder().token("").build();
        }
    }

    private Boolean isTokenValid(Token token) {
        return OffsetDateTime.now().isBefore(token.getExpiresOn());
    }
}
