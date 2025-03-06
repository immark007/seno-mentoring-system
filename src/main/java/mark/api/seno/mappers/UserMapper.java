package mark.api.seno.mappers;

import mark.api.seno.dto.CreatedUserDTO;
import mark.api.seno.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface UserMapper {
    @Mapping(target = "student", source = "student") // Mapeia student para student
    User toEntity(CreatedUserDTO createdUserDTO);

    @Mapping(target = "student", source = "student") // Mapeia student para student
    CreatedUserDTO toDTO(User user);
}
