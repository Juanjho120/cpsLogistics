package proteus.pago.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.pago.model.PagoProveedorDetalle;

/**
 * Repository for PagoProveedorDetalle Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IPagoProveedorDetalleRepository extends IGenericRepository<PagoProveedorDetalle, Integer> {

}
