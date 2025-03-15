package mark.api.seno.security;

import lombok.RequiredArgsConstructor;
import mark.api.seno.model.user.User;
import mark.api.seno.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userService.obterLogin(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getEmail())
                .password(user.get().getPassword())
                .roles(user.get().getRoles().name())
                .build();
    }
}