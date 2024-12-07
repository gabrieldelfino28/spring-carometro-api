package evento.fatec.api.controller.admin;

import evento.fatec.api.aluno.AlunoService;
import evento.fatec.api.link.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/link")

public class LinkController {
    @Autowired
    private LinkRepository repository;
    @Autowired
    private LinkService linkService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/cadastro")
    public String carregaPaginaFormulario(Long id, Model model) {
        model.addAttribute("links", linkService.getAllLink());
        model.addAttribute("alunos", alunoService.getAllAluno());
        if (id != null) {
            var link = repository.getReferenceById(id);
            model.addAttribute("link", link);
        }
        return "admin/link/cadastro";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", repository.findAll());
        model.addAttribute("alunos", alunoService.getAllAluno());
        return "admin/link/listLink";
    }

    @PostMapping
    @Transactional
    public String cadastrar(@Valid DadosCadastroLink t) {
        var aluno = alunoService.getAlunoById(t.aluno());
        repository.save(new Link(t, aluno));
        return "redirect:link";
    }

    @PutMapping
    @Transactional
    public String atualizar(@Valid DadosAtualizadoLink t) {
        var aluno = alunoService.getAlunoById(t.aluno());
        var link = repository.getReferenceById(t.id());
        link.AtualizarInformacao(t, aluno);
        return "redirect:link";
    }

    @DeleteMapping
    @Transactional
    public String removeLink(Long id) {
        repository.deleteById(id);
        return "redirect:link";
    }
}
