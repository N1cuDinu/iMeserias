package dinu.imeserias.security;

import dinu.imeserias.enums.RoluriEnum;
import dinu.imeserias.model.Utilizatori;
import dinu.imeserias.repository.UtilizatoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UtilizatoriRepository utilizatoriRepository;
    @Autowired
    public CustomUserDetailsService(UtilizatoriRepository utilizatoriRepository) {
        this.utilizatoriRepository = utilizatoriRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilizatori user = utilizatoriRepository.findFirstByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(RoluriEnum.valueOf(user.getTipUtilizator()).getNumeRol())
        );
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
