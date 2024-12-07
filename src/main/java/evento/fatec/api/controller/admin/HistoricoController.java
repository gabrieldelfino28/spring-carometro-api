package evento.fatec.api.controller.admin;

import evento.fatec.api.aluno.Aluno;
import evento.fatec.api.aluno.AlunoService;
import evento.fatec.api.historico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/historico")

public class HistoricoController {
    @Autowired
    private HistoricoRepository repository;
    @Autowired
    private HistoricoService historicoService;

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/cadastro")
    public String carregaPaginaFormulario(@ModelAttribute("aluno") Aluno aluno, Long id, Model model) {
        model.addAttribute("historicos", historicoService.getAllHistorico());
        model.addAttribute("alunos", alunoService.getAllAluno());
        model.addAttribute("aluno", aluno);
        if (id != null) {
            var historico = repository.getReferenceById(id);
            model.addAttribute("historico", historico);
        }
        return "admin/historico/cadastro";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", repository.findAll());
        model.addAttribute("alunos", alunoService.getAllAluno());
        return "admin/historico/listHistorico";
    }

    @PostMapping
    @Transactional
    public String cadastrar(@Valid DadosCadastroHistorico c) {
        var aluno = alunoService.getAlunoById(c.aluno());
        repository.save(new Historico(c, aluno));
        return "redirect:historico";
    }

    @PutMapping
    @Transactional
    public String atualizar(@Valid DadosAtualizadoHistorico c) {
        var aluno = alunoService.getAlunoById(c.aluno());
        var historico = repository.getReferenceById(c.id());
        historico.AtualizarInformacao(c, aluno);
        return "redirect:historico";
    }

    @DeleteMapping
    @Transactional
    public String removeHistorico(Long id) {
        repository.deleteById(id);
        return "redirect:historico";
    }
}
