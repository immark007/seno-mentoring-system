package mark.api.seno.validation;

import lombok.RequiredArgsConstructor;
import mark.api.seno.exceptions.UserAlreadyExistsException;
import mark.api.seno.model.user.User;
import mark.api.seno.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidation {
    private final UserRepository userRepository;


    public void validateEmailUser(User user
    ){
        if(validateEmail(user)){
            throw  new UserAlreadyExistsException("User already exists");
        }
    }


    public boolean validateEmail(User user){
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        if(user.getId() == null){
            return userOptional.isPresent();
        }

        return user.getId().equals(userOptional.get().getId()) && userOptional.isPresent();
    }

}
