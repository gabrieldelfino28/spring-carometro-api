package evento.fatec.api.link;

public record DadosListagemLink(Long id, String nomeRede, String linkURL, Long aluno) {
    public DadosListagemLink(Link l) {
        this(l.getId(), l.getNomeRede(), l.getLinkURL(), l.getAluno().getId());
    }
}
