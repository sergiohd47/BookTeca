package dad.web.bookteca.controladores;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
				if(usuario.getPuestoInformatico() != null)
					model.addAttribute("equipoElegido",true);
				else
					model.addAttribute("equipoElegido",false);
				listaEquipo=(ArrayList<EquipoInformatico>) equiposInformaticos.findByDisponible(true);
				model.addAttribute("visibleTabla",!listaEquipo.isEmpty());
				model.addAttribute("listaEquipo",listaEquipo);
			}
		}
		return "reservaEquipoInformatico";
	}
	@RequestMapping("/equipoReservado")
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
		return "sesionIniciada";
	}
	
	/*@RequestMapping("/equipoReservaEliminado")//reservaEquipoInfromatico
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
	}*/
}
