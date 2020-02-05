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
		Libro libro1=new Libro(1,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro2=new Libro(2,"Los Tres Cerditos", "Borja","El Mundo","Infantil");
		Libro libro3=new Libro(3,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes" );
		Libro libro4=new Libro(4,"Constitucion Española","Indef","España","Institucional");
		Libro libro5=new Libro(5,"Derecho Mercantil Español","Cristina","AAA","Educativo");
		Libro libro6=new Libro(6,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro7=new Libro(7,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro8=new Libro(8,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes" );
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);
		listaLibros.add(libro6);
		listaLibros.add(libro7);
		listaLibros.add(libro8);
		ArrayList<Revista> listaRevistas=new ArrayList<>();
		Revista revista1=new Revista(1,"GQ", "Editorial GQ",123,"Actualidad" );
		Revista revista2=new Revista(2,"FHM", "Editorial FHM",35,"Erotica");
		Revista revista3=new Revista(3,"MasQueCoches", "Editorial MasQueCoches",77,"Automovil" );
		Revista revista4=new Revista(4,"AutoSport", "Editorial AutoSport",23,"Automovil" );
		Revista revista5=new Revista(5,"MuyInteresante", "Editorial MuyInteresante",456,"Intelectual" );
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
		model.addAttribute("generoLibroDestacado",libroEscogido.getGenero());
		model.addAttribute("numeroEjemplaresLibroDestacado",3); //luego se saca de la base de datos
		
		Revista revistaEscogida=listaRevistas.get(randomPick.nextInt(listaRevistas.size()));
		model.addAttribute("nombreRevistaDestacada",revistaEscogida.getNombre());
		model.addAttribute("editorialRevistaDestacada",revistaEscogida.getEditorial());
		model.addAttribute("fasciculoRevistaDestacada",revistaEscogida.getFasciculo());
		model.addAttribute("generoRevistaDestacada",revistaEscogida.getGenero());
		model.addAttribute("numeroEjemplaresRevistaDestacada",3); //luego se saca de la base de datos
		
		//NO HA INICIADO SESION
		model.addAttribute("visibleIniciarSesion", true);
		model.addAttribute("visibleCerrarSesion", false);
		
		return "index";
	}
	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(Model model) {
		return "iniciarSesionNuevo";
	}
	@RequestMapping("/sesionIniciada")
	public String sesionIniciada(Model model) {
		ArrayList<Libro> listaLibros=new ArrayList<>();
		Libro libro1=new Libro(1,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro2=new Libro(2,"Los Tres Cerditos", "Borja","El Mundo","Infantil");
		Libro libro3=new Libro(3,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes" );
		Libro libro4=new Libro(4,"Constitucion Española","Indef","España","Institucional");
		Libro libro5=new Libro(5,"Derecho Mercantil Español","Cristina","AAA","Educativo");
		Libro libro6=new Libro(6,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro7=new Libro(7,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro8=new Libro(8,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes" );
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);
		listaLibros.add(libro6);
		listaLibros.add(libro7);
		listaLibros.add(libro8);
		ArrayList<Revista> listaRevistas=new ArrayList<>();
		Revista revista1=new Revista(1,"GQ", "Editorial GQ",123,"Actualidad" );
		Revista revista2=new Revista(2,"FHM", "Editorial FHM",35,"Erotica");
		Revista revista3=new Revista(3,"MasQueCoches", "Editorial MasQueCoches",77,"Automovil" );
		Revista revista4=new Revista(4,"AutoSport", "Editorial AutoSport",23,"Automovil" );
		Revista revista5=new Revista(5,"MuyInteresante", "Editorial MuyInteresante",456,"Intelectual" );
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
		model.addAttribute("generoLibroDestacado",libroEscogido.getGenero());
		model.addAttribute("numeroEjemplaresLibroDestacado",3); //luego se saca de la base de datos
		
		Revista revistaEscogida=listaRevistas.get(randomPick.nextInt(listaRevistas.size()));
		model.addAttribute("nombreRevistaDestacada",revistaEscogida.getNombre());
		model.addAttribute("editorialRevistaDestacada",revistaEscogida.getEditorial());
		model.addAttribute("fasciculoRevistaDestacada",revistaEscogida.getFasciculo());
		model.addAttribute("generoRevistaDestacada",revistaEscogida.getGenero());
		model.addAttribute("numeroEjemplaresRevistaDestacada",3); //luego se saca de la base de datos
		return "sesionIniciada";
	}
	@RequestMapping("/buscadorLibros")
	public String buscadorLibros(Model model) {
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		model.addAttribute("visibleIniciarSesion",true);
		return "buscadorLibros";
		
	}
	@RequestMapping("/buscadorRevistas")
	public String buscadorRevista(Model model) {
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		model.addAttribute("visibleIniciarSesion",true);
		return "buscadorRevistas";
	}
	@RequestMapping("/reservaSalaTrabajoGrupo")
	public String reservaSalaTrabajoGrupo(Model model) {
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		model.addAttribute("visibleIniciarSesion",true);
		return "reservaSalaTrabajoGrupo";
	}
	@RequestMapping("/reservaEquipoInformatico")
	public String reservaEquipoInformatico(Model model) {
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		model.addAttribute("visibleIniciarSesion",true);
		return "reservaEquipoInformatico";
	}
	
	
	

}
