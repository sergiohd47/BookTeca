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

	private ArrayList<Libro> listaLibrosDestacados;
	private ArrayList<Revista> listaRevistasDestacadas;
	private boolean sesionNoIniciada = true;

	@PostConstruct
	public void init() {
		
		/*//LIBROS
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
		usuarios.save(new Usuario("Daniel","Molina Ballesteros","Daany10","dmolinaballesteros@gmail.com",false));*/
		
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
		do {
			if(!nombresLibros.contains(listaLibros.get(j).getNombre())) {
				nombresLibros.add(listaLibros.get(j).getNombre());
				listaLibrosDestacados.add(listaLibros.get(j));
				j++;
				i++;
			} else
				j++;
		} while(i < NUMERO_RECURSOS_MAIN);
		model.addAttribute("listaLibrosDestacados",listaLibrosDestacados);
		ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
		Collections.shuffle(listaRevistas);
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
		model.addAttribute("listaRevistasDestacadas",listaRevistasDestacadas);

		//NO HA INICIADO SESION
		model.addAttribute("visibleIniciarSesion",true);
		return "index";
	}

	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(Model model) {
		return "iniciarSesionNuevo";
	}

	@RequestMapping("/sesionIniciada")
	public String sesionIniciada(Model model, @RequestParam("nombreUsuario") String email,@RequestParam String contrasenya, HttpSession usuarioSesion) {
		Usuario usuario=usuarios.findByEmailAndContrasenya(email,contrasenya);
		if(usuario==null)
			return iniciarSesion(model);
		usuarioSesion.setAttribute("infoUsuario",usuario);
		sesionNoIniciada = false;
		if(!usuario.getAdministrador()) {
			model.addAttribute("usuario",true);
			model.addAttribute("listaLibrosDestacados",listaLibrosDestacados);
			model.addAttribute("listaRevistasDestacadas",listaRevistasDestacadas);
		} else {
			model.addAttribute("usuarioAdmin",true);
			ArrayList<Libro> listaLibros=(ArrayList<Libro>) libros.findAll();
			model.addAttribute("listaLibros",listaLibros);
			ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
			model.addAttribute("listaRevistas",listaRevistas);
			ArrayList<SalaTrabajoGrupo> listaSTGs=(ArrayList<SalaTrabajoGrupo>) salasTrabajoGrupo.findAll();
			model.addAttribute("listaSTGs",listaSTGs);
			ArrayList<EquipoInformatico> listaEquipos=(ArrayList<EquipoInformatico>) equiposInformaticos.findAll();
			model.addAttribute("listaEquipos",listaEquipos);
		}
		return "sesionIniciada";
	}

	@RequestMapping("/buscadorLibros")
	public String buscadorLibros(Model model, HttpSession usuarioSesion) {
		Usuario usuario=(Usuario) usuarioSesion.getAttribute("infoUsuario");
		if(sesionNoIniciada)
			model.addAttribute("visibleIniciarSesion",true);
		else {
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador())
				model.addAttribute("nombre",usuario.getNombre());
		}
		return "buscadorLibros";
	}

	@RequestMapping("/busquedaLibros")
	public String busquedaLibros(Model model, HttpSession usuarioSesion, @RequestParam("palabraClaveLibro") String info) {
		Usuario usuario=(Usuario) usuarioSesion.getAttribute("infoUsuario");
		ArrayList<Libro> listaLibrosBusqueda=new ArrayList<>();
		listaLibrosBusqueda.addAll(libros.findByNombreOrAutorOrEditorialOrGenero(info,info,info,info));
		if(sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			model.addAttribute("listaLibrosBusqueda",listaLibrosBusqueda);
			model.addAttribute("visibleTabla",!listaLibrosBusqueda.isEmpty());
		} else {
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				model.addAttribute("nombre",usuario.getNombre());
				model.addAttribute("listaLibrosBusqueda",listaLibrosBusqueda);
				model.addAttribute("visibleTabla",!listaLibrosBusqueda.isEmpty());
			}
		}
		return "busquedaLibros";
	}


	@RequestMapping("/buscadorRevistas")
	public String buscadorRevista(Model model, HttpSession usuarioSesion) {
		Usuario usuario=(Usuario)usuarioSesion.getAttribute("infoUsuario");
		ArrayList<Revista> listaRevistasBusqueda=new ArrayList<>();
		if(sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			model.addAttribute("listaRevistasBusqueda",listaRevistasBusqueda);
			model.addAttribute("visibleTabla",!listaRevistasBusqueda.isEmpty());
		} else {
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				model.addAttribute("nombre",usuario.getNombre());
				model.addAttribute("listaRevistasBusqueda",listaRevistasBusqueda);
				model.addAttribute("visibleTabla",!listaRevistasBusqueda.isEmpty());
			}
		}
		return "buscadorRevistas";
	}
	@RequestMapping("/busquedaRevistas")
	public String busquedaRevistas(Model model, HttpSession usuarioSesion, @RequestParam("palabraClaveRevista") String info) {
		Usuario usuario=(Usuario) usuarioSesion.getAttribute("infoUsuario");
		ArrayList<Revista> listaRevistasBusqueda=new ArrayList<>();
		listaRevistasBusqueda.addAll(revistas.findByNombreOrEditorialOrGenero(info,info,info));
		if(sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			model.addAttribute("listaRevistasBusqueda",listaRevistasBusqueda);
			model.addAttribute("visibleTabla",!listaRevistasBusqueda.isEmpty());
		} else {
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				model.addAttribute("nombre",usuario.getNombre());
				model.addAttribute("listaRevistasBusqueda",listaRevistasBusqueda);
				model.addAttribute("visibleTabla",!listaRevistasBusqueda.isEmpty());
			}
		}
		return "busquedaRevistas";
	}

	@RequestMapping("/reservaSalaTrabajoGrupo")
	public String reservaSalaTrabajoGrupo(Model model, HttpSession usuarioSesion) {
		Usuario usuario=(Usuario)usuarioSesion.getAttribute("infoUsuario");
		ArrayList<SalaTrabajoGrupo> listaSTG=new ArrayList<>();
		if(sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			listaSTG=(ArrayList<SalaTrabajoGrupo>) salasTrabajoGrupo.findByDisponible(true);
			boolean visibleTabla=!listaSTG.isEmpty();
			model.addAttribute("visibleTabla",visibleTabla);
			model.addAttribute("listaSTG",listaSTG);
			//model.addAttribute("visibleIniciarSesion",!usuarios.findAll().contains(usuario));
		} else {
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				listaSTG=(ArrayList<SalaTrabajoGrupo>) salasTrabajoGrupo.findByDisponible(true);
				model.addAttribute("visibleTabla",!listaSTG.isEmpty());
				model.addAttribute("listaSTG",listaSTG);
			}
		}
		return "reservaSalaTrabajoGrupo";
	}

	@RequestMapping("/reservaEquipoInformatico")
	public String reservaEquipoInformatico(Model model, HttpSession usuarioSesion) {
		ArrayList<EquipoInformatico> listaEquipo=new ArrayList<>();
		Usuario usuario=(Usuario)usuarioSesion.getAttribute("infoUsuario");
		if(sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			listaEquipo=(ArrayList<EquipoInformatico>) equiposInformaticos.findByDisponible(true);
			model.addAttribute("visibleTabla",!listaEquipo.isEmpty());
			model.addAttribute("listaEquipo",listaEquipo);
		} else {
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				listaEquipo=(ArrayList<EquipoInformatico>) equiposInformaticos.findByDisponible(true);
				model.addAttribute("visibleTabla",!listaEquipo.isEmpty());
				model.addAttribute("listaEquipo",listaEquipo);
			}
		}
		return "reservaEquipoInformatico";
	}

	@RequestMapping("/registro")
	public String registro(Model model, Usuario usuario) {
		usuarios.save(usuario);
		return "registro";
	}

	@RequestMapping("/miPerfil")
	public String miPerfil(Model model,HttpSession sesionUsuario) {
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if(!usuario.getAdministrador()) {
			model.addAttribute("nombre",usuario.getNombre());
			model.addAttribute("usuario",true);
			ArrayList<Libro> listaLibros=libros.findByIdUsuario(usuario);
			boolean visibleTablaLibros=!listaLibros.isEmpty();
			model.addAttribute("visibleTablaLibros",visibleTablaLibros);
			model.addAttribute("listaLibros",listaLibros);
			ArrayList<Revista> listaRevistas=revistas.findByIdUsuario(usuario);
			boolean visibleTablaRevistas=!listaRevistas.isEmpty();
			model.addAttribute("visibleTablaRevistas",visibleTablaRevistas);
			model.addAttribute("listaRevistas",listaRevistas);
			SalaTrabajoGrupo STG=salasTrabajoGrupo.findByIdUsuario(usuario);
			boolean visibleTablaSTG=STG!=null;
			model.addAttribute("visibleTablaSTG",visibleTablaSTG);
			model.addAttribute("STG",STG);
			ArrayList<EquipoInformatico> listaEquipos=equiposInformaticos.findByIdUsuario(usuario);
			boolean visibleTablaEquipos=!listaEquipos.isEmpty();
			model.addAttribute("visibleTablaEquipos",visibleTablaEquipos);
			model.addAttribute("listaEquipos",listaEquipos);
		} else {
			model.addAttribute("nombre",usuario.getNombre());
			model.addAttribute("usuarioAdmin",true);
		}
		return "miPerfil";
	}
	@RequestMapping("/perfilEditado")
	public String perfilEditado(Model model, HttpSession sesionUsuario, @RequestParam("nuevoNombreUsuario") String nombre, 
			@RequestParam("nuevoApellidoUsuario") String apellidos, @RequestParam("nuevaContrasenya") String contrasenya) {
		Usuario usuarioEditado = (Usuario) sesionUsuario.getAttribute("infoUsuario");
		usuarioEditado.setNombre(nombre);
		usuarioEditado.setApellidos(apellidos);
		usuarioEditado.setContrasenya(contrasenya);
		usuarios.save(usuarioEditado);
		sesionUsuario.setAttribute("infoUsuario",usuarioEditado);
		String nombreUsuario=usuarioEditado.getNombre()+" "+usuarioEditado.getApellidos();
		model.addAttribute("nombre", nombreUsuario);
		return "perfilEditado";
		
	}

	@RequestMapping("/editarPerfil")
	public String editarPerfil(Model model, HttpSession sesionUsuario ) {
		return "editarPerfil";
	}

}
