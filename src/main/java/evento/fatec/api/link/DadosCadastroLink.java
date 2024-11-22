package evento.fatec.api.link;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DadosCadastroLink(
		@NotBlank String nomeRede,
		String linkURL,
		Long aluno
	) {
}
