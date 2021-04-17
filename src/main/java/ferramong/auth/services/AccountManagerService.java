package ferramong.auth.services;

import org.jvnet.hk2.annotations.Service;

import ferramong.auth.dtos.SignUpDTO;

@Service
public class AccountManagerService {
    
    public Boolean signUp(SignUpDTO signUpDTO) {
        return false;
    }
}
