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
	
	@Autowired
	private UsuarioService serviciosUsuario;
	
	@RequestMapping("/buscadorLibros")
	public String buscadorLibros(Model model, HttpSession usuarioSesion) {
		Usuario usuario=(Usuario) usuarioSesion.getAttribute("infoUsuario");
		if(InicioController.sesionNoIniciada)
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
		if(InicioController.sesionNoIniciada) {
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
		if(InicioController.sesionNoIniciada) {
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
		if(InicioController.sesionNoIniciada) {
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
		if(InicioController.sesionNoIniciada) {
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
		if(InicioController.sesionNoIniciada) {
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
	/*
	@RequestMapping("/libroReservado")//reservarLibros
	public String libroReservado(Model model, HttpSession sesionUsuario,  @RequestParam long idLibro) {
		//Optional<Libro> oLibro = libros.findById(idLibro);
		Libro libro = libros.findById(idLibro);
		//if (oLibro.get() != null) {
		//	libro = oLibro.get();
		//	return "";//buscadorLibros
		//}
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		boolean reservado = serviciosUsuario.reservarLibro(usuario,libro);
		if(reservado) {
			model.addAttribute("reservado",true);
			libros.save(libro);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
			model.addAttribute("usuario",true);
			model.addAttribute("usuarioAdmin",false);
			InicioController.listaLibrosDestacados=new ArrayList<>();
			ArrayList<Libro> listaLibros=(ArrayList<Libro>) libros.findAll();
			Collections.shuffle(listaLibros);
			int i = 0;
			int j = 0;
			ArrayList<String> nombresLibros = new ArrayList<>(InicioController.NUMERO_RECURSOS_MAIN);
			do {
				if(!nombresLibros.contains(listaLibros.get(j).getNombre()) && (listaLibros.get(j).isDisponible())) {
					nombresLibros.add(listaLibros.get(j).getNombre());
					InicioController.listaLibrosDestacados.add(listaLibros.get(j));
					j++;
					i++;
				} else
					j++;
			} while(i < InicioController.NUMERO_RECURSOS_MAIN);
			model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
			model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		}
		return "sesionIniciada";
	}
	*/
	@RequestMapping("/libroReservado")//reservarLibros
	public String libroReservado(Model model, HttpSession sesionUsuario,  @RequestParam long idLibro) {
		//Optional<Libro> oLibro = libros.findById(idLibro);
		Libro libro = libros.findById(idLibro);
		/*if (oLibro.get() != null) {
			libro = oLibro.get();
			return "";//buscadorLibros
		}*/
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		boolean reservado = usuario.reservarLibro(libro);
		if(reservado) {
			model.addAttribute("reservado",true);
			libros.save(libro);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
			model.addAttribute("usuario",true);
			model.addAttribute("usuarioAdmin",false);
			InicioController.listaLibrosDestacados=new ArrayList<>();
			ArrayList<Libro> listaLibros=(ArrayList<Libro>) libros.findAll();
			Collections.shuffle(listaLibros);
			int i = 0;
			int j = 0;
			ArrayList<String> nombresLibros = new ArrayList<>(InicioController.NUMERO_RECURSOS_MAIN);
			while((i < InicioController.NUMERO_RECURSOS_MAIN)&&(j<listaLibros.size())){
				if(!nombresLibros.contains(listaLibros.get(j).getNombre()) && (listaLibros.get(j).isDisponible())) {
					nombresLibros.add(listaLibros.get(j).getNombre());
					InicioController.listaLibrosDestacados.add(listaLibros.get(j));
					if(InicioController.listaLibrosDestacados.size()==InicioController.NUMERO_RECURSOS_MAIN) {
						break;
					}
					j++;
					i++;
				} else
					j++;
			}
			model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
			model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		}
		return "sesionIniciada";
	}
	
	
	@RequestMapping("/libroReservaEliminado")
	public String libroReservaEliminado(Model model, HttpSession sesionUsuario,  @RequestParam long idLibro) {
		//Optional<Libro> oLibro = libros.findById(idLibro);
		Libro libro = libros.findById(idLibro);
		//if (oLibro.get() != null) {
			//libro = oLibro.get();
			//return "";//buscadorLibros
		//}
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if (usuario.quitarLibro(libro)) {
			libros.save(libro);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
			model.addAttribute("usuario",true);
			model.addAttribute("usuarioAdmin",false);
			InicioController.listaLibrosDestacados=new ArrayList<>();
			ArrayList<Libro> listaLibros=(ArrayList<Libro>) libros.findAll();
			Collections.shuffle(listaLibros);
			int i = 0;
			int j = 0;
			ArrayList<String> nombresLibros = new ArrayList<>(InicioController.NUMERO_RECURSOS_MAIN);
			while((i < InicioController.NUMERO_RECURSOS_MAIN)&&(j<listaLibros.size())){
				if(!nombresLibros.contains(listaLibros.get(j).getNombre()) && (listaLibros.get(j).isDisponible())) {
					nombresLibros.add(listaLibros.get(j).getNombre());
					InicioController.listaLibrosDestacados.add(listaLibros.get(j));
					if(InicioController.listaLibrosDestacados.size()==InicioController.NUMERO_RECURSOS_MAIN) {
						break;
					}
					j++;
					i++;
				} else
					j++;
			}
			model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
			model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		}
		
		return "sesionIniciada";
	}
	
	@RequestMapping("/revistaReservada")
	public String revistaReservada(Model model, HttpSession sesionUsuario, @RequestParam long idRevista) {
		//Optional<Revista> oEquipo = revistas.findById(idRevista);
		Revista revista = revistas.findById(idRevista);;
		/*if (oRevista.get() != null) {
			revista = oRevista.get();
			return "";//busquedaRevistas
		}*/
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if (usuario.reservarRevista(revista)){
			revistas.save(revista);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
		}
		model.addAttribute("usuario",true);
		model.addAttribute("usuarioAdmin",false);
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		InicioController.listaRevistasDestacadas=new ArrayList<>();
		ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
		Collections.shuffle(listaRevistas);
		ArrayList<String> nombresRevistas = new ArrayList<>(InicioController.NUMERO_RECURSOS_MAIN);
		int i = 0;
		int j = 0;
		while((i < InicioController.NUMERO_RECURSOS_MAIN)&&(j<listaRevistas.size())){
			if(!nombresRevistas.contains(listaRevistas.get(j).getNombre()) && (listaRevistas.get(j).isDisponible())) {
				nombresRevistas.add(listaRevistas.get(j).getNombre());
				InicioController.listaRevistasDestacadas.add(listaRevistas.get(j));
				if(InicioController.listaRevistasDestacadas.size()==InicioController.NUMERO_RECURSOS_MAIN) {
					break;
				}
				j++;
				i++;
			} else
				j++;
		} 
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";
	}

	@RequestMapping("/revistaReservaEliminada")
	public String revistaReservaEliminada(Model model, HttpSession sesionUsuario, @RequestParam long idRevista)  {
		//Optional<Revista> oEquipo = revistas.findById(idRevista);
		Revista revista = revistas.findById(idRevista);
		//if (oRevista.get() != null) {
			//revista = oRevista.get();
			//return "";//busquedaLibros
		//}
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if (usuario.quitarRevista(revista)){
			revistas.save(revista);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
		}
		model.addAttribute("usuario",true);
		model.addAttribute("usuarioAdmin",false);
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		InicioController.listaRevistasDestacadas=new ArrayList<>();
		ArrayList<Revista> listaRevistas=(ArrayList<Revista>) revistas.findAll();
		Collections.shuffle(listaRevistas);
		ArrayList<String> nombresRevistas = new ArrayList<>(InicioController.NUMERO_RECURSOS_MAIN);
		int i = 0;
		int j = 0;
		while((i < InicioController.NUMERO_RECURSOS_MAIN)&&(j<listaRevistas.size())){
			if(!nombresRevistas.contains(listaRevistas.get(j).getNombre()) && (listaRevistas.get(j).isDisponible())) {
				nombresRevistas.add(listaRevistas.get(j).getNombre());
				InicioController.listaRevistasDestacadas.add(listaRevistas.get(j));
				if(InicioController.listaRevistasDestacadas.size()==InicioController.NUMERO_RECURSOS_MAIN) {
					break;
				}
				j++;
				i++;
			} else
				j++;
		}
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";
	}
	
	@RequestMapping("/equipoReservado")//reservarEquipos
	public String equipoReservado(Model model, HttpSession sesionUsuario, @RequestParam long idEquipo) {
		EquipoInformatico equipo = equiposInformaticos.findById(idEquipo);
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if (usuario.reservarPuestoInformatico(equipo)){
			equiposInformaticos.save(equipo);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
		}
		model.addAttribute("usuario",true);
		model.addAttribute("usuarioAdmin",false);
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";//reservaEquipoInfromatico
	}
	
	@RequestMapping("/equipoReservaEliminado")//reservaEquipoInfromatico
	public String equipoReservaEliminado(Model model, HttpSession sesionUsuario, @RequestParam long idEquipo) {
		//Optional<EquipoInformatico> oEquipo = equiposInformaticos.findById(idEquipo);
		EquipoInformatico equipo = equiposInformaticos.findById(idEquipo);
		//if (oEquipo.get() != null) {
			//equipo = oEquipo.get();
			//return "";//reservaEquipoInfromatico
		//}
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if (usuario.quitarPuestoInformatico(equipo)){
			equiposInformaticos.save(equipo);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
		}
		model.addAttribute("usuario",true);
		model.addAttribute("usuarioAdmin",false);
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";//reservaEquipoInfromatico
	}
	
	@RequestMapping("/salaReservada")//reservarSalas
	public String salaReservada(Model model, HttpSession sesionUsuario, @RequestParam long idSala) {
		//Optional<SalaTrabajoGrupo> oSala = salasTrabajoGrupo.findById(idSala);
		SalaTrabajoGrupo sala = salasTrabajoGrupo.findById(idSala);
		/*if (oSala.get() != null) {
			sala = oSala.get();
			return "";//reservaSalaTrabajoGrupo
		}*/
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if (usuario.reservarSalaTrabajoGrupo(sala)){
			salasTrabajoGrupo.save(sala);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
		}
		model.addAttribute("usuario",true);
		model.addAttribute("usuarioAdmin",false);
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";//reservaSalaTrabajoGrupo
	}
	
	@RequestMapping("/salaReservaEliminada")
	public String salaReservaEliminada(Model model, HttpSession sesionUsuario, @RequestParam long idSala) {
		//Optional<SalaTrabajoGrupo> oSala = salasTrabajoGrupo.findById(idSala);
		SalaTrabajoGrupo sala = salasTrabajoGrupo.findById(idSala);
		//if (oSala.get() != null) {
			//sala = oSala.get();
			//return "";//reservaSalaTrabajoGrupo
		//}
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if (usuario.quitarSalaTrabajoGrupo(sala)){
			salasTrabajoGrupo.save(sala);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
		}
		model.addAttribute("usuario",true);
		model.addAttribute("usuarioAdmin",false);
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";//reservaSalaTrabajoGrupo
	}
	

}
