package evento.fatec.api.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import evento.fatec.api.curso.Curso;
import evento.fatec.api.curso.CursoRepository;
import evento.fatec.api.curso.CursoService;
import evento.fatec.api.curso.DadosAtualizadoCurso;
import evento.fatec.api.curso.DadosCadastroCurso;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/curso")

public class CursoController {
		@Autowired
		private CursoRepository repository;
	    @Autowired
	    private CursoService cursoService;

	    @GetMapping ("/cadastro")                  
		public String carregaPaginaFormulario (Long id, Model model){
			model.addAttribute("cursos", cursoService.getAllCurso());
			if(id != null) {
		        var curso = repository.getReferenceById(id);
		        model.addAttribute("curso", curso);
		    }
		    return "admin/curso/cadastro";
		}

		@GetMapping                                           
		public String carregaPaginaListagem (Model model){    
		    model.addAttribute("lista", repository.findAll());
		     model.addAttribute("cursos", cursoService.getAllCurso());
		    return "admin/curso/listagemCurso";
		}

		@PostMapping
		@Transactional
		public String cadastrar(@Valid DadosCadastroCurso c) {
			 repository.save(new Curso(c));
			 return "redirect:curso";
		}
		
		@PutMapping
		@Transactional
		public String atualizar(@Valid DadosAtualizadoCurso c) {
		var curso = repository.getReferenceById(c.id());
			curso.AtualizarInformacao(c);
		    return "redirect:curso";
		}
		
		@DeleteMapping
		@Transactional
		public String removeCurso (Long id) {
			repository.deleteById(id);
			return "redirect:curso";
		}
	}
