package evento.fatec.api.controller;

import evento.fatec.api.comentario.*;
import evento.fatec.api.historico.*;
import evento.fatec.api.link.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import evento.fatec.api.aluno.Aluno;
import evento.fatec.api.aluno.AlunoRepository;
import evento.fatec.api.aluno.AlunoService;
import evento.fatec.api.aluno.DadosAtualizadoAluno;
import evento.fatec.api.curso.CursoService;
import evento.fatec.api.turma.TurmaService;
import jakarta.validation.Valid;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/carometro")

public class CarometroController {
    @Autowired
    private AlunoRepository repository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private TurmaService turmaService;

    //Other Objects
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private HistoricoService historicoService;
    @Autowired
    private LinkRepository linkRepository;
    @Autowired
    private LinkService linkService;
    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model) {
        model.addAttribute("cursos", cursoService.getAllCurso());
        model.addAttribute("turmas", turmaService.getAllTurma());
        if (id != null) {
            var alu = repository.getReferenceById(id);
            model.addAttribute("aluno", alu);
        }
        return "carometro/formulario";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        List<Aluno> alunos = repository.findAll();
        List<Aluno> aprovados = new LinkedList<>();
        for (Aluno al : alunos) {
            if (al.getStatus().contains("Aprovado")) {
                aprovados.add(al);
            }
        }
        model.addAttribute("lista", aprovados);
        model.addAttribute("cursos", cursoService.getAllCurso());
        model.addAttribute("turmas", turmaService.getAllTurma());
        return "carometro/listagem";
    }

    @GetMapping("/buscar")
    @Transactional
    public String buscarPorRa(@RequestParam("raBusca") String raBusca, Model model) {
        Aluno aluno = alunoService.buscarPorRa(raBusca);
        model.addAttribute("aluno", aluno);
        return "carometro/cadastro";
    }

    @PutMapping
    @Transactional
    public String atualizar(@Valid DadosAtualizadoAluno alu) {
        var turma = turmaService.getTurmaById(alu.turma());
        var curso = cursoService.getCursoById(alu.cursoFormado());
        var aluno = repository.getReferenceById(alu.id());
        aluno.atualizarInformacao(alu, curso, turma);
        return "redirect:carometro";
    }

    @DeleteMapping
    @Transactional
    public String removeAluno(Long id) {
        repository.deleteById(id);
        return "redirect:carometro";
    }
}