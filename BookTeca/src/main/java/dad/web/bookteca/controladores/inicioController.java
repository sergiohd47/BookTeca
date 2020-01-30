package dad.web.bookteca.controladores;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.web.bookteca.clases.Libro;

@Controller
public class inicioController {
	@RequestMapping("/")
	public String inicio(Model model) {
		ArrayList<Libro> listaLibros=new ArrayList<>();
		Libro libro1=new Libro(1,"Caperucita Roja", "Sergio","Anaya",null );
		Libro libro2=new Libro(2,"Los Tres Cerditos", "Borja","El Mundo",null );
		Libro libro3=new Libro(3,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana",null );
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		model.addAttribute("visibleIniciarSesion", true);
		model.addAttribute("visibleCerrarSesion", false);
		model.addAttribute("listaLibros",listaLibros);
		return "index";
	}

}
