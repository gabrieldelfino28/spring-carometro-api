package evento.fatec.api.link;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizadoLink(
		@NotNull Long id,
		String nomeRede,
		String linkURL,
		Long aluno
	) {
}
