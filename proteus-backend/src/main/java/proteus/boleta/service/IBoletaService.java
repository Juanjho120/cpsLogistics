package proteus.boleta.service;

import java.util.List;

import proteus.boleta.model.Boleta;
import proteus.generico.service.ICRUD;

/**
 * Services for Boleta Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IBoletaService extends ICRUD<Boleta, Integer> {

	Boleta getByNumeroAndCuentaBancaria(String numero, Integer idCuentaBancaria) throws Exception;
	List<Boleta> getByCuentaBancaria(Integer idCuentaBancaria) throws Exception;
	List<Boleta> getByBanco(Integer idBanco) throws Exception;
	List<Boleta> getByFecha(String fechaDesde, String fechaHasta) throws Exception;
	List<Boleta> getByCheque(Integer idCheque) throws Exception;
	List<Boleta> getByNumero(String numero) throws Exception;
	List<Boleta> getByBoletaTipoDocumento(Integer idBoletaTipoDocumento) throws Exception;
	
}
