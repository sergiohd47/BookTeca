package dad.web.bookteca.controladores;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dad.web.bookteca.clases.Libro;
import dad.web.bookteca.clases.Revista;

@Controller
public class inicioController {
	private static final int NUMERO_RECURSOS_MAIN=3;
	@RequestMapping("/")
	public String inicio(Model model) {
		ArrayList<Libro> listaLibros=new ArrayList<>();
		Libro libro1=new Libro(1,"Caperucita Roja", "Sergio","Anaya",5,null );
		Libro libro2=new Libro(2,"Los Tres Cerditos", "Borja","El Mundo",2,null );
		Libro libro3=new Libro(3,"La Vuelta Al Mundo En 80 Dias", "Dani","Santillana",3,null );
		Libro libro4=new Libro(4,"Constitucion Española","Indef","España",2,null);
		Libro libro5=new Libro(5,"Derecho Mercantil Español","Cristina","AAA",1,null);
		listaLibros.add(libro1);
		listaLibros.add(libro2);
		listaLibros.add(libro3);
		listaLibros.add(libro4);
		listaLibros.add(libro5);
		ArrayList<Revista> listaRevistas=new ArrayList<>();
		Revista revista1=new Revista(1,"GQ", "Editorial GQ",123,10,null );
		Revista revista2=new Revista(2,"FHM", "Editorial FHM",35,2,null );
		Revista revista3=new Revista(3,"MasQueCoches", "Editorial MasQueCoches",77,8,null );
		Revista revista4=new Revista(4,"AutoSport", "Editorial AutoSport",23,5,null );
		Revista revista5=new Revista(5,"MuyInteresante", "Editorial MuyInteresante",456,15,null );
		listaRevistas.add(revista1);
		listaRevistas.add(revista2);
		listaRevistas.add(revista3);
		listaRevistas.add(revista4);
		listaRevistas.add(revista5);
		Random randomPick=new Random();
		for(int i=0;i<NUMERO_RECURSOS_MAIN;i++) {
			int numeroAleatorio=randomPick.nextInt(listaLibros.size());
			System.out.print(numeroAleatorio);
			Libro libroEscogido=listaLibros.get(numeroAleatorio);
			model.addAttribute("nombreLibro",libroEscogido.getNombre());
			model.addAttribute("autorLibro",libroEscogido.getAutor());
			model.addAttribute("editorialLibro",libroEscogido.getEditorial());
			model.addAttribute("numeroEjemplaresLibro",libroEscogido.getNumeroEjemplares());
			//model.addAttribute("listaGenerosLibro",libroEscogido.getGeneros());
		}
		for(int i=0;i<NUMERO_RECURSOS_MAIN;i++) {
			int numeroAleatorio=randomPick.nextInt(listaRevistas.size());
			System.out.println(numeroAleatorio);
			Revista revistaEscogida=listaRevistas.get(numeroAleatorio);
			model.addAttribute("nombreRevista",revistaEscogida.getNombre());
			model.addAttribute("editorialRevista",revistaEscogida.getEditorial());
			model.addAttribute("fasciculoRevista",revistaEscogida.getFasciculo());
			model.addAttribute("numeroEjemplaresRevista",revistaEscogida.getNumeroEjemplares());
			//model.addAttribute("listaGenerosRevista", revistaEscogida.getGeneros());
		}
		//NO HA INICIADO SESION
		model.addAttribute("visibleIniciarSesion", true);
		model.addAttribute("visibleCerrarSesion", false);
		
		//SESION INICIADA
		//model.addAttribute("visibleIniciarSesion", false);
		//model.addAttribute("visibleCerrarSesion", true);
		
		//model.addAttribute("listaLibros",listaLibros);
		//model.addAttribute("listaRevistas",listaRevistas);
		return "index";
	}

}
