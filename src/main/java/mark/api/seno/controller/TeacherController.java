package mark.api.seno.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mark.api.seno.dto.CreateTeacherDTO;
import mark.api.seno.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateTeacherDTO> createTeacher(@RequestBody @Valid CreateTeacherDTO createTeacherDTO) {
        CreateTeacherDTO savedUserDTO = userService.createUserWithTeacher(createTeacherDTO);
        return ResponseEntity.ok().body(savedUserDTO);
    }
}