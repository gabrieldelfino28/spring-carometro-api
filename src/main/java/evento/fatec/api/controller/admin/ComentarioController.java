package evento.fatec.api.controller.admin;

import evento.fatec.api.aluno.AlunoService;
import evento.fatec.api.comentario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/comentario")

public class ComentarioController {
    @Autowired
    private ComentarioRepository repository;
    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/cadastro")
    public String carregaPaginaFormulario(Long id, Model model) {
        model.addAttribute("comentarios", comentarioService.getAllComentario());
        model.addAttribute("alunos", alunoService.getAllAluno());
        if (id != null) {
            var comentario = repository.getReferenceById(id);
            model.addAttribute("comentario", comentario);
        }
        return "admin/comentario/cadastro";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", repository.findAll());
        model.addAttribute("alunos", alunoService.getAllAluno());
        return "admin/comentario/listComentario";
    }

    @PostMapping
    @Transactional
    public String cadastrar(@Valid DadosCadastroComentario c) {
        var aluno = alunoService.getAlunoById(c.aluno());
        repository.save(new Comentario(c, aluno));
        return "redirect:comentario";
    }

    @PutMapping
    @Transactional
    public String atualizar(@Valid DadosAtualizadoComentario c) {
        var aluno = alunoService.getAlunoById(c.aluno());
        var comentario = repository.getReferenceById(c.id());
        comentario.AtualizarInformacao(c, aluno);
        return "redirect:comentario";
    }

    @DeleteMapping
    @Transactional
    public String removeComentario(Long id) {
        repository.deleteById(id);
        return "redirect:comentario";
    }
}
