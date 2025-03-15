package mark.api.seno.mappers;

import mark.api.seno.dto.StudentDTO;
import mark.api.seno.model.student.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "user", ignore = true)
    Student toEntity(StudentDTO studentDTO);

    StudentDTO toDTO(Student student);

}
