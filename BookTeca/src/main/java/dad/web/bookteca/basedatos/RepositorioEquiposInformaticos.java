package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.EquipoInformatico;

public interface RepositorioEquiposInformaticos extends JpaRepository<EquipoInformatico, Integer>{
	@Query(value="select equipoInformatico from EquipoInformatico equipoInformatico where equipoInformatico.disponible=?1")
	ArrayList<EquipoInformatico> buscarDisponibles(boolean disponibles);
	@Query(value="select equipoInformatico from EquipoInformatico equipoInformatico where equipoInformatico.sistemaOperativo=?1")
	ArrayList<EquipoInformatico> buscarPorSistemaOperativo(String so);
	
	ArrayList<EquipoInformatico> buscarPorId(int id);
	
	EquipoInformatico buscarPorUsuario(int idUsuario);
}


