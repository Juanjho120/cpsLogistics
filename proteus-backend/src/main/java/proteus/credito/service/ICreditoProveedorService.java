package proteus.credito.service;

import proteus.credito.model.CreditoProveedor;
import proteus.generico.service.ICRUD;

/**
 * Services for CreditoProveedor Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ICreditoProveedorService extends ICRUD<CreditoProveedor, Integer> {

	CreditoProveedor getByProveedor(Integer idProveedor) throws Exception;
	void updateDeudaAcumulativa(Integer idCreditoProveedor, Double monto, Boolean suma) throws Exception;
	
}
