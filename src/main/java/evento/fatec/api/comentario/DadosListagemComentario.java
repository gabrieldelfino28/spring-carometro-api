package evento.fatec.api.comentario;


import evento.fatec.api.historico.Historico;

import java.time.LocalDate;

public record DadosListagemComentario(Long id, String categoria, LocalDate data, String conteudo, Long aluno) {
    public DadosListagemComentario(Comentario c) {
        this(c.getId(), c.getCategoria(), c.getData(), c.getConteudo(), c.getAluno().getId());
    }
}
