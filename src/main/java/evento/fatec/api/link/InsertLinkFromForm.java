package evento.fatec.api.link;

import jakarta.validation.constraints.NotBlank;

public record InsertLinkFromForm(
        @NotBlank String nomeRede,
        String linkURL
) {
}
