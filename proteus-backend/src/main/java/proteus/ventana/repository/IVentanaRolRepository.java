package proteus.ventana.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.ventana.model.VentanaRol;

/**
 * Repository for VentanaRol Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IVentanaRolRepository extends IGenericRepository<VentanaRol, Integer> {

}
