package proteus.proveedor.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.proveedor.model.ProveedorAsesor;
import proteus.proveedor.repository.IProveedorAsesorRepository;
import proteus.proveedor.service.IProveedorAsesorService;

/**
 * Services for ProveedorAsesor Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ProveedorAsesorServiceImpl extends CRUDImpl<ProveedorAsesor, Integer> implements IProveedorAsesorService {

	@Autowired
	private IProveedorAsesorRepository proveedorAsesorRepository;
	
	@Override
	protected IGenericRepository<ProveedorAsesor, Integer> getRepository() {
		return proveedorAsesorRepository;
	}

	@Override
	public ProveedorAsesor getByTelefono(String telefono) throws Exception {
		return proveedorAsesorRepository.findByTelefono(telefono);
	}

	@Override
	public ProveedorAsesor getByNombreAndTelefonoAndProveedor(String nombre, String telefono, Integer idProveedor)
			throws Exception {
		return proveedorAsesorRepository.findByNombreAndTelefonoAndProveedor(nombre, telefono, idProveedor);
	}

	@Override
	public List<ProveedorAsesor> getByProveedor(Integer idProveedor) throws Exception {
		return proveedorAsesorRepository.findByProveedor(idProveedor);
	}

	@Override
	public ProveedorAsesor create(ProveedorAsesor proveedorAsesor) throws Exception {
		ProveedorAsesor proveedorAsesorAux = this.getByNombreAndTelefonoAndProveedor(proveedorAsesor.getNombre(), proveedorAsesor.getTelefono(), proveedorAsesor.getProveedor().getIdProveedor());
		if(proveedorAsesorAux==null) {
			proveedorAsesorAux = this.getByTelefono(proveedorAsesor.getTelefono());
			if(proveedorAsesorAux==null) {
				return proveedorAsesorRepository.save(proveedorAsesor);
			}
		}
		return null;
	}

	@Transactional
	@Override
	public void deleteByProveedor(Integer idProveedor) throws Exception {
		proveedorAsesorRepository.deleteByProveedor(idProveedor);
	}
	
}
