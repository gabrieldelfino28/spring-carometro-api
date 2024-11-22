package evento.fatec.api.turma;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.Valid;

@Entity(name = "turma")
@Table(name = "turma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Turma {

    @Column(name = "turma_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String periodo;
    private int semestre;
    private int anoInicio;
    private int anoFormacao;

    public Turma(@Valid DadosCadastroTurma t) {
        this.nome = t.nome();
        this.periodo = t.periodo();
        this.semestre = t.semestre();
        this.anoInicio = t.anoInicio();
        this.anoFormacao = t.anoFormacao();
    }

    public void AtualizarInformacao(@Valid DadosAtualizadoTurma t) {
        this.nome = t.nome();
        this.periodo = t.periodo();
        this.semestre = t.semestre();
        this.anoInicio = t.anoInicio();
        this.anoFormacao = t.anoFormacao();
    }
}
