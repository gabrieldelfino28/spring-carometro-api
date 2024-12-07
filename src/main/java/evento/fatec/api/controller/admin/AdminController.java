package evento.fatec.api.controller.admin;

import evento.fatec.api.aluno.Aluno;
import evento.fatec.api.aluno.AlunoRepository;
import evento.fatec.api.aluno.AlunoService;
import evento.fatec.api.comentario.Comentario;
import evento.fatec.api.comentario.ComentarioRepository;
import evento.fatec.api.comentario.ComentarioService;
import evento.fatec.api.curso.CursoService;
import evento.fatec.api.historico.Historico;
import evento.fatec.api.historico.HistoricoRepository;
import evento.fatec.api.historico.HistoricoService;
import evento.fatec.api.link.Link;
import evento.fatec.api.link.LinkRepository;
import evento.fatec.api.link.LinkService;
import evento.fatec.api.turma.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AlunoService alunoService;
    @Autowired
    AlunoRepository alunoRepository;
    @Autowired
    TurmaService turmaService;
    @Autowired
    CursoService cursoService;

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

    @GetMapping
    public String admin(Long id, Model model) {
        List<Aluno> alunos = alunoService.getAllAluno();
        List<Aluno> pendentes = new LinkedList<>();
        for (Aluno aluno : alunos) {
            if (aluno.getStatus().contains("Pendente")) {
                pendentes.add(aluno);
            }
        }

        if (id != null) {
            var alu = alunoRepository.getReferenceById(id);
            model.addAttribute("aluno", alu);
        }

        model.addAttribute("pendentes", pendentes);
        return "admin/admin/index";
    }

    @DeleteMapping
    @Transactional
    public String reprovarRequest(Long id) {
        cascadeReproval(id);
        alunoRepository.deleteById(id);
        return "redirect:admin";
    }

    private void cascadeReproval(Long id) {

        var historico = new Historico();
        List<Historico> historicos = historicoService.getAllHistorico();
        for (Historico h : historicos) {
            if(h.getAluno().getId() == id) {
                historico = h;
                break;
            }
        }
        if (historico.getId() != null)
            historicoRepository.deleteById(historico.getId());

        var link = new Link();
        List<Link> links = linkService.getAllLink();
        for (Link l : links) {
            if(l.getAluno().getId() == id) {
                link = l;
                break;
            }
        }
        if (link.getId() != null)
            linkRepository.deleteById(link.getId());

        var comentario = new Comentario();
        List<Comentario> comentarios = comentarioService.getAllComentario();
        for (Comentario c : comentarios) {
            if(c.getAluno().getId() == id) {
                comentario = c;
                break;
            }
        }
        if (comentario.getId() != null)
            comentarioRepository.deleteById(comentario.getId());
    }

    @PutMapping
    @Transactional
    public String aprovarRequest(Long id) {
        var aluno = alunoRepository.getReferenceById(id);
        aluno.setStatus("Aprovado");
        return "redirect:admin";
    }
}
