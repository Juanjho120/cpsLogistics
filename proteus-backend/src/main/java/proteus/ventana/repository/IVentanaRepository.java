package proteus.ventana.repository;

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

}
