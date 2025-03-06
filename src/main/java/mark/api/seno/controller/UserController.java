package mark.api.seno.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mark.api.seno.dto.CreatedUserDTO;
import mark.api.seno.service.UserService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<CreatedUserDTO> createUserWithStudent(@RequestBody @Valid CreatedUserDTO createdUserDTO) {
        CreatedUserDTO savedUserDTO = userService.createdUserWithStudent(createdUserDTO);

        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }
}
