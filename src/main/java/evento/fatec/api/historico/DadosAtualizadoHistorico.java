package evento.fatec.api.historico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizadoHistorico(
		@NotNull Long id,
		String nomeEmpresa,
		String cargo,
		LocalDate dataContratacao,
		LocalDate dataDesligamento,
		String funcao,
		Long aluno
	) {
}
