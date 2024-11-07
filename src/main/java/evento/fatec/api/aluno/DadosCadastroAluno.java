package evento.fatec.api.aluno;

import evento.fatec.api.curso.Curso;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAluno(
		@NotBlank
  String nome, String anoFormacao, Long cursoFormado, String ra, String status, String foto
		
	) {

	

}
