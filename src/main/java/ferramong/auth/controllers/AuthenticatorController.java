package ferramong.auth.controllers;

import ferramong.auth.dtos.LoginDTO;
import ferramong.auth.dtos.LoginReponseDTO;
import ferramong.auth.entities.Dweller;
import ferramong.auth.services.AuthenticatorService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;

import javax.validation.constraints.NotBlank;

/*
* Controller
*   Deve ter minimo de logica
*	Serve para chamar serviços
*	Mapeia endpoints
* */

@RestController
@CrossOrigin(
        origins = CorsConfiguration.ALL,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
@AllArgsConstructor
public class AuthenticatorController {
    
    @Autowired
    private AuthenticatorService authenticatorService;

    @GetMapping("/authenticator/validateToken/{token}")
    public ResponseEntity<Dweller> validateToken(@PathVariable("token") @NotBlank String token) {
        if(authenticatorService.validateToken(token)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();            
        }
    }

    @GetMapping("/authenticator/renewToken/{token}")
    public ResponseEntity<Dweller> renewToken(@PathVariable("token") @NotBlank String token) {
        if(authenticatorService.renewToken(token)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();            
        }
    }

    @PostMapping(
        path = "/authenticator/login",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoginReponseDTO> login(@RequestBody LoginDTO loginDTO){
        LoginReponseDTO response = authenticatorService.login(loginDTO);
        if(response.getToken().isBlank()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }
}
