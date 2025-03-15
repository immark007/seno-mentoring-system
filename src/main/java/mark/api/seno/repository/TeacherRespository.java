package mark.api.seno.repository;

import mark.api.seno.model.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeacherRespository extends JpaRepository<Teacher, UUID> {
}
