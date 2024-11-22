package evento.fatec.api.aluno;

public record  DadosListagemAluno (Long id,
		String nome, String anoFormacao, Long cursoFormado, Long turma, String ra, String status, String foto){
	public DadosListagemAluno (Aluno alu) {
		this(alu.getId(),alu.getNome(), alu.getAnoFormacao(), alu.getCursoFormado().getId(), alu.getTurma().getId(), alu.getRa(),alu.getStatus(),alu.getFoto());
	}

}
