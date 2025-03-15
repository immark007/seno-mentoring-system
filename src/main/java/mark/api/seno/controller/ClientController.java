package mark.api.seno.controller;

import lombok.RequiredArgsConstructor;
import mark.api.seno.model.Client;
import mark.api.seno.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody Client client) {
        clientService.salvar(client);
    }
}
