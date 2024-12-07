package evento.fatec.api.controller.admin;

import evento.fatec.api.comentario.Comentario;
import evento.fatec.api.comentario.ComentarioRepository;
import evento.fatec.api.comentario.ComentarioService;
import evento.fatec.api.historico.Historico;
import evento.fatec.api.historico.HistoricoRepository;
import evento.fatec.api.historico.HistoricoService;
import evento.fatec.api.link.Link;
import evento.fatec.api.link.LinkRepository;
import evento.fatec.api.link.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import evento.fatec.api.aluno.Aluno;
import evento.fatec.api.aluno.AlunoRepository;
import evento.fatec.api.aluno.AlunoService;
import evento.fatec.api.aluno.DadosAtualizadoAluno;
import evento.fatec.api.aluno.DadosCadastroAluno;
import evento.fatec.api.curso.CursoRepository;
import evento.fatec.api.curso.CursoService;
import evento.fatec.api.turma.TurmaService;
import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/admin/aluno")

public class AlunoController {
    @Autowired
    private AlunoRepository repository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TurmaService turmaService;


    @Autowired
    HistoricoService historicoService;
    @Autowired
    HistoricoRepository historicoRepository;

    @Autowired
    LinkService linkService;
    @Autowired
    LinkRepository linkRepository;

    @Autowired
    ComentarioService comentarioService;
    @Autowired
    ComentarioRepository comentarioRepository;

    @GetMapping("/cadastro")
    public String carregaPaginaFormulario(Long id, Model model) {
        model.addAttribute("cursos", cursoService.getAllCurso());
        model.addAttribute("turmas", turmaService.getAllTurma());
        if (id != null) {
            var alu = repository.getReferenceById(id);
            model.addAttribute("aluno", alu);
        }
        return "admin/aluno/cadastro";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", repository.findAll());
        model.addAttribute("cursos", cursoService.getAllCurso());
        model.addAttribute("turmas", turmaService.getAllTurma());
        return "admin/aluno/listagem";
    }

    @PostMapping
    @Transactional
    public String cadastrar(@Valid DadosCadastroAluno alu) {
        var turma = turmaService.getTurmaById(alu.turma());
        var curso = cursoService.getCursoById(alu.cursoFormado());
        var aluno = new Aluno(alu, curso, turma);
        repository.save(aluno);
        return "redirect:aluno";
    }

    @GetMapping("/buscar")
    @Transactional
    public String buscarPorRa(@RequestParam("raBusca") String raBusca, Model model) {
        Aluno aluno = alunoService.buscarPorRa(raBusca);
        model.addAttribute("aluno", aluno);
        return "admin/aluno/cadastro";
    }

    @PutMapping
    @Transactional
    public String atualizar(@Valid DadosAtualizadoAluno alu) {
        var turma = turmaService.getTurmaById(alu.turma());
        var curso = cursoService.getCursoById(alu.cursoFormado());
        var aluno = repository.getReferenceById(alu.id());
        aluno.atualizarInformacao(alu, curso, turma);
        return "redirect:aluno";
    }

    @DeleteMapping
    @Transactional
    public String removeAluno(Long id) {
        cascadeRemove(id);
        repository.deleteById(id);
        return "redirect:aluno";
    }

    private void cascadeRemove(Long id) {

        var historico = new Historico();
        List<Historico> historicos = historicoService.getAllHistorico();
        for (Historico h : historicos) {
            if(h.getAluno().getId() == id) {
                historico = h;
                break;
            }
        }
        historicoRepository.deleteById(historico.getId());

        var link = new Link();
        List<Link> links = linkService.getAllLink();
        for (Link l : links) {
            if(l.getAluno().getId() == id) {
                link = l;
                break;
            }
        }
        linkRepository.deleteById(link.getId());

        var comentario = new Comentario();
        List<Comentario> comentarios = comentarioService.getAllComentario();
        for (Comentario c : comentarios) {
            if(c.getAluno().getId() == id) {
                comentario = c;
                break;
            }
        }
        comentarioRepository.deleteById(comentario.getId());
    }
}