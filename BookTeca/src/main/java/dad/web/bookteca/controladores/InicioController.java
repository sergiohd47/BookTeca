package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dad.web.bookteca.clases.EquipoInformatico;
import dad.web.bookteca.clases.Libro;
import dad.web.bookteca.clases.Revista;
import dad.web.bookteca.clases.SalaTrabajoGrupo;
import dad.web.bookteca.clases.Usuario;
import dad.web.bookteca.basedatos.*;

@Controller
public class InicioController {

	public static final int NUMERO_RECURSOS_MAIN=3; //NUMERO DE LIBROS Y REVISTAS QUE SE MUESTRAN EN LA PANTALLA DE INICIO

	@Autowired
	private EquipoInformaticoRepository equiposInformaticos;

	@Autowired
	private LibroRepository libros;

	@Autowired
	private RevistaRepository revistas;

	@Autowired
	private SalaTrabajoGrupoRepository salasTrabajoGrupo;

	@Autowired
	private UsuarioRepository usuarios;

	public static ArrayList<Libro> listaLibrosDestacados;
	public static ArrayList<Revista> listaRevistasDestacadas;
	public static boolean sesionNoIniciada;

	@PostConstruct
	public void init() {
		/*
		//LIBROS
		libros.save(new Libro("Caperucita Roja", "Sergio","Anaya","Infantil"));
		libros.save(new Libro("Los Tres Cerditos", "Borja","El Mundo","Infantil"));
		libros.save(new Libro("La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes"));
		libros.save(new Libro("Constitucion Española","Indef","España","Institucional"));
		libros.save(new Libro("Derecho Mercantil Español","Cristina","AAA","Educativo"));
		libros.save(new Libro("Caperucita Roja", "Sergio","Anaya","Infantil"));
		libros.save(new Libro("Caperucita Roja", "Sergio","Anaya","Infantil"));
		libros.save(new Libro("La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes"));

		//REVISTAS
		revistas.save(new Revista("GQ", "Editorial GQ",123,"Actualidad"));
		revistas.save(new Revista("FHM", "Editorial FHM",35,"Erotica"));
		revistas.save(new Revista("MasQueCoches", "Editorial MasQueCoches",77,"Automovil"));
		revistas.save(new Revista("AutoSport", "Editorial AutoSport",23,"Automovil"));
		revistas.save(new Revista("MuyInteresante", "Editorial MuyInteresante",456,"Intelectual"));

		//SALAS
		salasTrabajoGrupo.save(new SalaTrabajoGrupo(16,"Planta 1",true));
		salasTrabajoGrupo.save(new SalaTrabajoGrupo(3,"Planta 0",false));
		salasTrabajoGrupo.save(new SalaTrabajoGrupo(10,"Planta 2",true));
		salasTrabajoGrupo.save(new SalaTrabajoGrupo(5,"Planta 0",false));

		//EQUIPO INFORMATICO
		equiposInformaticos.save(new EquipoInformatico("MacOSX","Equipo 45"));
		equiposInformaticos.save(new EquipoInformatico("Linux","Equipo 32"));
		equiposInformaticos.save(new EquipoInformatico("Windows","Equipo 2"));
		equiposInformaticos.save(new EquipoInformatico("MacOSX","Equipo 1"));

		//USUARIOS
		usuarios.save(new Usuario("Borja","Martin Alonso","G07martin","bormaral13@gmail.com",false));
		usuarios.save(new Usuario("Sergio","Hernandez Dominguez","Pass1","sergiohd47@gmail.com",true));
		usuarios.save(new Usuario("Daniel","Molina Ballesteros","Daany10","dmolinaballesteros@gmail.com",false));
		*/
	}

	@RequestMapping("/")
	public String inicio(Model model) {
		listaLibrosDestacados=new ArrayList<>();
		listaRevistasDestacadas=new ArrayList<>();
		ArrayList<Libro> listaLibros=(ArrayList<Libro>) libros.findAll();
		Collections.shuffle(listaLibros);
		int i = 0;
		int j = 0;
		ArrayList<String> nombresLibros = new ArrayList<>(NUMERO_RECURSOS_MAIN);
		while((i < NUMERO_RECURSOS_MAIN)&&(j<listaLibros.size())){
			if(!nombresLibros.contains(listaLibros.get(j).getNombre()) && (listaLibros.get(j).isDisponible())) {
				nombresLibros.add(listaLibros.get(j).getNombre());
				listaLibrosDestacados.add(listaLibros.get(j));
				if(listaLibrosDestacados.size()==NUMERO_RECURSOS_MAIN) {
					break;
				}
				j++;
				i++;
			} else
				j++;
		}
		model.addAttribute("listaLibrosDestacados",listaLibrosDestacados);
		ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
		Collections.shuffle(listaRevistas);
		ArrayList<String> nombresRevistas = new ArrayList<>(NUMERO_RECURSOS_MAIN);
		i = 0;
		j = 0;
		while((i < NUMERO_RECURSOS_MAIN)&&(j<listaRevistas.size())){
			if(!nombresRevistas.contains(listaRevistas.get(j).getNombre()) && (listaRevistas.get(j).isDisponible())) {
				nombresRevistas.add(listaRevistas.get(j).getNombre());
				listaRevistasDestacadas.add(listaRevistas.get(j));
				if(listaRevistasDestacadas.size()==NUMERO_RECURSOS_MAIN) {
					break;
				}
				j++;
				i++;
			} else
				j++;
		} 
		model.addAttribute("listaRevistasDestacadas",listaRevistasDestacadas);
		sesionNoIniciada = true;
		model.addAttribute("visibleIniciarSesion",true);
		return "index";
	}

}
