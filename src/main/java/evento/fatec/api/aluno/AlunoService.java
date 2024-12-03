package evento.fatec.api.aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AlunoService {

@Autowired
private AlunoRepository repository;

public List<Aluno> getAllAluno(){
	return repository.findAll();
	
}
public Aluno getAlunoById(Long id) {
	 return repository.getReferenceById(id);
}

@Transactional
public Aluno buscarPorRa(String ra) {
    return repository.findAlunoByra(ra);
}
}
