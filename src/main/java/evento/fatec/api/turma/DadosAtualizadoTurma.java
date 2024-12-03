package evento.fatec.api.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizadoTurma(
		@NotNull Long id,
		String nome,
		String periodo,
		int semestre,
		int anoInicio,
		int anoFormacao
	) {
}
