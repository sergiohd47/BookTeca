package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.web.bookteca.clases.EquipoInformatico;
import dad.web.bookteca.clases.Libro;
import dad.web.bookteca.clases.Revista;
import dad.web.bookteca.clases.SalaTrabajoGrupo;
import dad.web.bookteca.clases.Usuario;
import dad.web.bookteca.basedatos.*;

@Controller
public class inicioController {

	protected final int NUMERO_RECURSOS_MAIN=3; //NUMERO DE LIBROS Y REVISTAS QUE SE MUESTRAN EN LA PANTALLA DE INICIO

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

	@PostConstruct
	public void init() {
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
		usuarios.save(new Usuario("Sergio","Hernandez Dominguez","HDsergii47","sergiohd47@gmail.com",true));
		usuarios.save(new Usuario("Daniel","Molina Ballesteros","Daany10","dmolinaballesteros@gmail.com",false));
	}

	@RequestMapping("/")
	public String inicio(Model model) {
		ArrayList<Libro> listaLibros=(ArrayList<Libro>) libros.findAll();
		Collections.shuffle(listaLibros);
		ArrayList<Libro> listaLibrosDestacados=new ArrayList<>();
		int i = 0;
		int j = 0;
		ArrayList<String> nombresLibros = new ArrayList<>(NUMERO_RECURSOS_MAIN);
		do {
			if(!nombresLibros.contains(listaLibros.get(j).getNombre())) {
				nombresLibros.add(listaLibros.get(j).getNombre());
				listaLibrosDestacados.add(listaLibros.get(j));
				j++;
				i++;
			} else
				j++;
		} while(i < NUMERO_RECURSOS_MAIN);
		/*for(int i=0;i<NUMERO_RECURSOS_MAIN;i++) {
			if(!listaLibrosDestacados.contains(listaLibros.get(i)))
				listaLibrosDestacados.add(listaLibros.get(i));
		}*/
		model.addAttribute("listaLibrosDestacados",listaLibrosDestacados);

		ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
		Collections.shuffle(listaRevistas);
		ArrayList<Revista> listaRevistasDestacadas=new ArrayList<>();
		ArrayList<String> nombresRevistas = new ArrayList<>(NUMERO_RECURSOS_MAIN);
		i = 0;
		j = 0;
		do {
			if(!nombresRevistas.contains(listaRevistas.get(j).getNombre())) {
				nombresRevistas.add(listaRevistas.get(j).getNombre());
				listaRevistasDestacadas.add(listaRevistas.get(j));
				j++;
				i++;
			} else
				j++;
		} while(i < NUMERO_RECURSOS_MAIN);
		/*for(int i=0;i<NUMERO_RECURSOS_MAIN;i++) {
			if(!listaRevistasDestacadas.contains(listaRevistas.get(i)))
				listaRevistasDestacadas.add(listaRevistas.get(i));
		}*/
		model.addAttribute("listaRevistasDestacadas",listaRevistasDestacadas);

		//NO HA INICIADO SESION
		model.addAttribute("visibleIniciarSesion", true);
		return "index";
	}

	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(Model model) {
		return "iniciarSesionNuevo";
	}

	@RequestMapping("/sesionIniciada")
	public String sesionIniciada(Model model, @RequestParam("nombreUsuario") String email, HttpSession usuarioSesion) {
		Usuario usuario=usuarios.findByEmail(email);
		if(usuario==null)
			return "iniciarSesionNuevo";
		usuarioSesion.setAttribute("infoUsuario", usuario);
		
		ArrayList<Libro> listaLibros=(ArrayList<Libro>) libros.findAll();
		ArrayList<Libro> listaLibrosDestacados=new ArrayList<>();
		Random randomPick=new Random();
		for(int i=0;i<NUMERO_RECURSOS_MAIN;i++)
			listaLibrosDestacados.add(listaLibros.get(randomPick.nextInt(listaLibros.size())));
		model.addAttribute("listaLibrosDestacados",listaLibrosDestacados);

		ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
		ArrayList<Revista> listaRevistasDestacadas=new ArrayList<>();
		for(int i=0;i<NUMERO_RECURSOS_MAIN;i++)
			listaRevistasDestacadas.add(listaRevistas.get(randomPick.nextInt(listaRevistas.size())));
		model.addAttribute("listaRevistasDestacadas",listaRevistasDestacadas);
		return "sesionIniciada";
	}

	@RequestMapping("/buscadorLibros")
	public String buscadorLibros(Model model, HttpSession usuarioSesion) {
		Usuario usuario=(Usuario) usuarioSesion.getAttribute("infoUsuario");
		ArrayList<Libro> listaLibrosBusqueda=new ArrayList<>();
		//listaLibrosBusqueda=(ArrayList<Libro>) libros.findAll();
		boolean visibleTabla=!listaLibrosBusqueda.isEmpty();
		model.addAttribute("listaLibrosBusqueda",listaLibrosBusqueda);
		model.addAttribute("visibleTabla",visibleTabla);
		model.addAttribute("visibleIniciarSesion",!usuarios.findAll().contains(usuario));
		return "buscadorLibros";

	}

