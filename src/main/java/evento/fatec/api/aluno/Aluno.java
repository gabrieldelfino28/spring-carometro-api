package evento.fatec.api.aluno;

import evento.fatec.api.curso.Curso;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity (name = "aluno")
@Table(name = "aluno")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Aluno {
	
	@Column (name= "aluno_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String anoFormacao;
	private String ra;
	private String status;
	private String foto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "curso_id", referencedColumnName = "curso_id")
	private Curso cursoFormado;
		
	public Aluno(@Valid DadosCadastroAluno alu, Curso cursoFormado) {
		this.nome = alu.nome();
		this.anoFormacao = alu.anoFormacao();
		this.ra = alu.ra();
		this.status = alu.status();
		this.foto = alu.foto();
		this.cursoFormado = cursoFormado;
	}

	public void AtualizarInformacao(@Valid DadosAtualizadoAluno alu, Curso cursoFormado) {
		this.nome = alu.nome();
		this.anoFormacao = alu.anoFormacao();
		this.ra = alu.ra();
		this.status = alu.status();
		this.foto = alu.foto();	
		this.cursoFormado = cursoFormado;
	}
}

