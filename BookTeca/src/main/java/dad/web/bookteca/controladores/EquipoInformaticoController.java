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
import org.springframework.web.client.RestTemplate;

import dad.web.bookteca.basedatos.EquipoInformaticoRepository;
import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.Email;
import dad.web.bookteca.clases.EquipoInformatico;
import dad.web.bookteca.clases.Usuario;

@Controller
public class EquipoInformaticoController {
	@Autowired
	private EquipoInformaticoRepository equiposInformaticos;
	@Autowired
	private UsuarioRepository usuarios;
	
	@RequestMapping("/reservaEquipoInformatico")
	public String reservaEquipoInformatico(Model model, HttpServletRequest request) {
		ArrayList<EquipoInformatico> listaEquipo=new ArrayList<>();
		if(InicioController.sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			listaEquipo=(ArrayList<EquipoInformatico>) equiposInformaticos.findByDisponible(true);
			model.addAttribute("visibleTabla",!listaEquipo.isEmpty());
			model.addAttribute("listaEquipo",listaEquipo);
		} else {
			Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
				model.addAttribute("token",token.getToken());
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
	public String equipoReservado(Model model, @RequestParam long idEquipo, HttpServletRequest request) {
		EquipoInformatico equipo = equiposInformaticos.findById(idEquipo);
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		if (usuario.reservarPuestoInformatico(equipo)){
			equiposInformaticos.save(equipo);
			usuarios.save(usuario);
			//PARTE SERVICIO INTERNO
			Email email=new Email(usuario.getEmail(),idEquipo,"reserva");
			String urlCorreo="http://0.0.0.0:5000/mail/equipoInformatico/";
			RestTemplate rest=new RestTemplate();
			rest.postForObject(urlCorreo,email,Email.class);
			System.out.println("Datos reserva enviados: "+usuario.getEmail());
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
	
	@RequestMapping("/equipoDesocupado")
	public String equipoDesocupado(Model model, @RequestParam long idEquipo, 
			HttpServletRequest request) {
		EquipoInformatico equipo = equiposInformaticos.findById(idEquipo);
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		usuario.quitarPuestoInformatico(equipo);
		equiposInformaticos.save(equipo);
		usuarios.save(usuario);
		//PARTE SERVICIO INTERNO
		Email email=new Email(usuario.getEmail(),idEquipo,"devolucion");
		String urlCorreo="http://0.0.0.0:5000/mail/equipoInformatico/";
		RestTemplate rest=new RestTemplate();
		rest.postForObject(urlCorreo,email,Email.class);
		System.out.println("Datos devolucion enviados: "+usuario.getEmail());
		
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
