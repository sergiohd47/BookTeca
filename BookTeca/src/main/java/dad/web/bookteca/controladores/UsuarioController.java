package dad.web.bookteca.controladores;

import java.util.ArrayList;

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
	
	private boolean sesionNoIniciada = true;
	
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
		ArrayList<Libro>listaLibros=new ArrayList<>();
		listaLibros.addAll(libros.findByNombreOrAutorOrEditorialOrGenero(info,info,info,info));
		if(sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			model.addAttribute("listaLibrosBusqueda",listaLibros);
			model.addAttribute("visibleTabla",!listaLibros.isEmpty());
		} else {
			ArrayList<Libro> listaLibrosBusqueda=new ArrayList<>();
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				model.addAttribute("nombre",usuario.getNombre());
				for(Libro l : listaLibros) {
					if(l.isDisponible())
						listaLibrosBusqueda.add(l);
				}
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
		ArrayList<Revista> listaRevistas=new ArrayList<>();
		listaRevistas.addAll(revistas.findByNombreOrEditorialOrGenero(info,info,info));
		if(sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			model.addAttribute("listaRevistasBusqueda",listaRevistas);
			model.addAttribute("visibleTabla",!listaRevistas.isEmpty());
		} else {
			ArrayList<Revista> listaRevistasBusqueda=new ArrayList<>();
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				model.addAttribute("nombre",usuario.getNombre());
				for(Revista r : listaRevistas) {
					if(r.isDisponible())
						listaRevistasBusqueda.add(r);
				}
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
		sesionNoIniciada = false;
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
		String nombreUsuario=usuarioEditado.getNombre()+" "+usuarioEditado.getApellidos();
		model.addAttribute("nombre", nombreUsuario);
		return "perfilEditado";
		
	}
}
