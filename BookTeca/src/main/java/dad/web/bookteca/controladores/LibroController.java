package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
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
	public String buscadorLibros(Model model, HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token",token.getToken());
		if(InicioController.sesionNoIniciada)
			model.addAttribute("visibleIniciarSesion",true);
		else {
			Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador())
				model.addAttribute("nombre",usuario.getNombre());
		}
		return "buscadorLibros";
	}

	@RequestMapping("/busquedaLibros")
	public String busquedaLibros(Model model, @RequestParam("palabraClaveLibro") String info, 
			HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token",token.getToken());
		ArrayList<Libro>listaLibros=new ArrayList<>();
		listaLibros.addAll(libros.findByNombreOrAutorOrEditorialOrGenero(info,info,info,info));
		if(InicioController.sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			model.addAttribute("listaLibrosBusqueda",listaLibros);
			model.addAttribute("visibleTabla",!listaLibros.isEmpty());
		} else {
			Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
			ArrayList<Libro> listaLibrosBusqueda=new ArrayList<>();
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				model.addAttribute("nombre",usuario.getNombre());
				for(Libro l : listaLibros) {
					if(l.isDisponible())
						listaLibrosBusqueda.add(l);
				}
				CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
				model.addAttribute("tokenLibro",tokenLibro.getToken());
				model.addAttribute("listaLibrosBusqueda",listaLibrosBusqueda);
				model.addAttribute("visibleTabla",!listaLibrosBusqueda.isEmpty());
			}
		}
		return "busquedaLibros";
	}
	@RequestMapping("/libroReservado")
	public String libroReservado(Model model,@RequestParam long idLibro, 
			HttpServletRequest request) {
		Libro libro = libros.findById(idLibro);
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		if(usuario.reservarLibro(libro)) {
			libros.save(libro);
			usuarios.save(usuario);
		}
		model.addAttribute("usuario",request.isUserInRole("USER"));
		model.addAttribute("usuarioAdmin",!request.isUserInRole("USER"));
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
		CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenLibro",tokenLibro.getToken());
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);	
		CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenRevista",tokenRevista.getToken());
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";
	}
	
	
	@RequestMapping("/libroDevuelto")
	public String libroDevuelto(Model model,@RequestParam long idLibro, 
			HttpServletRequest request) {
		Libro libro = libros.findById(idLibro);
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		usuario.quitarLibro(libro);
		libros.save(libro);
		usuarios.save(usuario);
		model.addAttribute("nombre",usuario.getNombre());
		model.addAttribute("usuario",request.isUserInRole("USER"));
		model.addAttribute("usuarioAdmin",!request.isUserInRole("USER"));
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
		CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenLibro",tokenLibro.getToken());
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenRevista",tokenRevista.getToken());
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";
	}
}
