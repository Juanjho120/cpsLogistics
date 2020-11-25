package proteus.proveedor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.proveedor.dto.ProveedorDTO;
import proteus.proveedor.model.Proveedor;
import proteus.proveedor.model.ProveedorAsesor;
import proteus.proveedor.repository.IProveedorRepository;
import proteus.proveedor.service.IProveedorService;

/**
 * Services for Proveedor Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ProveedorServiceImpl extends CRUDImpl<Proveedor, Integer> implements IProveedorService {

	@Autowired
	private IProveedorRepository proveedorRepository;
	
	@Autowired
	private ProveedorAsesorServiceImpl proveedorAsesorService;

	@Override
	protected IGenericRepository<Proveedor, Integer> getRepository() {
		return proveedorRepository;
	}

	@Override
	public Proveedor getByNombreAndNit(String nombre, String nit) throws Exception {
		return proveedorRepository.findByNombreAndNit(nombre, nit);
	}
	
	@Override
	public Proveedor getByNit(String nit) throws Exception {
		return proveedorRepository.findByNit(nit);
	}
	
	@Override
	public Proveedor create(Proveedor proveedor) throws Exception {
		Proveedor proveedorAux = this.getByNit(proveedor.getNit());
		if(proveedorAux==null) {
			proveedorAux = this.getByNombreAndNit(proveedor.getNombre(), proveedor.getNit());
			if(proveedorAux==null) {
				return proveedorRepository.save(proveedor);
			}
		}
		return null;
	}
	
	@Override
	public void delete(Integer idProveedor) throws Exception {
		List<ProveedorAsesor> proveedorAsesorList = proveedorAsesorService.getByProveedor(idProveedor);
		if(!proveedorAsesorList.isEmpty()) {
			proveedorAsesorService.deleteByProveedor(idProveedor);
		}
		proveedorRepository.deleteById(idProveedor);
	}

	@Override
	public List<ProveedorDTO> getAllDTO() throws Exception {
		List<ProveedorDTO> proveedorDTOList = new ArrayList<ProveedorDTO>();
		List<Proveedor> proveedorList = this.getAll();
		for(Proveedor proveedor : proveedorList) {
			ProveedorDTO proveedorDTO = new ProveedorDTO();
			proveedorDTO.setProveedor(proveedor);
			List<ProveedorAsesor> proveedorAsesorList = proveedorAsesorService.getByProveedor(proveedor.getIdProveedor());
			proveedorDTO.setProveedorAsesor(proveedorAsesorList);
			proveedorDTOList.add(proveedorDTO);
		}
		return proveedorDTOList;
	}

	
	
}