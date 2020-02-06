package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import dad.web.bookteca.clases.EquipoInformatico;

public interface RepositorioEquiposInformaticos extends JpaRepository<EquipoInformatico, Integer>{

	ArrayList<EquipoInformatico> buscarDisponibles(boolean disponibles);
	
	ArrayList<EquipoInformatico> buscarPorSistemaOperativo(String so);
	
	ArrayList<EquipoInformatico> buscarPorId(int id);
	
	EquipoInformatico buscarPorUsuario(int idUsuario);
}


