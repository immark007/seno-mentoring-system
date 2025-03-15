package mark.api.seno.mappers;

import mark.api.seno.dto.CreateStudentDTO;
import mark.api.seno.dto.CreateTeacherDTO;
import mark.api.seno.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, TeacherMapper.class})
public interface UserMapper {
    @Mapping(target = "student", source = "student") // Mapeia student para student
    User toEntity(CreateStudentDTO createStudentDTO);

    @Mapping(target = "student", source = "student") // Mapeia student para student
    CreateStudentDTO toDTO(User user);

    @Mapping(target = "teacher", source = "teacher")
    User toEntityTeacher(CreateTeacherDTO createTeacherDTO);

    @Mapping(target = "teacher", source = "teacher")
    CreateTeacherDTO toDTOTeacher(User user);
}
