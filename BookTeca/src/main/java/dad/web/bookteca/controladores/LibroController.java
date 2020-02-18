package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.web.bookteca.basedatos.LibroRepository;
import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.Libro;
import dad.web.bookteca.clases.Usuario;
@Controller
public class LibroController {
	@Autowired
	private LibroRepository libros;
	@Autowired
	private UsuarioRepository usuarios;
	
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
	
	
	/*@RequestMapping("/libroReservaEliminado")
	public String libroReservaEliminado(Model model, HttpSession sesionUsuario,  @RequestParam long idLibro) {
		//Optional<Libro> oLibro = libros.findById(idLibro);
		Libro libro = libros.findById(idLibro);
		//if (oLibro.get() != null) {
			//libro = oLibro.get();
			//return "";//buscadorLibros
		//}
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if (usuario.quitarLibro(libro)) {
			libro.setIsDisponible(true);
			libros.save(libro);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
			model.addAttribute("nombre",usuario.getNombre());
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
		
		return "sesion";
	}*/
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
}
