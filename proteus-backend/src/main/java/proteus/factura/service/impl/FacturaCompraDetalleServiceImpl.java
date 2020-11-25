package proteus.factura.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.factura.model.FacturaCompraDetalle;
import proteus.factura.repository.IFacturaCompraDetalleRepository;
import proteus.factura.service.IFacturaCompraDetalleService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for FacturaCompraDetalle Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class FacturaCompraDetalleServiceImpl extends CRUDImpl<FacturaCompraDetalle, Integer> implements IFacturaCompraDetalleService {

	@Autowired
	private IFacturaCompraDetalleRepository facturaCompraDetalleRepository;

	@Override
	protected IGenericRepository<FacturaCompraDetalle, Integer> getRepository() {
		return facturaCompraDetalleRepository;
	}

	@Override
	public List<FacturaCompraDetalle> getByFacturaCompra(Integer idFacturaCompra) throws Exception {
		return facturaCompraDetalleRepository.findByFacturaCompra(idFacturaCompra);
	}

	@Transactional
	@Override
	public void deleteByFacturaCompra(Integer idFacturaCompra) throws Exception {
		facturaCompraDetalleRepository.deleteByFacturaCompra(idFacturaCompra);
	}
	
}