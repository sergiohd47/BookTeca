package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Random;

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
		Libro libro1=new Libro(1,"Caperucita Roja", "Sergio","Anaya",5,"Infantil" );
		Libro libro2=new Libro(2,"Los Tres Cerditos", "Borja","El Mundo",2,"Infantil");
		Libro libro3=new Libro(3,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana",3,"Viajes" );
		Libro libro4=new Libro(4,"Constitucion Española","Indef","España",2,"Institucional");
		Libro libro5=new Libro(5,"Derecho Mercantil Español","Cristina","AAA",1,"Educativo");
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);
		ArrayList<Revista> listaRevistas=new ArrayList<>();
		Revista revista1=new Revista(1,"GQ", "Editorial GQ",123,10,"Actualidad" );
		Revista revista2=new Revista(2,"FHM", "Editorial FHM",35,2,"Erotica");
		Revista revista3=new Revista(3,"MasQueCoches", "Editorial MasQueCoches",77,8,"Automovil" );
		Revista revista4=new Revista(4,"AutoSport", "Editorial AutoSport",23,5,"Automovil" );
		Revista revista5=new Revista(5,"MuyInteresante", "Editorial MuyInteresante",456,15,"Intelectual" );
		listaRevistas.add(revista1);
		listaRevistas.add(revista2);
		listaRevistas.add(revista3);
		listaRevistas.add(revista4);
		listaRevistas.add(revista5);
		
		Random randomPick=new Random();
		Libro libroEscogido=listaLibros.get(randomPick.nextInt(listaLibros.size()));
		model.addAttribute("nombreLibroDestacado",libroEscogido.getNombre());
		model.addAttribute("autorLibroDestacado",libroEscogido.getAutor());
		model.addAttribute("editorialLibroDestacado",libroEscogido.getEditorial());
		model.addAttribute("numeroEjemplaresLibroDestacado",libroEscogido.getNumeroEjemplares());
		model.addAttribute("generoLibroDestacado",libroEscogido.getGenero());
		
		Revista revistaEscogida=listaRevistas.get(randomPick.nextInt(listaRevistas.size()));
		model.addAttribute("nombreRevistaDestacada",revistaEscogida.getNombre());
		model.addAttribute("editorialRevistaDestacada",revistaEscogida.getEditorial());
		model.addAttribute("fasciculoRevistaDestacada",revistaEscogida.getFasciculo());
		model.addAttribute("numeroEjemplaresRevistaDestacada",revistaEscogida.getNumeroEjemplares());
		model.addAttribute("generoRevistaDestacada",revistaEscogida.getGenero());
		
		//NO HA INICIADO SESION
		model.addAttribute("visibleIniciarSesion", true);
		model.addAttribute("visibleCerrarSesion", false);
		
		//SESION INICIADA
		//model.addAttribute("visibleIniciarSesion", false);
		//model.addAttribute("visibleCerrarSesion", true);
		
		
		return "index";
	}

}
