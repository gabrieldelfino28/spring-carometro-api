package evento.fatec.api.controller.admin;

import evento.fatec.api.turma.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/turma")

public class TurmaController {
		@Autowired
		private TurmaRepository repository;
	    @Autowired
	    private TurmaService cursoService;

	    @GetMapping ("/cadastro")                  
		public String carregaPaginaFormulario (Long id, Model model){
			model.addAttribute("turmas", cursoService.getAllTurma());
			if(id != null) {
		        var turma = repository.getReferenceById(id);
		        model.addAttribute("turma", turma);
		    }
		    return "admin/turma/cadastro";
		}

		@GetMapping                                           
		public String carregaPaginaListagem (Model model){    
		    model.addAttribute("lista", repository.findAll());
		     model.addAttribute("turmas", cursoService.getAllTurma());
		    return "admin/turma/listTurma";
		}

		@PostMapping
		@Transactional
		public String cadastrar(@Valid DadosCadastroTurma t) {
			 repository.save(new Turma(t));
			 return "redirect:turma";
		}
		
		@PutMapping
		@Transactional
		public String atualizar(@Valid DadosAtualizadoTurma t) {
		var turma = repository.getReferenceById(t.id());
			turma.AtualizarInformacao(t);
		    return "redirect:turma";
		}
		
		@DeleteMapping
		@Transactional
		public String removeTurma (Long id) {
			repository.deleteById(id);
			return "redirect:turma";
		}
	}
