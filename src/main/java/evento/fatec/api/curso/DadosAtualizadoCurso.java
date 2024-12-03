package evento.fatec.api.curso;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizadoCurso(
	@NotNull
	Long id,
	String nome, String unidade, int tempoDeCurso, String periodo) {
}
