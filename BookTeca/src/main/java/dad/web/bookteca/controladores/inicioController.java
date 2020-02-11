package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		ArrayList<Libro> listaLibrosDestacados=new ArrayList<>();
		Random randomPick=new Random();
		for(int i=0;i<NUMERO_RECURSOS_MAIN;i++) {
			listaLibrosDestacados.add(listaLibros.get(randomPick.nextInt(listaLibros.size())));
		}
		model.addAttribute("listaLibrosDestacados",listaLibrosDestacados);
		
		ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
		ArrayList<Revista> listaRevistasDestacadas=new ArrayList<>();
		for(int i=0;i<NUMERO_RECURSOS_MAIN;i++) {
			listaRevistasDestacadas.add(listaRevistas.get(randomPick.nextInt(listaRevistas.size())));
		}
		model.addAttribute("listaRevistasDestacadas",listaRevistasDestacadas);
		
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
		ArrayList<Libro> listaLibros=(ArrayList<Libro>) libros.findAll();
		ArrayList<Libro> listaLibrosDestacados=new ArrayList<>();
		Random randomPick=new Random();
		for(int i=0;i<NUMERO_RECURSOS_MAIN;i++) {
			listaLibrosDestacados.add(listaLibros.get(randomPick.nextInt(listaLibros.size())));
		}
		model.addAttribute("listaLibrosDestacados",listaLibrosDestacados);
		
		ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
		ArrayList<Revista> listaRevistasDestacadas=new ArrayList<>();
		for(int i=0;i<NUMERO_RECURSOS_MAIN;i++) {
			listaRevistasDestacadas.add(listaRevistas.get(randomPick.nextInt(listaRevistas.size())));
		}
		model.addAttribute("listaRevistasDestacadas",listaRevistasDestacadas);
		return "sesionIniciada";
	}
	
	@RequestMapping("/buscadorLibros")
	public String buscadorLibros(Model model) {
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		ArrayList<Libro> listaLibrosBusqueda=new ArrayList<>();
		//listaLibrosBusqueda=(ArrayList<Libro>) libros.findAll();
		boolean visibleTabla=!listaLibrosBusqueda.isEmpty();
		model.addAttribute("listaLibrosBusqueda",listaLibrosBusqueda);
		model.addAttribute("visibleTabla",visibleTabla);
		model.addAttribute("visibleIniciarSesion",true);
		return "buscadorLibros";
		
	}
	
	@RequestMapping("/buscadorRevistas")
	public String buscadorRevista(Model model) {
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		ArrayList<Revista> listaRevistasBusqueda=new ArrayList<>();
		//listaRevistasBusqueda=(ArrayList<Revista>) revistas.findAll();
		boolean visibleTabla=!listaRevistasBusqueda.isEmpty();
		model.addAttribute("listaRevistasBusqueda",listaRevistasBusqueda);
		model.addAttribute("visibleTabla",visibleTabla);
		model.addAttribute("visibleIniciarSesion",true);
		return "buscadorRevistas";
	}
	
	@RequestMapping("/reservaSalaTrabajoGrupo")
	public String reservaSalaTrabajoGrupo(Model model) {
		ArrayList<SalaTrabajoGrupo> listaSTG=new ArrayList<>();
		//listaSTG=(ArrayList<SalaTrabajoGrupo>) salasTrabajoGrupo.findAll();
		boolean visibleTabla=!listaSTG.isEmpty();
		model.addAttribute("visibleTabla",visibleTabla);
		model.addAttribute("listaSTG",listaSTG);
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		model.addAttribute("visibleIniciarSesion",true);
		return "reservaSalaTrabajoGrupo";
	}
	
	@RequestMapping("/reservaEquipoInformatico")
	public String reservaEquipoInformatico(Model model) {
		ArrayList<EquipoInformatico> listaEquipo=new ArrayList<>();
		//listaEquipo=(ArrayList<EquipoInformatico>) equiposInformaticos.findAll();
		boolean visibleTabla=!listaEquipo.isEmpty();
		model.addAttribute("visibleTabla",visibleTabla);
		model.addAttribute("listaEquipo",listaEquipo);
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		model.addAttribute("visibleIniciarSesion",true);
		return "reservaEquipoInformatico";
	}
	
	@RequestMapping("/registro")
	public String registro(Model model, Usuario usuario) {
		usuarios.save(usuario);
		return "registro";
	}
	
	@RequestMapping("/miPerfil")
	public String miPerfil(Model model) {
		Random randomPick=new Random();
		ArrayList<Libro> listaLibros=(ArrayList<Libro>) libros.findAll();
		Libro libroEscogido=listaLibros.get(randomPick.nextInt(listaLibros.size()));
		model.addAttribute("nombreLibro", libroEscogido.getNombre());
		model.addAttribute("autorLibro", libroEscogido.getAutor());
		model.addAttribute("editorialLibro", libroEscogido.getEditorial());
		model.addAttribute("generoLibro", libroEscogido.getGenero());
		model.addAttribute("diaFinReservaLibro", "3.03.2020");
		
		ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
		Revista revistaEscogida=listaRevistas.get(randomPick.nextInt(listaRevistas.size()));
		model.addAttribute("nombreRevista", revistaEscogida.getNombre());
		model.addAttribute("editorialRevista", revistaEscogida.getEditorial());
		model.addAttribute("fasciculoRevista", revistaEscogida.getFasciculo());
		model.addAttribute("generoRevista", revistaEscogida.getGenero());
		model.addAttribute("diaFinReservaRevista", "18.02.2020");
		
		ArrayList<SalaTrabajoGrupo> listaSTG=(ArrayList<SalaTrabajoGrupo>) salasTrabajoGrupo.findAll();
		SalaTrabajoGrupo salaEscogida=listaSTG.get(randomPick.nextInt(listaSTG.size()));
		model.addAttribute("capacidadSala", salaEscogida.getCapacidad());
		model.addAttribute("localizacionSala", salaEscogida.getLocalizacion());
		if(salaEscogida.isCompartida()) {
			model.addAttribute("compartidaSala", "Si");
		}else {
			model.addAttribute("compartidaSala", "No");
		}
		model.addAttribute("diaFinReservaSala", "5.02.2020");
		
		ArrayList<EquipoInformatico> listaEquipo=(ArrayList<EquipoInformatico>) equiposInformaticos.findAll();
		EquipoInformatico equipoEscogido=listaEquipo.get(randomPick.nextInt(listaEquipo.size()));
		model.addAttribute("sistemaOperativoEquipo", equipoEscogido.getSistemaOperativo());
		model.addAttribute("localizacionEquipo", equipoEscogido.getLocalizacion());
		model.addAttribute("diaFinReservaEquipo", "8.02.2020");
		
		//EL USUARIO ES ADMINISTRADOR
		model.addAttribute("usuarioAdmin", true);
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
	public String administrarUsuarios(Model model) {
		return "administrarUsuarios";
	}

}
