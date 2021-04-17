package ferramong.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;

import ferramong.auth.dtos.SignUpDTO;
import ferramong.auth.entities.Dweller;
import ferramong.auth.services.AccountManagerService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(
        origins = CorsConfiguration.ALL,
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
@AllArgsConstructor
public class AccountManagerController {
    
    @Autowired
    private AccountManagerService accountManagerService;
    
    @PostMapping(
        path = "/accountManager/signUp",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Dweller> login(@RequestBody SignUpDTO signUpDTO){
        if(accountManagerService.signUp(signUpDTO)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
