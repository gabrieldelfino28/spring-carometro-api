package evento.fatec.api.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {

	@Autowired
	private LinkRepository repository;

	public List<Link> getAllLink(){
		return repository.findAll(Sort.by("nomeRede").ascending());
	}
	public Link getLinkById(Long id) {
		return repository.getReferenceById(id);
	}
}
