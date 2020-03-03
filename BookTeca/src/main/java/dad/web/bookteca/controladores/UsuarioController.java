package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
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
import dad.web.bookteca.servicios.UsuarioService;

@Controller
public class UsuarioController {

	private static final Logger LOGUEO = LoggerFactory.getLogger(UsuarioController.class);
	
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
	
	@Autowired
	private UsuarioService servicio;
	
	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(Model model, HttpServletRequest request) {
		//SecurityContextHolder.getContext().getAuthentication();
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token",token.getToken());
		return "iniciarSesion";
	}
	
	@RequestMapping("/sesionIniciada")
	public String sesionIniciada(Model model, HttpServletRequest request) {
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		InicioController.sesionNoIniciada = false;
		if(usuario.getAdministrador()) {
			model.addAttribute("usuarioAdmin",request.isUserInRole("ADMIN"));
			ArrayList<Libro> listaLibros=(ArrayList<Libro>) libros.findAll();
			model.addAttribute("listaLibros",listaLibros);
			ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
			model.addAttribute("listaRevistas",listaRevistas);
			ArrayList<SalaTrabajoGrupo> listaSTGs=(ArrayList<SalaTrabajoGrupo>) salasTrabajoGrupo.findAll();
			model.addAttribute("listaSTGs",listaSTGs);
			ArrayList<EquipoInformatico> listaEquipos=(ArrayList<EquipoInformatico>) equiposInformaticos.findAll();
			model.addAttribute("listaEquipos",listaEquipos);
		} else {
			model.addAttribute("usuario",request.isUserInRole("USER"));
			CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("tokenLibro",tokenLibro.getToken());
			model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
			CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("tokenRevista",tokenRevista.getToken());
			model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		}
		return "sesionIniciada";
	}
	
	@RequestMapping("/inicio")
	public String inicio(Model model, HttpSession sesionUsuario, HttpServletRequest request) {
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		if(!usuario.getAdministrador()) {
			model.addAttribute("usuario",request.isUserInRole("USER"));
			model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
			model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		} else {
			model.addAttribute("usuarioAdmin",request.isUserInRole("ADMIN"));
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

	@RequestMapping("/registro")
	public String registro(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token",token.getToken());
		return "registro";
	}
	@RequestMapping("/iniciarSesionTrasRegistro")
	public String iniciarSesionTrasRegistro(Model model, @RequestParam("nombreUsuario") String nombre, 
			@RequestParam("apellidosUsuario") String apellidos, @RequestParam String email,
			@RequestParam("contrasenya") String contraseña, HttpServletRequest request) {
		usuarios.save(new Usuario(nombre,apellidos,contraseña,email,false));
		model.addAttribute("email",email);
		model.addAttribute("contraseña",contraseña);
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token",token.getToken());
		return "iniciarSesionTrasRegistro";
	}

	@RequestMapping("/miPerfil")
	public String miPerfil(Model model,HttpSession sesionUsuario, HttpServletRequest request) {
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		if(!usuario.getAdministrador()) {
			model.addAttribute("nombre",usuario.getNombre());
			model.addAttribute("usuario",true);
			
			CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("tokenLibro",tokenLibro.getToken());
			ArrayList<Libro> listaLibros=libros.findByIdUsuario(usuario);
			boolean visibleTablaLibros=!listaLibros.isEmpty();
			model.addAttribute("visibleTablaLibros",visibleTablaLibros);
			model.addAttribute("listaLibros",listaLibros);
			
			CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("tokenRevista",tokenRevista.getToken());
			ArrayList<Revista> listaRevistas=revistas.findByIdUsuario(usuario);
			boolean visibleTablaRevistas=!listaRevistas.isEmpty();
			model.addAttribute("visibleTablaRevistas",visibleTablaRevistas);
			model.addAttribute("listaRevistas",listaRevistas);
			
			CsrfToken tokenSTG = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("tokenSTG",tokenSTG.getToken());
			SalaTrabajoGrupo STG=null;
			if(usuario.getSalaTrabajo()!=null)
				STG = salasTrabajoGrupo.findById(usuario.getSalaTrabajo().getId());
			model.addAttribute("visibleSTG",STG!=null);
			model.addAttribute("STG",STG);
			
			CsrfToken tokenEquipo = (CsrfToken) request.getAttribute("_csrf");
			model.addAttribute("tokenEquipo",tokenEquipo.getToken());
			EquipoInformatico equipo=null;
			if(usuario.getPuestoInformatico()!=null)
				equipo = equiposInformaticos.findById(usuario.getPuestoInformatico().getId());
			model.addAttribute("visibleEquipo",equipo!=null);
			model.addAttribute("equipo",equipo);
		} else {
			model.addAttribute("nombre",usuario.getNombre());
			model.addAttribute("usuarioAdmin",true);
		}
		return "miPerfil";
	}
	
	@RequestMapping("/editarPerfil")
	public String editarPerfil(Model model, HttpSession sesionUsuario , HttpServletRequest request) {
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		model.addAttribute("nombre",usuario.getNombre());
		model.addAttribute("apellidos",usuario.getApellidos());
		model.addAttribute("contraseña",usuario.getContrasenya());
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token",token.getToken());
		return "editarPerfil";
	}
	
	@RequestMapping("/perfilEditado")
	public String perfilEditado(Model model, HttpSession sesionUsuario, @RequestParam("nuevoNombreUsuario") String nombre, 
			@RequestParam("nuevosApellidosUsuario") String apellidos, @RequestParam("nuevaContrasenya") String contrasenya, 
			HttpServletRequest request) {
		Usuario usuarioEditado = usuarios.findByEmail(request.getUserPrincipal().getName());
		usuarioEditado.setNombre(nombre);
		usuarioEditado.setApellidos(apellidos);
		usuarioEditado.setContrasenya(contrasenya);
		usuarios.save(usuarioEditado);
		sesionUsuario.setAttribute("infoUsuario",usuarioEditado);
		model.addAttribute("nombre",usuarioEditado.getNombre());
		return "perfilEditado";
		
	}	
}
