package mark.api.seno.dto;

import mark.api.seno.model.Role;

public record CreateUser(String email, String password, Role roles) {
}
