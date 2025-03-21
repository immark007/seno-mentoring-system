package mark.api.seno.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mark.api.seno.dto.CreateStudentDTO;
import mark.api.seno.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/students")
@RequiredArgsConstructor
public class StudentController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateStudentDTO> createUserWithStudent(@RequestBody @Valid CreateStudentDTO createStudentDTO) {
        CreateStudentDTO savedUserDTO = userService.createdUserWithStudent(createStudentDTO);

        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }
}
