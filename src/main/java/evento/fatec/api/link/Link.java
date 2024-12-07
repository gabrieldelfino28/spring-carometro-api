package evento.fatec.api.link;

import evento.fatec.api.aluno.Aluno;
import evento.fatec.api.historico.InsertHistoricoFromForm;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.Valid;

@Entity(name = "link")
@Table(name = "link")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Link {

    @Column(name = "link_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeRede;
    private String linkURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", referencedColumnName = "aluno_id")
    private Aluno aluno;

    public Link(@Valid DadosCadastroLink l, Aluno alu) {
        this.nomeRede = l.nomeRede();
        this.linkURL = l.linkURL();
        this.aluno = alu;
    }
    public Link(@Valid InsertLinkFromForm l, Aluno alu) {
        this.nomeRede = l.nomeRede();
        this.linkURL = l.linkURL();
        this.aluno = alu;
    }

    public void AtualizarInformacao(@Valid DadosAtualizadoLink l, Aluno alu) {
        this.nomeRede = l.nomeRede();
        this.linkURL = l.linkURL();
        this.aluno = alu;
    }
}
