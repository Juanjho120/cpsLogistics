package proteus.inventario.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.inventario.model.InventarioDetalle;
import proteus.inventario.repository.IInventarioDetalleRepository;
import proteus.inventario.service.IInventarioDetalleService;

/**
 * Services for InventarioDetalle Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class InventarioDetalleServiceImpl extends CRUDImpl<InventarioDetalle, Integer> implements IInventarioDetalleService {

	@Autowired
	private IInventarioDetalleRepository inventarioDetalleRepository;

	@Override
	protected IGenericRepository<InventarioDetalle, Integer> getRepository() {
		return inventarioDetalleRepository;
	}

	@Transactional
	@Override
	public void deleteByInventario(Integer idInventario) throws Exception {
		inventarioDetalleRepository.deleteByInventario(idInventario);
	}

	@Override
	public List<InventarioDetalle> getByInventario(Integer idInventario) throws Exception {
		return inventarioDetalleRepository.findByInventario(idInventario);
	}
	
}