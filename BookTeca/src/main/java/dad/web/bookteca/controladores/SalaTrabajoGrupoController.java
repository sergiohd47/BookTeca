package dad.web.bookteca.controladores;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String reservaSalaTrabajoGrupo(Model model, HttpServletRequest request) {
		ArrayList<SalaTrabajoGrupo> listaSTG=new ArrayList<>();
		if(InicioController.sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			listaSTG=(ArrayList<SalaTrabajoGrupo>) salasTrabajoGrupo.findByDisponible(true);
			boolean visibleTabla=!listaSTG.isEmpty();
			model.addAttribute("visibleTabla",visibleTabla);
			model.addAttribute("listaSTG",listaSTG);
		} else {
			Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
				model.addAttribute("token",token.getToken());
				if(usuario.getSalaTrabajo()!= null)
					model.addAttribute("salaElegida",true);
				else
					model.addAttribute("salaElegida",false);
				listaSTG=(ArrayList<SalaTrabajoGrupo>) salasTrabajoGrupo.findByDisponible(true);
				model.addAttribute("visibleTabla",!listaSTG.isEmpty());
				model.addAttribute("listaSTG",listaSTG);
			}
		}
		return "reservaSalaTrabajoGrupo";
	}
	
	@RequestMapping("/salaReservada")
	public String salaReservada(Model model, @RequestParam long idSala, 
			HttpServletRequest request) {
		SalaTrabajoGrupo sala = salasTrabajoGrupo.findById(idSala);
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		if(usuario.reservarSalaTrabajoGrupo(sala)){
			salasTrabajoGrupo.save(sala);
			usuarios.save(usuario);
		}
		model.addAttribute("usuario",request.isUserInRole("USER"));
		model.addAttribute("usuarioAdmin",!request.isUserInRole("USER"));
		CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenLibro",tokenLibro.getToken());
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenRevista",tokenRevista.getToken());
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";
	}
	
	@RequestMapping("/salaDesocupada")
	public String salaDesocupada(Model model, @RequestParam long idSala, 
			HttpServletRequest request) {
		SalaTrabajoGrupo sala = salasTrabajoGrupo.findById(idSala);
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		usuario.quitarSalaTrabajoGrupo(sala);
		salasTrabajoGrupo.save(sala);
		usuarios.save(usuario);
		model.addAttribute("usuario",request.isUserInRole("USER"));
		model.addAttribute("usuarioAdmin",!request.isUserInRole("USER"));
		CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenLibro",tokenLibro.getToken());
		model.addAttribute("listaLibrosDestacados",InicioController.listaLibrosDestacados);
		CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenRevista",tokenRevista.getToken());
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";
	}
	
}
