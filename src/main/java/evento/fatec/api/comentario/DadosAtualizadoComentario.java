package evento.fatec.api.comentario;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizadoComentario(
		@NotNull Long id,
		String categoria,
		LocalDate data,
		String conteudo,
		Long aluno
	) {
}
