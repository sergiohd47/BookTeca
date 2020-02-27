package dad.web.bookteca.controladores;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.web.bookteca.basedatos.EquipoInformaticoRepository;
import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.EquipoInformatico;
import dad.web.bookteca.clases.Usuario;

@Controller
public class EquipoInformaticoController {
	@Autowired
	private EquipoInformaticoRepository equiposInformaticos;
	@Autowired
	private UsuarioRepository usuarios;
	
	@RequestMapping("/reservaEquipoInformatico")
	public String reservaEquipoInformatico(Model model, HttpSession usuarioSesion, HttpServletRequest request) {
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
				if(usuario.getPuestoInformatico() != null)
					model.addAttribute("equipoElegido",true);
				else {
					CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
					model.addAttribute("token",token.getToken());
					model.addAttribute("equipoElegido",false);
				}
				listaEquipo=(ArrayList<EquipoInformatico>) equiposInformaticos.findByDisponible(true);
				model.addAttribute("visibleTabla",!listaEquipo.isEmpty());
				model.addAttribute("listaEquipo",listaEquipo);
			}
		}
		return "reservaEquipoInformatico";
	}
	@RequestMapping("/equipoReservado")
	public String equipoReservado(Model model, HttpSession sesionUsuario, @RequestParam long idEquipo, 
			HttpServletRequest request) {
		EquipoInformatico equipo = equiposInformaticos.findById(idEquipo);
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if (usuario.reservarPuestoInformatico(equipo)){
			equiposInformaticos.save(equipo);
			usuarios.save(usuario);
			sesionUsuario.setAttribute("infoUsuario",usuario);
		}
		model.addAttribute("usuario",true);
		model.addAttribute("usuarioAdmin",false);
		CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenLibro",tokenLibro.getToken());
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenRevista",tokenRevista.getToken());
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";
	}
	
	@RequestMapping("/equipoDesocupado")
	public String equipoDesocupado(Model model, HttpSession sesionUsuario, @RequestParam long idEquipo, 
			HttpServletRequest request) {
		EquipoInformatico equipo = equiposInformaticos.findById(idEquipo);
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		usuario.quitarPuestoInformatico(equipo);
		equiposInformaticos.save(equipo);
		usuarios.save(usuario);
		sesionUsuario.setAttribute("infoUsuario",usuario);
		model.addAttribute("usuario",true);
		model.addAttribute("usuarioAdmin",false);
		CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenLibro",tokenLibro.getToken());
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenRevista",tokenRevista.getToken());
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";
	}
}
