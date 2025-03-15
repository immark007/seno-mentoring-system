package mark.api.seno.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    private String scope;
}
