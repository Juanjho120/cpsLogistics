package proteus.repuesto.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.repuesto.model.Repuesto;
import proteus.repuesto.repository.IRepuestoRepository;
import proteus.repuesto.service.IRepuestoService;

/**
 * Services for Repuesto Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class RepuestoServiceImpl extends CRUDImpl<Repuesto, Integer> implements IRepuestoService {

	@Autowired
	private IRepuestoRepository repuestoRepository;

	@Override
	protected IGenericRepository<Repuesto, Integer> getRepository() {
		return repuestoRepository;
	}

	@Override
	public List<Repuesto> getByCodigo(String codigo) throws Exception {
		return repuestoRepository.findByCodigo(codigo);
	}

	@Override
	public List<Repuesto> getByCodigoBarra(String codigoBarra) throws Exception {
		return repuestoRepository.findByCodigoBarra(codigoBarra);
	}
	
	@Override
	public Repuesto create(Repuesto repuesto) throws Exception {
		List<Repuesto> repuestoCodigoList = this.getByCodigo(repuesto.getCodigo());
		List<Repuesto> repuestoCodigoBarraList = this.getByCodigoBarra(repuesto.getCodigoBarra());
		if(repuestoCodigoList.isEmpty() && repuestoCodigoBarraList.isEmpty()) {
			return repuestoRepository.save(repuesto);
		} else if(repuesto.getCodigo().equalsIgnoreCase("")) {
			repuestoCodigoBarraList = this.getByCodigoBarra(repuesto.getCodigoBarra());
			if(repuestoCodigoBarraList.isEmpty()) {
				return repuestoRepository.save(repuesto);
			} else if(repuesto.getCodigoBarra().equalsIgnoreCase("")) {
				return repuestoRepository.save(repuesto);
			}
		} else if(repuesto.getCodigoBarra().equalsIgnoreCase("")) {
			repuestoCodigoList = this.getByCodigo(repuesto.getCodigo());
			if(repuestoCodigoList.isEmpty()) {
				return repuestoRepository.save(repuesto);
			} else if(repuesto.getCodigo().equalsIgnoreCase("")) {
				return repuestoRepository.save(repuesto);
			}
		}
		return null;
	}
	
	@Override
	public Repuesto update(Repuesto repuesto) throws Exception {
		List<Repuesto> repuestoCodigoList;
		List<Repuesto> repuestoCodigoBarraList;
		Boolean repiteCodigo = false;
		Boolean repiteCodigoBarra = false;
		if(repuesto.getCodigo()!=null && repuesto.getCodigoBarra()!=null) {
			if(!repuesto.getCodigo().equalsIgnoreCase("") && !repuesto.getCodigoBarra().equalsIgnoreCase("")) {
				repuestoCodigoList = this.getByCodigo(repuesto.getCodigo());
				repuestoCodigoBarraList = this.getByCodigoBarra(repuesto.getCodigoBarra());
				
				for(Repuesto repuestoAux : repuestoCodigoList) {
					if(repuestoAux.getIdRepuesto()!=repuesto.getIdRepuesto()) {
						repiteCodigo = true;
					}
				}
				
				for(Repuesto repuestoAux : repuestoCodigoBarraList) {
					if(repuestoAux.getIdRepuesto()!=repuesto.getIdRepuesto()) {
						repiteCodigoBarra = true;
					}
				}
				
				if(!repiteCodigo && !repiteCodigoBarra) {
					return repuestoRepository.save(repuesto);
				}
			} else if(!repuesto.getCodigo().equalsIgnoreCase("")) {
				repuestoCodigoList = this.getByCodigo(repuesto.getCodigo());
				for(Repuesto repuestoAux : repuestoCodigoList) {
					if(repuestoAux.getIdRepuesto()!=repuesto.getIdRepuesto()) {
						repiteCodigo = true;
					}
				}
				
				if(!repiteCodigo) {
					return repuestoRepository.save(repuesto);
				}
			} else if(!repuesto.getCodigoBarra().equalsIgnoreCase("")) {
				repuestoCodigoBarraList = this.getByCodigoBarra(repuesto.getCodigoBarra());
				for(Repuesto repuestoAux : repuestoCodigoBarraList) {
					if(repuestoAux.getIdRepuesto()!=repuesto.getIdRepuesto()) {
						repiteCodigoBarra = true;
					}
				}
				
				if(!repiteCodigoBarra) {
					return repuestoRepository.save(repuesto);
				}
			} else if(repuesto.getCodigo().equalsIgnoreCase("") || repuesto.getCodigoBarra().equalsIgnoreCase("")) {
				return repuestoRepository.save(repuesto);
			}
		}
		return null;
	}

	@Transactional
	@Override
	public void deleteByCodigo(String codigo) throws Exception {
		repuestoRepository.deleteByCodigo(codigo);
	}

	@Transactional
	@Override
	public void deleteByCodigoBarra(String codigoBarra) throws Exception {
		repuestoRepository.deleteByCodigoBarra(codigoBarra);
	}

	@Override
	public void actualizarCantidad(Integer idRepuesto, Integer cantidad, Boolean sumar) throws Exception {
		Repuesto repuesto = this.getById(idRepuesto);
		if(repuesto != null) {
			if(sumar) {
				repuesto.setExistencia(repuesto.getExistencia() + cantidad);
			} else {
				repuesto.setExistencia(repuesto.getExistencia() - cantidad);
			}
			repuestoRepository.save(repuesto);
		}
	}

	@Override
	public String verStock(Integer idRepuesto, Integer cantidad, String mensaje) throws Exception {
		String stockMensaje = "";
		Repuesto repuesto = this.getById(idRepuesto);
		if(repuesto != null) {
			if(!((repuesto.getExistencia()>0) && (repuesto.getExistencia() >= cantidad))) {
				stockMensaje = "El repuesto " + repuesto.getCodigo() + " " + repuesto.getDescripcion() 
					+ " no hay suficiente en stock para " + mensaje;
			}
		}
		return stockMensaje;
	}
	
	
}