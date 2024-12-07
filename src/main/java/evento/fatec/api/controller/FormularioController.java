package evento.fatec.api.controller;

import evento.fatec.api.aluno.Aluno;
import evento.fatec.api.aluno.AlunoRepository;
import evento.fatec.api.aluno.AlunoService;
import evento.fatec.api.aluno.DadosCadastroAluno;
import evento.fatec.api.comentario.*;
import evento.fatec.api.curso.CursoService;
import evento.fatec.api.historico.*;
import evento.fatec.api.link.*;
import evento.fatec.api.turma.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/formulario")
public class FormularioController {

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

    @Autowired
    private ResourceLoader resourceLoader;

    // Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "src/main/resources/static/imagens/";

    @GetMapping
    public String getForms(Long id, Model model) throws Exception {
        reloadResource();
        model.addAttribute("cursos", cursoService.getAllCurso());
        model.addAttribute("turmas", turmaService.getAllTurma());
        if (id != null) {
            var alu = repository.getReferenceById(id);
            model.addAttribute("aluno", alu);
        }
        return "formulario/formulario";
    }

    @PostMapping
    @Transactional
    public String cadastrar(
            @Valid DadosCadastroAluno alu, @Valid DadosCadastroHistorico his,
            @Valid DadosCadastroLink lin, @Valid DadosCadastroComentario com,
            @RequestParam("file") MultipartFile file)
            throws IOException {

        var turma = turmaService.getTurmaById(alu.turma());
        var curso = cursoService.getCursoById(alu.cursoFormado());
        var aluno = new Aluno(alu, curso, turma);
        repository.save(aluno);
        historicoRepository.save(new Historico(his,aluno));
        linkRepository.save(new Link(lin, aluno));
        comentarioRepository.save(new Comentario(com, aluno));

        if (!file.isEmpty()) {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            // Files.write(path, bytes);
            file.transferTo(path);
            aluno.atualizarImagem(file.getOriginalFilename());
            // repository.save(aluno);
        }
        try {
            reloadResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:carometro";
    }

    public void reloadResource() throws Exception {
        // Caminho relativo ao diretório de recursos (por exemplo, dentro de src/main/resources/static/)
        String resourcePath = "classpath:/static/imagens";

        // Carrega o recurso
        Resource resource = resourceLoader.getResource(resourcePath);
    }
}
