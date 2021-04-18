package ferramong.auth.controllers;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

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

import ferramong.auth.dtos.ResetPasswordDTO;
import ferramong.auth.dtos.SignUpDTO;
import ferramong.auth.entities.Dweller;
import ferramong.auth.services.AccountManagerService;

@RestController
@CrossOrigin(origins = CorsConfiguration.ALL, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class AccountManagerController {
    @Autowired
    private AccountManagerService accountManagerService;

    @PostMapping(path = "/accountManager/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dweller> signUP(@RequestBody SignUpDTO signUpDTO) {
        if (accountManagerService.signUp(signUpDTO)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/accountManager/getSecretQuestion/{cpf}")
    public ResponseEntity<String> getSecretQuestion(@PathVariable("cpf") @NotBlank String cpf) {
        String secretQuestion = accountManagerService.getSecretQuestion(cpf);
        if (secretQuestion.isBlank()) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(secretQuestion);
        }
    }

    @PostMapping(path = "/accountManager/resetPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dweller> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        if (accountManagerService.resetPassword(resetPasswordDTO)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/accountManager/getDweller/id/{id}")
    public ResponseEntity<Dweller> getDwellerById(@PathVariable("id") @NotBlank Long id) {
        Optional<Dweller> dweller = accountManagerService.getDwellerById(id);

        if (dweller.isPresent()) {
            return ResponseEntity.ok(dweller.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/accountManager/getDweller/name/{name}")
    public ResponseEntity<Dweller> getDwellerByName(@PathVariable("name") @NotBlank String name) {
        Optional<Dweller> dweller = accountManagerService.getDwellerByName(name);

        if (dweller.isPresent()) {
            return ResponseEntity.ok(dweller.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
