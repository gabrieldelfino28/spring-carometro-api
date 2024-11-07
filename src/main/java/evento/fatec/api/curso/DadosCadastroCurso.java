package evento.fatec.api.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCurso(
		@NotBlank
  String nome, String unidade, int tempoDeCurso, String periodo
		
	) {

	

}
