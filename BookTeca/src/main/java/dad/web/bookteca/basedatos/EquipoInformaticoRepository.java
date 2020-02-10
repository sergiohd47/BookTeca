package dad.web.bookteca.basedatos;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dad.web.bookteca.clases.EquipoInformatico;

public interface EquipoInformaticoRepository extends JpaRepository<EquipoInformatico, Long>{
	
	ArrayList<EquipoInformatico> findByDisponible(boolean disponibles);
	
	ArrayList<EquipoInformatico> findBySistemaOperativo(String so);
	
	ArrayList<EquipoInformatico> findById(int id);
	
	EquipoInformatico findByIdUsuario(int idUsuario);
}

