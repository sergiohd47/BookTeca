package dad.web.bookteca.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.web.bookteca.basedatos.EquipoInformaticoRepository;
import dad.web.bookteca.basedatos.LibroRepository;
import dad.web.bookteca.basedatos.RevistaRepository;
import dad.web.bookteca.basedatos.SalaTrabajoGrupoRepository;
import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.EquipoInformatico;
import dad.web.bookteca.clases.Libro;
import dad.web.bookteca.clases.Revista;
import dad.web.bookteca.clases.SalaTrabajoGrupo;
import dad.web.bookteca.clases.Usuario;

@Controller
public class AdministradorController {
		
	@Autowired
	private LibroRepository libros;
	
	@Autowired
	private RevistaRepository revistas;
	
	@Autowired
	private SalaTrabajoGrupoRepository salasTrabajoGrupo;
	
	@Autowired
	private EquipoInformaticoRepository equiposInformaticos;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	private Usuario usuario;
	
	@RequestMapping("/añadirLibro")
	public String añadirLibro(Model model,HttpSession sesionUsuario, HttpServletRequest servlet) {
		sesionUsuario.getAttribute("infoUsuario");
		return "añadirLibro";
	}
	
	@RequestMapping("/libroAñadido")
	public String libroAñadido(Model model, @RequestParam("nombreLibro") String nombre, @RequestParam String autor, 
			@RequestParam("editorialLibro") String editorial, @RequestParam("generoLibro") String genero, 
			HttpSession sesionUsuario, HttpServletRequest servlet) {
		Usuario usuario = (Usuario) sesionUsuario.getAttribute("infoUsuario");
		model.addAttribute("nombre",usuario.getNombre());
		libros.save(new Libro(nombre,autor,editorial,genero));
		return "recursoAñadido";
	}
	
	@RequestMapping("/añadirRevista")
	public String añadirRevista(Model model,HttpSession sesionUsuario, HttpServletRequest servlet) {
		sesionUsuario.getAttribute("infoUsuario");
		return "añadirRevista";
	}
	
	@RequestMapping("/revistaAñadida")
	public String revistaAñadida(Model model, @RequestParam("nombreRevista") String nombre, 
			@RequestParam("editorialRevista") String editorial, @RequestParam int fasciculo, 
			@RequestParam("generoRevista") String genero, HttpSession sesionUsuario, HttpServletRequest servlet) {
		Usuario usuario = (Usuario) sesionUsuario.getAttribute("infoUsuario");
		model.addAttribute("nombre",usuario.getNombre());
		revistas.save(new Revista(nombre,editorial,fasciculo,genero));
		return "recursoAñadido";
	}
	
	@RequestMapping("/añadirSalaTrabajoGrupo")
	public String añadirSalaTrabajoGrupo(Model model, HttpSession sesionUsuario, HttpServletRequest servlet) {
		sesionUsuario.getAttribute("infoUsuario");
		return "añadirSalaTrabajoGrupo";
	}
	
	@RequestMapping("/salaAñadida")
	public String salaTrabajoGrupoAñadida(Model model, @RequestParam("capacidadSala") int capacidad, 
			@RequestParam("localizacionSala") String localizacion, @RequestParam(defaultValue = "false") boolean compartida, 
			HttpSession sesionUsuario, HttpServletRequest servlet) {
		Usuario usuario = (Usuario) sesionUsuario.getAttribute("infoUsuario");
		model.addAttribute("nombre",usuario.getNombre());
		salasTrabajoGrupo.save(new SalaTrabajoGrupo(capacidad,localizacion,compartida));
		return "recursoAñadido";
	}

	@RequestMapping("/añadirEquipoInformatico")
	public String añadirEquipo(Model model, HttpSession sesionUsuario, HttpServletRequest servlet) {
		sesionUsuario.getAttribute("infoUsuario");
		return "añadirEquipoInformatico";
	}
	
	@RequestMapping("/equipoAñadido")
	public String equipoAñadido(Model model, @RequestParam("soEquipo") String so, 
			@RequestParam("localizacionEquipo") String localizacion, HttpSession sesionUsuario, HttpServletRequest servlet) {
		Usuario usuario = (Usuario) sesionUsuario.getAttribute("infoUsuario");
		model.addAttribute("nombre",usuario.getNombre());
		equiposInformaticos.save(new EquipoInformatico(so,localizacion));
		return "recursoAñadido";
	}

	@RequestMapping("/administrarUsuarios")
	public String administrarUsuarios(Model model, HttpSession sesionUsuario, HttpServletRequest servlet) {
		sesionUsuario.getAttribute("infoUsuario");
		return "administrarUsuarios";
	}
	
	@RequestMapping("/busquedaUsuarios")
	public String busquedaUsuarios(Model model, @RequestParam("emailUsuario") String emailNuevoAdmin, 
			HttpSession sesionUsuario, HttpServletRequest servlet) {
		sesionUsuario.getAttribute("infoUsuario");
		usuario=usuarios.findByEmail(emailNuevoAdmin);
		model.addAttribute("usuario",usuario);
		if(usuario == null)
			model.addAttribute("visibleTabla",false);
		else {
			model.addAttribute("visibleTabla",true);
			if(usuario.getAdministrador())
				model.addAttribute("usuarioAdmin",true);
			else
				model.addAttribute("usuarioNoAdmin",true);
		}
		return "busquedaUsuarios";
	}
	
	@RequestMapping("/usuarioAdministrado")
	public String usuarioAdministrado(Model model, HttpSession sesionUsuario, @RequestParam String emailUsuario, HttpServletRequest servlet) {
		Usuario admin =(Usuario) sesionUsuario.getAttribute("infoUsuario");
		model.addAttribute("nombre",admin.getNombre());
		usuario = usuarios.findByEmail(emailUsuario);
		if(!usuario.getAdministrador())
			usuario.setAdministrador(true);
		usuarios.save(usuario);
		return "recursoAñadido";
	}	
}
