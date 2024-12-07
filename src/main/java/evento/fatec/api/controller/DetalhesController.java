package evento.fatec.api.controller;

import evento.fatec.api.aluno.AlunoRepository;
import evento.fatec.api.aluno.AlunoService;
import evento.fatec.api.comentario.Comentario;
import evento.fatec.api.comentario.ComentarioRepository;
import evento.fatec.api.comentario.ComentarioService;
import evento.fatec.api.curso.CursoRepository;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/detalhes")
public class DetalhesController {

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

    @GetMapping
    public String carregaPaginaListagem(Long id, Model model) {
        if (id != null) {
            var alu = repository.getReferenceById(id);
            var historico = new Historico();
            var link = new Link();
            var comentario = new Comentario();

            List<Historico> historicos = historicoService.getAllHistorico();
            for (Historico h : historicos) {
                if(h.getAluno().getId() == id) {
                    historico = h;
                    break;
                }
            }

            List<Link> links = linkService.getAllLink();
            for (Link l : links) {
                if(l.getAluno().getId() == id) {
                    link = l;
                    break;
                }
            }

            List<Comentario> comentarios = comentarioService.getAllComentario();
            for (Comentario c : comentarios) {
                if(c.getAluno().getId() == id) {
                    comentario = c;
                    break;
                }
            }

            model.addAttribute("aluno", alu);
            model.addAttribute("historico", historico);
            model.addAttribute("link", link);
            model.addAttribute("comentario", comentario);
        }
        return "detalhes/detalhes";
    }
}
