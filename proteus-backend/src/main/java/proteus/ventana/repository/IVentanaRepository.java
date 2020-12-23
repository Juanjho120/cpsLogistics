package proteus.ventana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.ventana.model.Ventana;

/**
 * Repository for Ventana Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IVentanaRepository extends IGenericRepository<Ventana, Integer> {

	@Query(value = "SELECT DISTINCT v.* FROM ventanas_roles vr "
			+ "		INNER JOIN usuario_rol ur ON ur.id_rol = vr.id_rol "
			+ "		INNER JOIN ventanas v ON v.id_ventana = vr.id_ventana "
			+ "		INNER JOIN usuarios u ON u.id_usuario = ur.id_usuario "
			+ "WHERE u.username = :username", nativeQuery=true)
	List<Ventana> findByUsername(String username);
	
}
