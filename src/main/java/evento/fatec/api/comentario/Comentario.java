package evento.fatec.api.comentario;

import evento.fatec.api.aluno.Aluno;
import evento.fatec.api.link.DadosAtualizadoLink;
import evento.fatec.api.link.DadosCadastroLink;
import evento.fatec.api.link.InsertLinkFromForm;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Entity(name = "comentario")
@Table(name = "comentario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comentario {

    @Column(name = "comentario_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;
    private LocalDate data;
    private String conteudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", referencedColumnName = "aluno_id")
    private Aluno aluno;

    public Comentario(@Valid DadosCadastroComentario c, Aluno alu) {
        this.categoria = c.categoria();
        this.data = c.data();
        this.conteudo = c.conteudo();
        this.aluno = alu;
    }

    public Comentario(@Valid InsertComentarioFromForm c, Aluno alu) {
        this.categoria = c.categoria();
        this.data = c.data();
        this.conteudo = c.conteudo();
        this.aluno = alu;
    }

    public void AtualizarInformacao(@Valid DadosAtualizadoComentario c, Aluno alu) {
        this.categoria = c.categoria();
        this.data = c.data();
        this.conteudo = c.conteudo();
        this.aluno = alu;
    }
}
