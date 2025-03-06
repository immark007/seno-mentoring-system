package mark.api.seno.service;

import lombok.RequiredArgsConstructor;
import mark.api.seno.dto.CreatedUserDTO;
import mark.api.seno.mappers.UserMapper;
import mark.api.seno.model.user.User;
import mark.api.seno.repository.UserRepository;
import mark.api.seno.validation.UserValidation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserValidation userValidation;

    public CreatedUserDTO createdUserWithStudent(CreatedUserDTO createdUserDTO) {
        User user = userMapper.toEntity(createdUserDTO);
        userValidation.validateEmailUser(user);
        if (user.getStudent() != null) {
            user.getStudent().setUser(user); // Definir explicitamente a relação
        }

        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }
}
