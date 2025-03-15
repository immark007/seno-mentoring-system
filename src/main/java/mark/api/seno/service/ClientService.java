package mark.api.seno.service;

import lombok.RequiredArgsConstructor;
import mark.api.seno.model.Client;
import mark.api.seno.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public Client salvar(Client client) {
        return clientRepository.save(client);
    }

    public Client obterPorClientId(String clientId) {
        return clientRepository.findByClientId(clientId);
    }
}
