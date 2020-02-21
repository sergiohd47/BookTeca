package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.web.bookteca.basedatos.RevistaRepository;
import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.Revista;
import dad.web.bookteca.clases.Usuario;

@Controller
public class RevistaController {
	@Autowired
	private RevistaRepository revistas;
	@Autowired
	private UsuarioRepository usuarios;
	
	@RequestMapping("/buscadorRevistas")
	public String buscadorRevista(Model model, HttpSession usuarioSesion, HttpServletRequest servlet) {
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
	public String busquedaRevistas(Model model, HttpSession usuarioSesion, @RequestParam("palabraClaveRevista") String info, HttpServletRequest servlet) {
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
	@RequestMapping("/revistaReservada")
	public String revistaReservada(Model model, HttpSession sesionUsuario, @RequestParam long idRevista, HttpServletRequest servlet) {
		Revista revista = revistas.findById(idRevista);;
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

	@RequestMapping("/revistaDevuelta")
	public String revistaDevuelta(Model model, HttpSession sesionUsuario, @RequestParam long idRevista, HttpServletRequest servlet)  {
		Revista revista = revistas.findById(idRevista);
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		usuario.quitarRevista(revista);
		revistas.save(revista);
		usuarios.save(usuario);
		sesionUsuario.setAttribute("infoUsuario",usuario);
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
}
