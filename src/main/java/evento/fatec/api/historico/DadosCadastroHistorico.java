package evento.fatec.api.historico;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record DadosCadastroHistorico(
		@NotBlank String nomeEmpresa,
		String cargo,
		LocalDate dataContratacao,
		LocalDate dataDesligamento,
		String funcao,
		Long aluno
	) {
}
