package mark.api.seno.dto;

import java.util.UUID;

public record PromoteDTO(
        UUID studentUserId,
        UUID adminUserId
) {
}
