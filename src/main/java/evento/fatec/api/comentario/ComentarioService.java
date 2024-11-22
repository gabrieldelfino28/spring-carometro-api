package evento.fatec.api.comentario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

	@Autowired
	private ComentarioRepository repository;

	public List<Comentario> getAllComentario(){
		return repository.findAll(Sort.by("categoria").ascending());
	}
	public Comentario getComentarioById(Long id) {
		return repository.getReferenceById(id);
	}
}