	@RequestMapping("/buscadorRevistas")
	public String buscadorRevista(Model model, HttpSession usuarioSesion) {
		Usuario usuario=(Usuario)usuarioSesion.getAttribute("infoUsuario");
		ArrayList<Revista> listaRevistasBusqueda=new ArrayList<>();
		//listaRevistasBusqueda=(ArrayList<Revista>) revistas.findAll();
		boolean visibleTabla=!listaRevistasBusqueda.isEmpty();
		model.addAttribute("listaRevistasBusqueda",listaRevistasBusqueda);
		model.addAttribute("visibleTabla",visibleTabla);
		model.addAttribute("visibleIniciarSesion",!usuarios.findAll().contains(usuario));
		return "buscadorRevistas";
	}

	@RequestMapping("/reservaSalaTrabajoGrupo")
	public String reservaSalaTrabajoGrupo(Model model, HttpSession usuarioSesion) {
		Usuario usuario=(Usuario)usuarioSesion.getAttribute("infoUsuario");
		ArrayList<SalaTrabajoGrupo> listaSTG=new ArrayList<>();
		//listaSTG=(ArrayList<SalaTrabajoGrupo>) salasTrabajoGrupo.findAll();
		boolean visibleTabla=!listaSTG.isEmpty();
		model.addAttribute("visibleTabla",visibleTabla);
		model.addAttribute("listaSTG",listaSTG);
		model.addAttribute("visibleIniciarSesion",!usuarios.findAll().contains(usuario));
		System.out.println(!usuarios.findAll().contains(usuario));
		return "reservaSalaTrabajoGrupo";
	}

	@RequestMapping("/reservaEquipoInformatico")
	public String reservaEquipoInformatico(Model model, HttpSession usuarioSesion) {
		Usuario usuario=(Usuario)usuarioSesion.getAttribute("infoUsuario");
		ArrayList<EquipoInformatico> listaEquipo=new ArrayList<>();
		//listaEquipo=(ArrayList<EquipoInformatico>) equiposInformaticos.findAll();
		boolean visibleTabla=!listaEquipo.isEmpty();
		model.addAttribute("visibleTabla",visibleTabla);
		model.addAttribute("listaEquipo",listaEquipo);
		model.addAttribute("visibleIniciarSesion",!usuarios.findAll().contains(usuario));
		return "reservaEquipoInformatico";
	}

	@RequestMapping("/registro")
	public String registro(Model model, Usuario usuario) {
		usuarios.save(usuario);
		return "registro";
	}

	@RequestMapping("/miPerfil")
	public String miPerfil(Model model, HttpSession sesionUsuario) {
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if(!usuario.getAdministrador()) {
			model.addAttribute("nombre",usuario.getNombre());
			model.addAttribute("usuario",true);
			ArrayList<Libro> listaLibros=libros.findByIdUsuario(usuario);
			model.addAttribute("listaLibros",listaLibros);
			ArrayList<Revista> listaRevistas=revistas.findByIdUsuario(usuario);
			model.addAttribute("listaRevistas",listaRevistas);
			SalaTrabajoGrupo STG=salasTrabajoGrupo.findByIdUsuario(usuario);
			model.addAttribute("STG",STG);
			ArrayList<EquipoInformatico> listaEquipos=equiposInformaticos.findByIdUsuario(usuario);
			model.addAttribute("listaEquipos",listaEquipos);
		} else {
			model.addAttribute("nombre",usuario.getNombre());
			model.addAttribute("usuarioAdmin",true);
		}
		return "miPerfil";
	}

	@RequestMapping("/editarPerfil")
	public String editarPerfil(Model model, Usuario usuario) {
		usuarios.save(usuario);
		return "editarPerfil";
	}

	@RequestMapping("/añadirRevista")
	public String añadirRevista(Model model, Revista revista) {
		revistas.save(revista);
		return "añadirRevista";
	}

	@RequestMapping("/añadirSalaTrabajoGrupo")
	public String añadirSala(Model model, SalaTrabajoGrupo sala) {
		salasTrabajoGrupo.save(sala);
		return "añadirSalaTrabajoGrupo";
	}

	@RequestMapping("/añadirEquipoInformatico")
	public String añadirEquipoInformatico(Model model, EquipoInformatico equipo) {
		equiposInformaticos.save(equipo);
		return "añadirEquipoInformatico";
	}

	@RequestMapping("/añadirLibro")
	public String añadirLibro(Model model, Libro libro) {
		libros.save(libro);
		return "añadirLibro";
	}

	@RequestMapping("/administrarUsuarios")
	public String administrarUsuarios(Model model, @RequestParam("emailUsuario") String emailNuevoAdmin) {
		Usuario usuario=usuarios.findByEmail(emailNuevoAdmin);
		usuario.setAdministrador(true);
		return "administrarUsuarios";
	}

}
