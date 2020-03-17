package dad.web.ServicioInterno.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sun.mail.smtp.SMTPTransport;

import dad.web.ServicioInterno.clases.Libro;
import dad.web.ServicioInterno.clases.Revista;
import dad.web.ServicioInterno.clases.SalaTrabajoGrupo;
import dad.web.ServicioInterno.clases.EquipoInformatico;
import dad.web.ServicioInterno.clases.Email;
import dad.web.ServicioInterno.basedatos.LibroRepository;
import dad.web.ServicioInterno.basedatos.RevistaRepository;
import dad.web.ServicioInterno.basedatos.SalaTrabajoGrupoRepository;
import dad.web.ServicioInterno.basedatos.EquipoInformaticoRepository;
import dad.web.ServicioInterno.basedatos.UsuarioRepository;


@RestController
public class CorreoController {
	
	@Autowired
	private LibroRepository libros;

	@Autowired
	private RevistaRepository revistas;
	
	@Autowired
	private SalaTrabajoGrupoRepository salasTrabajoGrupo;
	
	@Autowired
	private EquipoInformaticoRepository equiposInformaticos;
	
	@Autowired
	private UsuarioRepository usuarios;
	
	//CORREO BOOKTECA GMAIL
	private final String correoBookteca= "booktecadad";
	private final String contraseñaBookteca="bookteca123";
	
