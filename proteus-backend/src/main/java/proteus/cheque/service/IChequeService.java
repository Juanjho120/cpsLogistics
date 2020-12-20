package proteus.cheque.service;

import java.util.List;

import proteus.cheque.model.Cheque;
import proteus.generico.service.ICRUD;

/**
 * Services for Cheque Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IChequeService extends ICRUD<Cheque, Integer> {

	Cheque getByNumeroAndCuentaBancaria(String numero, Integer idCuentaBancaria) throws Exception;
	List<Cheque> getByCuentaBancaria(Integer idCuentaBancaria) throws Exception;
	List<Cheque> getByBanco(Integer idBanco) throws Exception;
	List<Cheque> getByNombre(String nombre) throws Exception;
	List<Cheque> getByNumero(String numero) throws Exception;
	List<Cheque> getByFechaEmision(String fechaDesde, String fechaHasta) throws Exception;
	List<Cheque> getByFechaDeposito(String fechaDesde, String fechaHasta) throws Exception;
	List<Cheque> getByFacturaNumeroInSegmentoPago(String facturaNumero) throws Exception;
	List<Cheque> getByFacturaCodigoInPagoProveedor(String codigo) throws Exception;
	List<Cheque> getNotInBoletas() throws Exception;
	List<Cheque> getNotInBoletasExceptCheque(Integer idCheque) throws Exception;
	List<Cheque> getInBoletas() throws Exception;
	Boolean isChequeInPagoProveedor(Integer idCheque) throws Exception;
	Boolean isChequeInSegmentoPago(Integer idCheque) throws Exception;
	
}
