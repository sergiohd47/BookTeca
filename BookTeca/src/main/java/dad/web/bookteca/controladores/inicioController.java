package dad.web.bookteca.controladores;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class inicioController {
	@RequestMapping("/")
	public String inicio(Model model) {
		ArrayList<String> listaLibros=new ArrayList<>();
		listaLibros.add("Caperucita Roja");
		listaLibros.add("Los Tres Cerditos");
		listaLibros.add("Hola que tal?");
		model.addAttribute("visibleIniciarSesion", true);
		model.addAttribute("visibleCerrarSesion", false);
		model.addAttribute("listaLibros",listaLibros);
		return "index";
	}

}