	@PostMapping(value= "/mail/")
	public void enviarEmail(@RequestBody Email email) {
		Properties properties=System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.user", correoBookteca);
		properties.setProperty("mail.smtp.clave", contraseñaBookteca);
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		
		//ESTA PARTE HAY QUE MIRARLA
		Session sesion=Session.getDefaultInstance(properties);
		final MimeMessage mensaje=new MimeMessage(sesion);
		
		String direccionCorreo=email.getNombreEmail();
		String tipoAccion=email.getTipoAccion();
		long idRecurso=email.getIdRecurso();
		System.out.println("Datos recibidos de: "+direccionCorreo+" para la accion: "+tipoAccion);
		Libro libroCorreo=libros.findById(idRecurso);
		if(libroCorreo==null) {
			Revista revistaCorreo=revistas.findById(idRecurso);
			if(revistaCorreo==null) {
				SalaTrabajoGrupo salaCorreo=salasTrabajoGrupo.findById(idRecurso);
				if(salaCorreo==null) {
					EquipoInformatico equipoCorreo=equiposInformaticos.findById(idRecurso);
					if(equipoCorreo==null) {
						System.out.println("Fallo en el correo (no se encuentra recurso)");
					}else {
						try {
							mensaje.setFrom(new InternetAddress(correoBookteca));
							mensaje.addRecipients(Message.RecipientType.TO, direccionCorreo);
							if(tipoAccion.equals("reserva")) {
								mensaje.setSubject("Reserva de equipo informatico");
								mensaje.setText("Hola, usted acaba de reservar el siguiente equipo informatico: "+"\n"
										+"\t"+"Localizacion: "+equipoCorreo.getLocalizacion()+"\n"
										+"\t"+ "Sistema Operativo: "+equipoCorreo.getSistemaOperativo()+"\n"
										+"\t"+"Fecha final reserva: "+equipoCorreo.getFechaReserva()+"\n"
										+"Esperamos que le guste :)\nUn saludo, Bookteca","utf-8");
								SMTPTransport t=(SMTPTransport)sesion.getTransport("smtp");
								t.connect("smtp.gmail.com",correoBookteca,contraseñaBookteca);
								t.sendMessage(mensaje, mensaje.getAllRecipients());
								t.close();
								System.out.println("Correo enviado con exito");
							}else {
								mensaje.setSubject("Devolucion de sala");
								mensaje.setText("Hola, usted acaba de devolver el siguiente equipo informatico: "+"\n"
										+"\t"+"Localizacion: "+equipoCorreo.getLocalizacion()+"\n"
										+"\t"+"Sistema Operativo: "+equipoCorreo.getSistemaOperativo()+"\n"
										+"\t"+"Fecha final reserva: "+equipoCorreo.getFechaReserva()+"\n"
										+"Esperamos que le haya gustado :)\nUn saludo, Bookteca","utf-8");
								SMTPTransport t=(SMTPTransport)sesion.getTransport("smtp");
								t.connect("smtp.gmail.com",correoBookteca,contraseñaBookteca);
								t.sendMessage(mensaje, mensaje.getAllRecipients());
								t.close();
								System.out.println("Correo enviado con exito");
							}
						}catch (MessagingException ex) {
							ex.printStackTrace();
						}
					}
				}else {
					String compartida=null;
					if(salaCorreo.isCompartida()) {
						compartida="Si";
					}else {
						compartida="No";
					}
					try {
						mensaje.setFrom(new InternetAddress(correoBookteca));
						mensaje.addRecipients(Message.RecipientType.TO, direccionCorreo);
						if(tipoAccion.equals("reserva")) {
							mensaje.setSubject("Reserva de sala");
							mensaje.setText("Hola, usted acaba de reservar la siguiente sala de trabajo: "+"\n"
									+"\t"+"Localizacion: "+salaCorreo.getLocalizacion()+"\n"
									+"\t"+"Capacidad: "+salaCorreo.getCapacidad()+"\n"
									+"\t"+"Compartida: "+compartida+"\n"
									+"\t"+"Fecha final reserva: "+salaCorreo.getFechaReserva()+"\n"
									+"Esperamos que le guste :)\nUn saludo, Bookteca","utf-8");
							SMTPTransport t=(SMTPTransport)sesion.getTransport("smtp");
							t.connect("smtp.gmail.com",correoBookteca,contraseñaBookteca);
							t.sendMessage(mensaje, mensaje.getAllRecipients());
							t.close();
							System.out.println("Correo enviado con exito");
						}else {
							mensaje.setSubject("Devolucion de sala");
							mensaje.setText("Hola, usted acaba de devolver la siguiente sala de trabajo: "+"\n"
									+"\t"+"Localizacion: "+salaCorreo.getLocalizacion()+"\n"
									+"\t"+"Capacidad: "+salaCorreo.getCapacidad()+"\n"
									+"\t"+"Compartida: "+compartida+"\n"
									+"\t"+"Fecha final reserva: "+salaCorreo.getFechaReserva()+"\n"
									+"Esperamos que le haya gustado :)\nUn saludo, Bookteca","utf-8");
							SMTPTransport t=(SMTPTransport)sesion.getTransport("smtp");
							t.connect("smtp.gmail.com",correoBookteca,contraseñaBookteca);
							t.sendMessage(mensaje, mensaje.getAllRecipients());
							t.close();
							System.out.println("Correo enviado con exito");
						}
					}catch (MessagingException ex) {
						ex.printStackTrace();
					}
				}
			}else {
				try {
					mensaje.setFrom(new InternetAddress(correoBookteca));
					mensaje.addRecipients(Message.RecipientType.TO, direccionCorreo);
					if(tipoAccion.equals("reserva")) {
						mensaje.setSubject("Reserva de revista");
						mensaje.setText("Hola, usted acaba de reservar la siguiente revista: "+"\n"
								+"\t"+"Nombre: "+revistaCorreo.getNombre()+"\n"
								+"\t"+"Editorial: "+revistaCorreo.getEditorial()+"\n"
								+"\t"+"Fasciculo: "+revistaCorreo.getFasciculo()+"\n"
								+"\t"+"Genero: "+revistaCorreo.getGenero()+"\n"
								+"\t"+"Fecha final reserva: "+revistaCorreo.getFecFin()+"\n"
								+"Esperamos que le guste :)\nUn saludo, Bookteca","utf-8");
						SMTPTransport t=(SMTPTransport)sesion.getTransport("smtp");
						t.connect("smtp.gmail.com",correoBookteca,contraseñaBookteca);
						t.sendMessage(mensaje, mensaje.getAllRecipients());
						t.close();
						System.out.println("Correo enviado con exito");
					}else {
						mensaje.setSubject("Devolucion de revista");
						mensaje.setText("Hola, usted acaba de devolver la siguiente revista: "+"\n"
								+"\t"+"Nombre: "+revistaCorreo.getNombre()+"\n"
								+"\t"+"Editorial: "+revistaCorreo.getEditorial()+"\n"
								+"\t"+"Fasciculo: "+revistaCorreo.getFasciculo()+"\n"
								+"\t"+"Genero: "+revistaCorreo.getGenero()+"\n"
								+"Esperamos que le haya gustado :)\nUn saludo, Bookteca","utf-8");
						SMTPTransport t=(SMTPTransport)sesion.getTransport("smtp");
						t.connect("smtp.gmail.com",correoBookteca,contraseñaBookteca);
						t.sendMessage(mensaje, mensaje.getAllRecipients());
						t.close();
						System.out.println("Correo enviado con exito");
					}
				}catch (MessagingException ex) {
					System.out.println(ex);
				}
			}
		}else {
			try {
				mensaje.setFrom(new InternetAddress(correoBookteca));
				mensaje.addRecipients(Message.RecipientType.TO, direccionCorreo);
				if(tipoAccion.equals("reserva")) {
					mensaje.setSubject("Reserva de libro");
					mensaje.setText("Hola, usted acaba de reservar el siguiente libro: "+"\n"
							+"\t"+"Nombre: "+libroCorreo.getNombre()+"\n"
							+"\t"+"Autor: "+libroCorreo.getAutor()+"\n"
							+"\t"+"Editorial: "+libroCorreo.getEditorial()+"\n"
							+"\t"+"Genero: "+libroCorreo.getGenero()+"\n"
							+"\t"+"Fecha final reserva: "+libroCorreo.getFecFin()+"\n"
							+"Esperamos que le guste :)\nUn saludo, Bookteca","utf-8");
					SMTPTransport t=(SMTPTransport)sesion.getTransport("smtp");
					t.connect("smtp.gmail.com",correoBookteca,contraseñaBookteca);
					t.sendMessage(mensaje, mensaje.getAllRecipients());
					t.close();
					System.out.println("Correo enviado con exito");
				}else {
					mensaje.setSubject("Devolucion de libro");
					mensaje.setText("Hola, usted acaba de devolver el siguiente libro: "+"\n"
							+"\t"+"Nombre: "+libroCorreo.getNombre()+"\n"
							+"\t"+"Autor: "+libroCorreo.getAutor()+"\n"
							+"\t"+"Editorial: "+libroCorreo.getEditorial()+"\n"
							+"\t"+"Genero: "+libroCorreo.getGenero()+"\n"
							+"Esperamos que le haya gustado :)\nUn saludo, Bookteca","utf-8");
					SMTPTransport t=(SMTPTransport)sesion.getTransport("smtp");
					t.connect("smtp.gmail.com",correoBookteca,contraseñaBookteca);
					t.sendMessage(mensaje, mensaje.getAllRecipients());
					t.close();
					System.out.println("Correo enviado con exito");
				}
			}catch (MessagingException ex) {
				ex.printStackTrace();
			}
		}
		
		
	}
}