package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.web.bookteca.clases.EquipoInformatico;
import dad.web.bookteca.clases.Libro;
import dad.web.bookteca.clases.Revista;
import dad.web.bookteca.clases.SalaTrabajoGrupo;

@Controller
public class inicioController {
	@RequestMapping("/")
	public String inicio(Model model) {
		ArrayList<Libro> listaLibros=new ArrayList<>();
		Libro libro1=new Libro(1,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro2=new Libro(2,"Los Tres Cerditos", "Borja","El Mundo","Infantil");
		Libro libro3=new Libro(3,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes" );
		Libro libro4=new Libro(4,"Constitucion Española","Indef","España","Institucional");
		Libro libro5=new Libro(5,"Derecho Mercantil Español","Cristina","AAA","Educativo");
		Libro libro6=new Libro(6,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro7=new Libro(7,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro8=new Libro(8,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes" );
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);
		listaLibros.add(libro6);
		listaLibros.add(libro7);
		listaLibros.add(libro8);
		ArrayList<Revista> listaRevistas=new ArrayList<>();
		Revista revista1=new Revista(1,"GQ", "Editorial GQ",123,"Actualidad" );
		Revista revista2=new Revista(2,"FHM", "Editorial FHM",35,"Erotica");
		Revista revista3=new Revista(3,"MasQueCoches", "Editorial MasQueCoches",77,"Automovil" );
		Revista revista4=new Revista(4,"AutoSport", "Editorial AutoSport",23,"Automovil" );
		Revista revista5=new Revista(5,"MuyInteresante", "Editorial MuyInteresante",456,"Intelectual" );
		listaRevistas.add(revista1);
		listaRevistas.add(revista2);
		listaRevistas.add(revista3);
		listaRevistas.add(revista4);
		listaRevistas.add(revista5);
		
		Random randomPick=new Random();
		Libro libroEscogido=listaLibros.get(randomPick.nextInt(listaLibros.size()));
		model.addAttribute("nombreLibroDestacado",libroEscogido.getNombre());
		model.addAttribute("autorLibroDestacado",libroEscogido.getAutor());
		model.addAttribute("editorialLibroDestacado",libroEscogido.getEditorial());
		model.addAttribute("generoLibroDestacado",libroEscogido.getGenero());
		model.addAttribute("numeroEjemplaresLibroDestacado",3); //luego se saca de la base de datos
		
		Revista revistaEscogida=listaRevistas.get(randomPick.nextInt(listaRevistas.size()));
		model.addAttribute("nombreRevistaDestacada",revistaEscogida.getNombre());
		model.addAttribute("editorialRevistaDestacada",revistaEscogida.getEditorial());
		model.addAttribute("fasciculoRevistaDestacada",revistaEscogida.getFasciculo());
		model.addAttribute("generoRevistaDestacada",revistaEscogida.getGenero());
		model.addAttribute("numeroEjemplaresRevistaDestacada",3); //luego se saca de la base de datos
		
		//NO HA INICIADO SESION
		model.addAttribute("visibleIniciarSesion", true);
		model.addAttribute("visibleCerrarSesion", false);
		
		return "index";
	}
	@RequestMapping("/iniciarSesion")
	public String iniciarSesion(Model model) {
		return "iniciarSesionNuevo";
	}
	@RequestMapping("/sesionIniciada")
	public String sesionIniciada(Model model) {
		ArrayList<Libro> listaLibros=new ArrayList<>();
		Libro libro1=new Libro(1,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro2=new Libro(2,"Los Tres Cerditos", "Borja","El Mundo","Infantil");
		Libro libro3=new Libro(3,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes" );
		Libro libro4=new Libro(4,"Constitucion Española","Indef","España","Institucional");
		Libro libro5=new Libro(5,"Derecho Mercantil Español","Cristina","AAA","Educativo");
		Libro libro6=new Libro(6,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro7=new Libro(7,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro8=new Libro(8,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes" );
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);
		listaLibros.add(libro6);
		listaLibros.add(libro7);
		listaLibros.add(libro8);
		ArrayList<Revista> listaRevistas=new ArrayList<>();
		Revista revista1=new Revista(1,"GQ", "Editorial GQ",123,"Actualidad" );
		Revista revista2=new Revista(2,"FHM", "Editorial FHM",35,"Erotica");
		Revista revista3=new Revista(3,"MasQueCoches", "Editorial MasQueCoches",77,"Automovil" );
		Revista revista4=new Revista(4,"AutoSport", "Editorial AutoSport",23,"Automovil" );
		Revista revista5=new Revista(5,"MuyInteresante", "Editorial MuyInteresante",456,"Intelectual" );
		listaRevistas.add(revista1);
		listaRevistas.add(revista2);
		listaRevistas.add(revista3);
		listaRevistas.add(revista4);
		listaRevistas.add(revista5);
		
		Random randomPick=new Random();
		Libro libroEscogido=listaLibros.get(randomPick.nextInt(listaLibros.size()));
		model.addAttribute("nombreLibroDestacado",libroEscogido.getNombre());
		model.addAttribute("autorLibroDestacado",libroEscogido.getAutor());
		model.addAttribute("editorialLibroDestacado",libroEscogido.getEditorial());
		model.addAttribute("generoLibroDestacado",libroEscogido.getGenero());
		model.addAttribute("numeroEjemplaresLibroDestacado",3); //luego se saca de la base de datos
		
		Revista revistaEscogida=listaRevistas.get(randomPick.nextInt(listaRevistas.size()));
		model.addAttribute("nombreRevistaDestacada",revistaEscogida.getNombre());
		model.addAttribute("editorialRevistaDestacada",revistaEscogida.getEditorial());
		model.addAttribute("fasciculoRevistaDestacada",revistaEscogida.getFasciculo());
		model.addAttribute("generoRevistaDestacada",revistaEscogida.getGenero());
		model.addAttribute("numeroEjemplaresRevistaDestacada",3); //luego se saca de la base de datos
		return "sesionIniciada";
	}
	@RequestMapping("/buscadorLibros")
	public String buscadorLibros(Model model) {
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		model.addAttribute("visibleIniciarSesion",true);
		return "buscadorLibros";
		
	}
	@RequestMapping("/buscadorRevistas")
	public String buscadorRevista(Model model) {
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		model.addAttribute("visibleIniciarSesion",true);
		return "buscadorRevistas";
	}
	@RequestMapping("/reservaSalaTrabajoGrupo")
	public String reservaSalaTrabajoGrupo(Model model) {
		ArrayList<SalaTrabajoGrupo> listaSTG=new ArrayList<>();
		SalaTrabajoGrupo sala1=new SalaTrabajoGrupo(1,16,"Planta 1",true);
		SalaTrabajoGrupo sala2=new SalaTrabajoGrupo(2,3,"Planta 0",false);
		SalaTrabajoGrupo sala3=new SalaTrabajoGrupo(3,10,"Planta 2",true);
		SalaTrabajoGrupo sala4=new SalaTrabajoGrupo(4,5,"Planta 0",false);
		listaSTG.add(sala1);
		listaSTG.add(sala2);
		listaSTG.add(sala3);
		listaSTG.add(sala4);
		Random randomPick=new Random();
		SalaTrabajoGrupo salaEscogida=listaSTG.get(randomPick.nextInt(listaSTG.size()));
		model.addAttribute("capacidadSala",salaEscogida.getCapacidad());
		model.addAttribute("localizacionSala", salaEscogida.getLocalizacion());
		if(salaEscogida.isCompartida()) {
			model.addAttribute("compartidaSala", "Si");
		}else {
			model.addAttribute("compartidaSala", "No");
		}
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		model.addAttribute("visibleIniciarSesion",true);
		return "reservaSalaTrabajoGrupo";
	}
	@RequestMapping("/reservaEquipoInformatico")
	public String reservaEquipoInformatico(Model model) {
		ArrayList<EquipoInformatico> listaEquipo=new ArrayList<>();
		EquipoInformatico equipo1=new EquipoInformatico(1,"MacOSX","Equipo 45");
		EquipoInformatico equipo2=new EquipoInformatico(2,"Linux","Equipo 32");
		EquipoInformatico equipo3=new EquipoInformatico(3,"Windows","Equipo 2");
		EquipoInformatico equipo4=new EquipoInformatico(4,"MacOSX","Equipo 1");
		listaEquipo.add(equipo1);
		listaEquipo.add(equipo2);
		listaEquipo.add(equipo3);
		listaEquipo.add(equipo4);
		Random randomPick=new Random();
		EquipoInformatico equipoEscogido=listaEquipo.get(randomPick.nextInt(listaEquipo.size()));
		model.addAttribute("sistemaOperativoEquipo", equipoEscogido.getSistemaOperativo());
		model.addAttribute("localizacionEquipo", equipoEscogido.getLocalizacion());
		//NO HA INICIADO SESION
		//model.addAttribute("visibleCerrarSesion",false);
		model.addAttribute("visibleIniciarSesion",true);
		return "reservaEquipoInformatico";
	}
	@RequestMapping("/registro")
	public String registro(Model model) {
		return "registro";
	}
	@RequestMapping("/miPerfil")
	public String miPerfil(Model model) {
		Random randomPick=new Random();
		
		ArrayList<Libro> listaLibros=new ArrayList<>();
		Libro libro1=new Libro(1,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro2=new Libro(2,"Los Tres Cerditos", "Borja","El Mundo","Infantil");
		Libro libro3=new Libro(3,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes" );
		Libro libro4=new Libro(4,"Constitucion Española","Indef","España","Institucional");
		Libro libro5=new Libro(5,"Derecho Mercantil Español","Cristina","AAA","Educativo");
		Libro libro6=new Libro(6,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro7=new Libro(7,"Caperucita Roja", "Sergio","Anaya","Infantil" );
		Libro libro8=new Libro(8,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana","Viajes" );
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);
		listaLibros.add(libro6);
		listaLibros.add(libro7);
		listaLibros.add(libro8);
		
		ArrayList<Revista> listaRevistas=new ArrayList<>();
		Revista revista1=new Revista(1,"GQ", "Editorial GQ",123,"Actualidad" );
		Revista revista2=new Revista(2,"FHM", "Editorial FHM",35,"Erotica");
		Revista revista3=new Revista(3,"MasQueCoches", "Editorial MasQueCoches",77,"Automovil" );
		Revista revista4=new Revista(4,"AutoSport", "Editorial AutoSport",23,"Automovil" );
		Revista revista5=new Revista(5,"MuyInteresante", "Editorial MuyInteresante",456,"Intelectual" );
		listaRevistas.add(revista1);
		listaRevistas.add(revista2);
		listaRevistas.add(revista3);
		listaRevistas.add(revista4);
		listaRevistas.add(revista5);
		
		ArrayList<SalaTrabajoGrupo> listaSTG=new ArrayList<>();
		SalaTrabajoGrupo sala1=new SalaTrabajoGrupo(1,16,"Planta 1",true);
		SalaTrabajoGrupo sala2=new SalaTrabajoGrupo(2,3,"Planta 0",false);
		SalaTrabajoGrupo sala3=new SalaTrabajoGrupo(3,10,"Planta 2",true);
		SalaTrabajoGrupo sala4=new SalaTrabajoGrupo(4,5,"Planta 0",false);
		listaSTG.add(sala1);
		listaSTG.add(sala2);
		listaSTG.add(sala3);
		listaSTG.add(sala4);
		
		ArrayList<EquipoInformatico> listaEquipo=new ArrayList<>();
		EquipoInformatico equipo1=new EquipoInformatico(1,"MacOSX","Equipo 45");
		EquipoInformatico equipo2=new EquipoInformatico(2,"Linux","Equipo 32");
		EquipoInformatico equipo3=new EquipoInformatico(3,"Windows","Equipo 2");
		EquipoInformatico equipo4=new EquipoInformatico(4,"MacOSX","Equipo 1");
		listaEquipo.add(equipo1);
		listaEquipo.add(equipo2);
		listaEquipo.add(equipo3);
		listaEquipo.add(equipo4);
		
		Libro libroEscogido=listaLibros.get(randomPick.nextInt(listaLibros.size()));
		model.addAttribute("nombreLibro", libroEscogido.getNombre());
		model.addAttribute("autorLibro", libroEscogido.getAutor());
		model.addAttribute("editorialLibro", libroEscogido.getEditorial());
		model.addAttribute("generoLibro", libroEscogido.getGenero());
		model.addAttribute("diaFinReservaLibro", "3.03.2020");
		
		Revista revistaEscogida=listaRevistas.get(randomPick.nextInt(listaRevistas.size()));
		model.addAttribute("nombreRevista", revistaEscogida.getNombre());
		model.addAttribute("editorialRevista", revistaEscogida.getEditorial());
		model.addAttribute("fasciculoRevista", revistaEscogida.getFasciculo());
		model.addAttribute("generoRevista", revistaEscogida.getGenero());
		model.addAttribute("diaFinReservaRevista", "18.02.2020");
		
		SalaTrabajoGrupo salaEscogida=listaSTG.get(randomPick.nextInt(listaSTG.size()));
		model.addAttribute("capacidadSala", salaEscogida.getCapacidad());
		model.addAttribute("localizacionSala", salaEscogida.getLocalizacion());
		if(salaEscogida.isCompartida()) {
			model.addAttribute("compartidaSala", "Si");
		}else {
			model.addAttribute("compartidaSala", "No");
		}
		model.addAttribute("diaFinReservaSala", "5.02.2020");
		
		EquipoInformatico equipoEscogido=listaEquipo.get(randomPick.nextInt(listaEquipo.size()));
		model.addAttribute("sistemaOperativoEquipo", equipoEscogido.getSistemaOperativo());
		model.addAttribute("localizacionEquipo", equipoEscogido.getLocalizacion());
		model.addAttribute("diaFinReservaEquipo", "8.02.2020");
		
		return "miPerfil";
	}
	@RequestMapping("/editarPerfil")
	public String editarPerfil(Model model) {
		model.addAttribute("usuarioAdmin", true);
		return "editarPerfil";
	}
	@RequestMapping("/añadirRevista")
	public String añadirRevista(Model model) {
		return "añadirRevista";
	}
	@RequestMapping("/añadirSalaTrabajoGrupo")
	public String añadirSala(Model model) {
		return "añadirSalaTrabajoGrupo";
	}
	@RequestMapping("/añadirEquipoInformatico")
	public String añadirEquipoInformatico(Model model) {
		return "añadirEquipoInformatico";
	}
	@RequestMapping("/añadirLibro")
	public String añadirLibro(Model model) {
		return "añadirLibro";
	}
	@RequestMapping("/administrarUsuarios")
	public String administrarUsuarios(Model model) {
		return "administrarUsuarios";
	}
	

}
