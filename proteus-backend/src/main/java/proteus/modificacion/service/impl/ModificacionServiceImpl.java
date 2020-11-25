package proteus.modificacion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.modificacion.model.Modificacion;
import proteus.modificacion.repository.IModificacionRepository;
import proteus.modificacion.service.IModificacionService;

/**
 * Services for Modificacion Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ModificacionServiceImpl extends CRUDImpl<Modificacion, Integer> implements IModificacionService {

	@Autowired
	private IModificacionRepository modificacionRepository;

	@Override
	protected IGenericRepository<Modificacion, Integer> getRepository() {
		return modificacionRepository;
	}

	@Override
	public List<Modificacion> getByUsuario(Integer idUsuario) throws Exception {
		return modificacionRepository.findByUsuario(idUsuario);
	}

	@Override
	public List<Modificacion> getByConcepto(Integer idConcepto) throws Exception {
		return modificacionRepository.findByConcepto(idConcepto);
	}

	@Override
	public List<Modificacion> getByTabla(String tabla) throws Exception {
		return modificacionRepository.findByTabla(tabla);
	}

	@Override
	public List<Modificacion> getByConceptoAndTabla(Integer idConcepto, String tabla) throws Exception {
		return modificacionRepository.findByConceptoAndTabla(idConcepto, tabla);
	}
	
}