package dad.web.bookteca.controladores;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	/*
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
	*/

	
}
