package evento.fatec.api.aluno;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizadoAluno(
        @NotNull Long id,
        String nome,
        String anoFormacao,
        Long cursoFormado,
        Long turma,
        String ra,
        String status,
        String foto
	) {
}
