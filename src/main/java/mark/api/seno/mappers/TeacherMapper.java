package mark.api.seno.mappers;


import mark.api.seno.dto.CreateTeacherDTO;
import mark.api.seno.dto.TeacherDTO;
import mark.api.seno.model.teacher.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    @Mapping(target = "user", ignore = true)
    Teacher toEntity(TeacherDTO teacherDTO);

    TeacherDTO toDto(Teacher teacher);
}
