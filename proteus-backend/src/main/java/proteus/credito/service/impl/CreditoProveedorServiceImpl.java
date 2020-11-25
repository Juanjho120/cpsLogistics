package proteus.credito.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.credito.model.CreditoProveedor;
import proteus.credito.repository.ICreditoProveedorRepository;
import proteus.credito.service.ICreditoProveedorService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for CreditoProveedor Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class CreditoProveedorServiceImpl extends CRUDImpl<CreditoProveedor, Integer> implements ICreditoProveedorService {

	@Autowired
	private ICreditoProveedorRepository creditoProveedorRepository;

	@Override
	protected IGenericRepository<CreditoProveedor, Integer> getRepository() {
		return creditoProveedorRepository;
	}

	@Override
	public CreditoProveedor getByProveedor(Integer idProveedor) throws Exception {
		return creditoProveedorRepository.findByProveedor(idProveedor);
	}
	
	@Override
	public CreditoProveedor create(CreditoProveedor creditoProveedor) throws Exception {
		CreditoProveedor creditoProveedorAux = this.getByProveedor(creditoProveedor.getProveedor().getIdProveedor());
		if(creditoProveedorAux==null) {
			return creditoProveedorRepository.save(creditoProveedor);
		}
		return null;
	}

	@Override
	public void updateDeudaAcumulativa(Integer idCreditoProveedor, Double monto, Boolean suma) throws Exception {
		CreditoProveedor creditoProveedor = this.getById(idCreditoProveedor);
		if(creditoProveedor!=null) {
			if(suma) {
				creditoProveedor.setDeudaAcumulativa(creditoProveedor.getDeudaAcumulativa()+monto);
			} else {
				creditoProveedor.setDeudaAcumulativa(creditoProveedor.getDeudaAcumulativa()-monto);
			}
			this.update(creditoProveedor);
		}
		
	}
}