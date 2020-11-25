package proteus.ventana.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.ventana.model.Ventana;
import proteus.ventana.repository.IVentanaRepository;
import proteus.ventana.service.IVentanaService;

/**
 * Services for Ventana Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class VentanaServiceImpl extends CRUDImpl<Ventana, Integer> implements IVentanaService {

	@Autowired
	private IVentanaRepository ventanaRepository;

	@Override
	protected IGenericRepository<Ventana, Integer> getRepository() {
		return ventanaRepository;
	}
	
}