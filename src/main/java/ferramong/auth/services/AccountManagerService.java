package ferramong.auth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ferramong.auth.dtos.ResetPasswordDTO;
import ferramong.auth.dtos.SignUpDTO;
import ferramong.auth.entities.Dweller;
import ferramong.auth.repositories.DwellerRepository;

@Service
public class AccountManagerService {

    @Autowired
    private DwellerRepository dwellerRepository;

    public Boolean signUp(SignUpDTO signUpDTO) {
        Optional<Dweller> dweller = dwellerRepository.findByCpf(signUpDTO.getCpf());
        if (dweller.isPresent()) {
            return false;
        } else {
            dwellerRepository.save(Dweller.builder().cpf(signUpDTO.getCpf()).name(signUpDTO.getName())
                    .password(signUpDTO.getPassword()).secretQuestion(signUpDTO.getSecretQuestion())
                    .secretAnswer(signUpDTO.getSecretAnswer()).build());
            return true;
        }
    }

    public String getSecretQuestion(String cpf) {
        Optional<Dweller> dweller = dwellerRepository.findByCpf(cpf);
        if (dweller.isPresent()) {
            return dweller.get().getSecretQuestion();
        } else {
            return "";
        }
    }

    public Boolean resetPassword(ResetPasswordDTO resetPasswordDTO) {
        Optional<Dweller> dweller = dwellerRepository.findByCpf(resetPasswordDTO.getCpf());
        if (dweller.isPresent()
                && dweller.get().getSecretAnswer().equalsIgnoreCase(resetPasswordDTO.getSecretAnswer())) {
            dweller.get().setPassword(resetPasswordDTO.getNewPassword());
            dwellerRepository.save(dweller.get());
            return true;
        } else {
            return false;
        }
    }

    public Optional<Dweller> getDwellerById(Long id) {
        return dwellerRepository.findById(id);
    }

    public Optional<Dweller> getDwellerByName(String name) {
        return dwellerRepository.findByName(name);
    }
}
