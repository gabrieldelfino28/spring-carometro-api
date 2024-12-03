package evento.fatec.api.curso;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity (name = "curso")
@Table(name = "curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")

	public class Curso {
	
	@Column (name= "curso_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String unidade;
	private int tempoDeCurso;
	private String periodo;


	public Curso(@Valid DadosCadastroCurso c) {
		this.nome = c.nome();
		this.unidade = c.unidade();
		this.tempoDeCurso = c.tempoDeCurso();
		this.periodo = c.periodo();
		
	}
	public void AtualizarInformacao(@Valid DadosAtualizadoCurso c) {
		this.nome = c.nome();
		this.unidade = c.unidade();
		this.tempoDeCurso = c.tempoDeCurso();
		this.periodo = c.periodo();
	}
}


