package evento.fatec.api.historico;

import evento.fatec.api.aluno.Aluno;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Entity(name = "historico")
@Table(name = "historico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Historico {

    @Column(name = "historico_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEmpresa;
    private String cargo;
    private LocalDate dataContratacao;
    private String dataDesligamento;
    private String funcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", referencedColumnName = "aluno_id")
    private Aluno aluno;

    public Historico(@Valid DadosCadastroHistorico t, Aluno alu) {
        this.nomeEmpresa = t.nomeEmpresa();
        this.cargo = t.cargo();
        this.dataContratacao = t.dataContratacao();
        this.dataDesligamento = t.dataDesligamento();
        this.funcao = t.funcao();
        this.aluno = alu;
    }

    public Historico(@Valid InsertHistoricoFromForm t, Aluno alu) {
        this.nomeEmpresa = t.nomeEmpresa();
        this.cargo = t.cargo();
        this.dataContratacao = t.dataContratacao();
        this.dataDesligamento = t.dataDesligamento();
        this.funcao = t.funcao();
        this.aluno = alu;
    }

    public void AtualizarInformacao(@Valid DadosAtualizadoHistorico t, Aluno alu) {
        this.nomeEmpresa = t.nomeEmpresa();
        this.cargo = t.cargo();
        this.dataContratacao = t.dataContratacao();
        this.dataDesligamento = t.dataDesligamento();
        this.funcao = t.funcao();
        this.aluno = alu;
    }
}
