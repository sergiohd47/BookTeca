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
import org.springframework.web.client.RestTemplate;

import dad.web.bookteca.basedatos.RevistaRepository;
import dad.web.bookteca.basedatos.UsuarioRepository;
import dad.web.bookteca.clases.Email;
import dad.web.bookteca.clases.Revista;
import dad.web.bookteca.clases.Usuario;

@Controller
public class RevistaController {
	@Autowired
	private RevistaRepository revistas;
	@Autowired
	private UsuarioRepository usuarios;
	
	@RequestMapping("/buscadorRevistas")
	public String buscadorRevista(Model model, HttpServletRequest request) {
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
		return "buscadorRevistas";
	}
	
	@RequestMapping("/busquedaRevistas")
	public String busquedaRevistas(Model model, @RequestParam("palabraClaveRevista") String info, 
			HttpServletRequest request) {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token",token.getToken());
		ArrayList<Revista> listaRevistas=new ArrayList<>();
		listaRevistas.addAll(revistas.findByNombreOrEditorialOrGenero(info,info,info));
		if(InicioController.sesionNoIniciada) {
			model.addAttribute("visibleIniciarSesion",true);
			model.addAttribute("listaRevistasBusqueda",listaRevistas);
			model.addAttribute("visibleTabla",!listaRevistas.isEmpty());
		} else {
			Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
			ArrayList<Revista> listaRevistasBusqueda=new ArrayList<>();
			model.addAttribute("visibleCerrarSesion",true);
			if(!usuario.getAdministrador()) {
				model.addAttribute("nombre",usuario.getNombre());
				for(Revista r : listaRevistas) {
					if(r.isDisponible())
						listaRevistasBusqueda.add(r);
				}
				CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
				model.addAttribute("tokenRevista",tokenRevista.getToken());
				model.addAttribute("listaRevistasBusqueda",listaRevistasBusqueda);
				model.addAttribute("visibleTabla",!listaRevistasBusqueda.isEmpty());
			}
		}
		return "busquedaRevistas";
	}
	@RequestMapping("/revistaReservada")
	public String revistaReservada(Model model, @RequestParam long idRevista, 
			HttpServletRequest request) {
		Revista revista = revistas.findById(idRevista);
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		if (usuario.reservarRevista(revista)){
			revistas.save(revista);
			usuarios.save(usuario);
			//PARTE SERVICIO INTERNO
			String urlCorreo="http://172.17.0.3:5000/mail/revista/";
			Email email=new Email(usuario.getEmail(),idRevista,"reserva");
			RestTemplate rest=new RestTemplate();
			rest.postForObject(urlCorreo,email,Email.class);
			System.out.println("Datos reserva enviados: "+usuario.getEmail());
		}
		
		model.addAttribute("usuario",request.isUserInRole("USER"));
		model.addAttribute("usuarioAdmin",!request.isUserInRole("USER"));
		CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenLibro",tokenLibro.getToken());
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
		CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenRevista",tokenRevista.getToken());
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";
	}

	@RequestMapping("/revistaDevuelta")
	public String revistaDevuelta(Model model, @RequestParam long idRevista, 
			HttpServletRequest request)  {
		Revista revista = revistas.findById(idRevista);
		Usuario usuario = usuarios.findByEmail(request.getUserPrincipal().getName());
		usuario.quitarRevista(revista);
		revistas.save(revista);
		usuarios.save(usuario);
		//PARTE SERVICIO INTERNO
		String urlCorreo="http://172.17.0.3:5000/mail/revista/";
		Email email=new Email(usuario.getEmail(),idRevista,"devolucion");
		RestTemplate rest=new RestTemplate();
		rest.postForObject(urlCorreo,email,Email.class);
		System.out.println("Datos devolucion enviados: "+usuario.getEmail());
		
	
		model.addAttribute("usuario",request.isUserInRole("USER"));
		model.addAttribute("usuarioAdmin",!request.isUserInRole("USER"));
		CsrfToken tokenLibro = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenLibro",tokenLibro.getToken());
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
		CsrfToken tokenRevista = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("tokenRevista",tokenRevista.getToken());
		model.addAttribute("listaRevistasDestacadas",InicioController.listaRevistasDestacadas);
		return "sesionIniciada";
	}
}
