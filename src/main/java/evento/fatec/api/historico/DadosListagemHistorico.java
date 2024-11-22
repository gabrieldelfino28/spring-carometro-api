package evento.fatec.api.historico;


import evento.fatec.api.turma.Turma;

import java.time.LocalDate;

public record DadosListagemHistorico(Long id, String nomeEmpresa, String cargo, LocalDate dataContratacao, LocalDate dataDesligamento, String funcao, Long aluno) {
    public DadosListagemHistorico(Historico t) {
        this(t.getId(), t.getNomeEmpresa(), t.getCargo(), t.getDataContratacao(), t.getDataDesligamento(), t.getFuncao(), t.getAluno().getId());
    }
}
