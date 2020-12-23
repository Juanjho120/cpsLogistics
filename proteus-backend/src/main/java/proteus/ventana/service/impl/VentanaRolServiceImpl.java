package proteus.ventana.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.ventana.model.VentanaRol;
import proteus.ventana.repository.IVentanaRolRepository;
import proteus.ventana.service.IVentanaRolService;

/**
 * Services for VentanaRol Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class VentanaRolServiceImpl extends CRUDImpl<VentanaRol, Integer> implements IVentanaRolService {

	@Autowired
	private IVentanaRolRepository ventanaRolRepository;

	@Override
	protected IGenericRepository<VentanaRol, Integer> getRepository() {
		return ventanaRolRepository;
	}
	
	
}
