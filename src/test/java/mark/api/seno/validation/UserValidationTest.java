package mark.api.seno.validation;

import mark.api.seno.model.Role;
import mark.api.seno.model.student.Student;
import mark.api.seno.model.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationTest {
    @Test
    void testarValidadeDeEmailRepetido(){
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("password");
        user.setRoles(Role.STUDENT);
        user.setStudent(new Student());

        User user1 = new User();
        user.setEmail("email@email.com");
        user.setPassword("password");
        user.setRoles(Role.STUDENT);
        user.setStudent(new Student());

    }
}