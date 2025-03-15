package mark.api.seno.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import mark.api.seno.model.Role;

public record CreateTeacherDTO (
        @Email(message = "invalid email")
        @NotBlank(message = "Field cannot be empty")
        String email,
        @NotBlank(message = "Field cannot be empty")
        String password,
        Role roles,
        TeacherDTO teacher
){
}
