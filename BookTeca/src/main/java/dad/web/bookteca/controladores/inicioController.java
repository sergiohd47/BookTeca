package dad.web.bookteca.controladores;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.web.bookteca.clases.Libro;
import dad.web.bookteca.clases.Revista;

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
		ArrayList<Revista> listaRevistas=new ArrayList<>();
		Revista revista1=new Revista(1,"GQ", "Editorial GQ",123,null );
		Revista revista2=new Revista(2,"FHM", "Editorial FHM",35,null );
		Revista revista3=new Revista(3,"MasQueCoches", "Editorial MasQueCoches",77,null );
		listaRevistas.add(revista1);
		listaRevistas.add(revista2);
		listaRevistas.add(revista3);
		model.addAttribute("visibleIniciarSesion", true);
		model.addAttribute("visibleCerrarSesion", false);
		model.addAttribute("listaLibros",listaLibros);
		model.addAttribute("listaRevistas",listaRevistas);
		return "index";
	}

}
