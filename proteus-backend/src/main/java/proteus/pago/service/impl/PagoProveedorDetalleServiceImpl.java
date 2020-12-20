package proteus.pago.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.pago.model.PagoProveedorDetalle;
import proteus.pago.repository.IPagoProveedorDetalleRepository;
import proteus.pago.service.IPagoProveedorDetalleService;

/**
 * Services for PagoProveedorDetalle Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class PagoProveedorDetalleServiceImpl extends CRUDImpl<PagoProveedorDetalle, Integer> implements IPagoProveedorDetalleService {

	@Autowired
	private IPagoProveedorDetalleRepository pagoProveedorDetalleRepository;

	@Override
	protected IGenericRepository<PagoProveedorDetalle, Integer> getRepository() {
		return pagoProveedorDetalleRepository;
	}
	
	
}
