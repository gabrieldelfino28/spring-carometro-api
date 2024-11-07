package evento.fatec.api.controller;

import evento.fatec.api.aluno.*;
import evento.fatec.api.curso.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import evento.fatec.api.curso.CursoService;
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

    @GetMapping("/cadastro")
    public String carregaPaginaFormulario(Long id, Model model) {
        model.addAttribute("cursos", cursoService.getAllCurso());
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
        return "aluno/listagem";
    }

    @PostMapping
    @Transactional
    public String cadastrar(@Valid DadosCadastroAluno alu) {
        var curso = cursoService.getCursoById(alu.cursoFormado());
        var aluno = new Aluno(alu, curso);
        repository.save(aluno);
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
        var curso = cursoService.getCursoById(alu.cursoFormado());
        var aluno = repository.getReferenceById(alu.id());
        //aluno.setCursoFormado(curso);
        aluno.AtualizarInformacao(alu, curso);
        return "redirect:aluno";
    }

    @DeleteMapping
    @Transactional
    public String removeAluno(Long id) {
        repository.deleteById(id);
        return "redirect:aluno";
    }
}