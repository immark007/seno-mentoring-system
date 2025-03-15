package mark.api.seno.service;

import lombok.RequiredArgsConstructor;
import mark.api.seno.dto.CreateStudentDTO;
import mark.api.seno.dto.CreateTeacherDTO;
import mark.api.seno.dto.CreateUser;
import mark.api.seno.mappers.UserMapper;
import mark.api.seno.model.Role;
import mark.api.seno.model.user.User;
import mark.api.seno.repository.UserRepository;
import mark.api.seno.validation.UserValidation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidation userValidation;
    private final PasswordEncoder passwordEncoder;

    public CreateStudentDTO createdUserWithStudent(CreateStudentDTO createStudentDTO) {
        User user = userMapper.toEntity(createStudentDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Criptografa a senha
        userValidation.validateEmailUser(user);

        if (user.getStudent() != null) {
            user.getStudent().setUser(user);
        }
        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    public CreateTeacherDTO createUserWithTeacher(CreateTeacherDTO createTeacherDTO) {
        User user = userMapper.toEntityTeacher(createTeacherDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Criptografa a senha
        userValidation.validateEmailUser(user);

        if (user.getTeacher() != null) {
            user.getTeacher().setUser(user);
        }

        User savedUser = userRepository.save(user);

        return userMapper.toDTOTeacher(savedUser);
    }

    public User createUser(CreateUser createUserDTO) {
        User user = new User(createUserDTO.email(), passwordEncoder.encode(createUserDTO.password()), createUserDTO.roles());
        return userRepository.save(user);
    }

    public Optional<User> obterLogin(String email) {
        return userRepository.findByEmail(email);
    }
}