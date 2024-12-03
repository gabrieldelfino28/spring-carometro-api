package evento.fatec.api.turma;


import evento.fatec.api.turma.Turma;

public record DadosListagemTurma(Long id, String nome, String periodo, int semestre, int anoInicio, int anoFormacao) {
    public DadosListagemTurma(Turma t) {
        this(t.getId(), t.getNome(), t.getPeriodo(), t.getSemestre(), t.getAnoInicio(), t.getAnoFormacao());
    }
}
