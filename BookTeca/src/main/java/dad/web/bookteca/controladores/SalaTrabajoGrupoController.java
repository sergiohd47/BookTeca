package dad.web.bookteca.controladores;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dad.web.bookteca.basedatos.SalaTrabajoGrupoRepository;
import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.SalaTrabajoGrupo;
import dad.web.bookteca.clases.Usuario;

@Controller
public class SalaTrabajoGrupoController {
	@Autowired
	private SalaTrabajoGrupoRepository salasTrabajoGrupo;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	@RequestMapping("/reservaSalaTrabajoGrupo")
	public String reservaSalaTrabajoGrupo(Model model, HttpSession usuarioSesion, HttpServletRequest request) {
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
				//SalaTrabajoGrupo STG = salasTrabajoGrupo.findByIdUsuario(usuario);
				if(usuario.getSalaTrabajo()!= null)
					model.addAttribute("salaElegida",true);
				else {
					CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
					model.addAttribute("token",token.getToken());
					model.addAttribute("salaElegida",false);
				}
				listaSTG=(ArrayList<SalaTrabajoGrupo>) salasTrabajoGrupo.findByDisponible(true);
				model.addAttribute("visibleTabla",!listaSTG.isEmpty());
				model.addAttribute("listaSTG",listaSTG);
			}
		}
		return "reservaSalaTrabajoGrupo";
	}
	
	@RequestMapping("/salaReservada")
	public String salaReservada(Model model, HttpSession sesionUsuario, @RequestParam long idSala, 
			HttpServletRequest request) {
		SalaTrabajoGrupo sala = salasTrabajoGrupo.findById(idSala);
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		if(usuario.reservarSalaTrabajoGrupo(sala)){
			salasTrabajoGrupo.save(sala);
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
	
	@RequestMapping("/salaDesocupada")
	public String salaDesocupada(Model model, HttpSession sesionUsuario, @RequestParam long idSala, 
			HttpServletRequest request) {
		SalaTrabajoGrupo sala = salasTrabajoGrupo.findById(idSala);
		Usuario usuario=(Usuario)sesionUsuario.getAttribute("infoUsuario");
		usuario.quitarSalaTrabajoGrupo(sala);
		salasTrabajoGrupo.save(sala);
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
