package evento.fatec.api.comentario;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record InsertComentarioFromForm(
        @NotBlank String categoria,
        LocalDate data,
        String conteudo
) {
}
