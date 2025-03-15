package mark.api.seno.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mark.api.seno.dto.TeacherDTO;
import mark.api.seno.exceptions.UnauthorizedException;
import mark.api.seno.mappers.TeacherMapper;
import mark.api.seno.model.Role;
import mark.api.seno.model.teacher.Teacher;
import mark.api.seno.model.user.User;
import mark.api.seno.repository.TeacherRespository;
import mark.api.seno.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PromotionService {
    private final UserRepository userRepository;
    private final TeacherRespository teacherRespository;
    private final TeacherMapper teacherMapper;

    @Transactional
    public TeacherDTO promoteStudentToTeacher(UUID studentUserId, UUID adminUserId) {
        User adminUser = userRepository.findById(adminUserId)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));

        if (!adminUser.getRoles().equals(Role.ADMIN)) {
            throw new UnauthorizedException("Only admins can promote students to teachers");
        }

        User studentUser = userRepository.findById(studentUserId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        if (!studentUser.getRoles().equals(Role.STUDENT) || studentUser.getStudent() == null) {
            throw new IllegalStateException("User is not a student");
        }

        // Criando e associando professor
        Teacher teacher = new Teacher();
        teacher.setUser(studentUser);
        teacher.setName(studentUser.getStudent().getName());
        teacher.setSubject("Default Subject"); // Ou outro valor conforme a lógica

        teacherRespository.save(teacher);

        // Atualizando role para TEACHER
        studentUser.setRoles(Role.TEACHER);
        userRepository.save(studentUser);

        // Retornando TeacherDTO usando TeacherMapper
        return teacherMapper.toDto(teacher);
    }
}