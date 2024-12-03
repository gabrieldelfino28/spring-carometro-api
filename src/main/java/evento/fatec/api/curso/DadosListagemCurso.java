package evento.fatec.api.curso;


public record  DadosListagemCurso (Long id,
		String nome, String unidade, int tempoDeCurso, String periodo){
	public DadosListagemCurso (Curso c) {
		this(c.getId(),c.getNome(),c.getUnidade(), c.getTempoDeCurso(), c.getPeriodo());
	}

}
