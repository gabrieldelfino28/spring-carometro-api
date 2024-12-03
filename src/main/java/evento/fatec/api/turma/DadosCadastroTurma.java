package evento.fatec.api.turma;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTurma(
		@NotBlank String nome,
		String periodo,
		int semestre,
		int anoInicio,
		int anoFormacao
	) {
}
