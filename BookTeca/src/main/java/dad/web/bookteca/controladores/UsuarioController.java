package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

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
import dad.web.bookteca.servicios.UsuarioService;

@Controller
public class UsuarioController {

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
	
	
	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(Model model) {
		return "iniciarSesion";
	}

	@RequestMapping("/sesionIniciada")
	public String sesionIniciada(Model model, @RequestParam("nombreUsuario") String email,@RequestParam String contrasenya, HttpSession usuarioSesion) {
		Usuario usuario=usuarios.findByEmailAndContrasenya(email,contrasenya);
		if(usuario==null)
			return iniciarSesion(model);
		usuarioSesion.setAttribute("infoUsuario",usuario);
		InicioController.sesionNoIniciada = false;
		if(!usuario.getAdministrador()) {
			model.addAttribute("usuario",true);
			model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
			model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
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
	
	@RequestMapping("/inicio")
	public String inicio(Model model, HttpSession sesionUsuario) {
		Usuario usuario = (Usuario) sesionUsuario.getAttribute("infoUsuario");
		InicioController.sesionNoIniciada = false;
		if(!usuario.getAdministrador()) {
			model.addAttribute("usuario",true);
			model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
			model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
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

	@RequestMapping("/registro")
	public String registro(Model model) {
		return "registro";
	}
	@RequestMapping("/iniciarSesionTrasRegistro")
	public String iniciarSesionTrasRegistro(Model model, @RequestParam("nombreUsuario") String nombre, 
			@RequestParam("apellidosUsuario") String apellidos, @RequestParam String email,
			@RequestParam("contrasenya") String contraseña) {
		usuarios.save(new Usuario(nombre,apellidos,contraseña,email,false));
		model.addAttribute("email",email);
		model.addAttribute("contraseña",contraseña);
		return "iniciarSesionTrasRegistro";
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
			SalaTrabajoGrupo STG=null;
			if (usuario.getSalaTrabajo()!=null) {
				STG = salasTrabajoGrupo.findById(usuario.getSalaTrabajo().getId());
			}
			boolean visibleTablaSTG=STG!=null;
			model.addAttribute("visibleTablaSTG",visibleTablaSTG);
			model.addAttribute("STG",STG);
			EquipoInformatico listaEquipos=null;
			if (usuario.getPuestoInformatico()!=null) {
				listaEquipos=equiposInformaticos.findById(usuario.getPuestoInformatico().getId());
			}
			boolean visibleTablaEquipos=listaEquipos!=null;
			model.addAttribute("visibleTablaEquipos",visibleTablaEquipos);
			model.addAttribute("listaEquipos",listaEquipos);
		} else {
			model.addAttribute("nombre",usuario.getNombre());
			model.addAttribute("usuarioAdmin",true);
		}
		return "miPerfil";
	}
	
	@RequestMapping("/editarPerfil")
	public String editarPerfil(Model model, HttpSession sesionUsuario ) {
		Usuario usuario = (Usuario) sesionUsuario.getAttribute("infoUsuario");
		model.addAttribute("nombre",usuario.getNombre());
		model.addAttribute("apellidos",usuario.getApellidos());
		model.addAttribute("contraseña",usuario.getContrasenya());
		return "editarPerfil";
	}
	
	@RequestMapping("/perfilEditado")
	public String perfilEditado(Model model, HttpSession sesionUsuario, @RequestParam("nuevoNombreUsuario") String nombre, 
			@RequestParam("nuevosApellidosUsuario") String apellidos, @RequestParam("nuevaContrasenya") String contrasenya) {
		Usuario usuarioEditado = (Usuario) sesionUsuario.getAttribute("infoUsuario");
		usuarioEditado.setNombre(nombre);
		usuarioEditado.setApellidos(apellidos);
		usuarioEditado.setContrasenya(contrasenya);
		usuarios.save(usuarioEditado);
		sesionUsuario.setAttribute("infoUsuario",usuarioEditado);
		//String nombreUsuario=usuarioEditado.getNombre()+" "+usuarioEditado.getApellidos();	
		model.addAttribute("nombre",usuarioEditado.getNombre());
		return "perfilEditado";
		
	}
	
	
	
	
}
