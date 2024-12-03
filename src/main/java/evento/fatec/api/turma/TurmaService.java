package evento.fatec.api.turma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repository;

	public List<Turma> getAllTurma(){
		return repository.findAll(Sort.by("nome").ascending());
	}
	public Turma getTurmaById(Long id) {
		return repository.getReferenceById(id);
	}
}
