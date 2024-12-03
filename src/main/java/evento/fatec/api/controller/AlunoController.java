package evento.fatec.api.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import org.springframework.web.multipart.MultipartFile;

import evento.fatec.api.aluno.Aluno;
import evento.fatec.api.aluno.AlunoRepository;
import evento.fatec.api.aluno.AlunoService;
import evento.fatec.api.aluno.DadosAtualizadoAluno;
import evento.fatec.api.aluno.DadosCadastroAluno;
import evento.fatec.api.curso.CursoRepository;
import evento.fatec.api.curso.CursoService;
import evento.fatec.api.turma.TurmaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/aluno")

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
    
  //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://TEMP//";

    @GetMapping("/cadastro")
    public String carregaPaginaFormulario(Long id, Model model) {
        model.addAttribute("cursos", cursoService.getAllCurso());
        model.addAttribute("turmas", turmaService.getAllTurma());
        if (id != null) {
            var alu = repository.getReferenceById(id);
            model.addAttribute("aluno", alu);
        }
        return "aluno/cadastro";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model) {
        model.addAttribute("lista", repository.findAll());
        model.addAttribute("cursos", cursoService.getAllCurso());
        model.addAttribute("turmas", turmaService.getAllTurma());
        return "aluno/listagem";
    }

    @PostMapping
    @Transactional
    public String cadastrar(@Valid DadosCadastroAluno alu, 
    		@RequestParam("file") MultipartFile file) throws IOException {
    	
        var turma = turmaService.getTurmaById(alu.turma());
        var curso = cursoService.getCursoById(alu.cursoFormado());
        var aluno = new Aluno(alu, curso, turma);
        repository.save(aluno);
        if (!file.isEmpty()) {
               // Get the file and save it somewhere
               byte[] bytes = file.getBytes();
               Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
               Files.write(path, bytes);
        }
        
        aluno.atualizarImagem(UPLOADED_FOLDER);
        return "redirect:aluno";
    }
    

    @GetMapping("/buscar")
    @Transactional
    public String buscarPorRa(@RequestParam("raBusca") String raBusca, Model model) {
        Aluno aluno = alunoService.buscarPorRa(raBusca);
        model.addAttribute("aluno", aluno);
        return "aluno/cadastro";
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
        repository.deleteById(id);
        return "redirect:aluno";
    }
    
    
    
}