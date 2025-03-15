package mark.api.seno.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mark.api.seno.dto.CreateUser;
import mark.api.seno.dto.PromoteDTO;
import mark.api.seno.dto.TeacherDTO;
import mark.api.seno.model.user.User;
import mark.api.seno.service.PromotionService;
import mark.api.seno.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PromotionService promotionService;

    @PostMapping("/promote")
    public ResponseEntity<TeacherDTO> promoteStudentToTeacher(@RequestBody PromoteDTO promoteDTO) {
        TeacherDTO teacherDTO = promotionService.promoteStudentToTeacher(promoteDTO.studentUserId(), promoteDTO.adminUserId());
        return ResponseEntity.ok(teacherDTO);
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<User> createUser(@RequestBody CreateUser createUser) {
        User user = userService.createUser(createUser);
        return ResponseEntity.ok(user);
    }
}