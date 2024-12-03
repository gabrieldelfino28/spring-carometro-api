package evento.fatec.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sobre")

public class SobreController {

	@GetMapping
    public String carregaPaginaListagem() {
		return "sobre/sobre";
    }
}
