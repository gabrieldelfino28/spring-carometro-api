package evento.fatec.api.historico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {

	@Autowired
	private HistoricoRepository repository;

	public List<Historico> getAllHistorico(){
		return repository.findAll(Sort.by("nomeEmpresa").ascending());
	}
	public Historico getHistoricoById(Long id) {
		return repository.getReferenceById(id);
	}

}
