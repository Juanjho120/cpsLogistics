package proteus.cuenta.service;

import java.util.List;

import proteus.cuenta.model.CuentaBancaria;
import proteus.generico.service.ICRUD;

/**
 * Services for CuentaBancaria Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ICuentaBancariaService extends ICRUD<CuentaBancaria, Integer> {

	CuentaBancaria getByNumeroAndBanco(String numero, Integer idBanco) throws Exception;
	List<CuentaBancaria> getByCategoria(Integer idCategoria) throws Exception;
	List<CuentaBancaria> getByCategoriaAndIdItem(Integer idCategoria, Integer idItem) throws Exception;
	List<CuentaBancaria> getByCuentaBancariaTipo(Integer idCuentaBancariaTipo) throws Exception;
	void deleteByCategoriaAndIdItem(Integer idCategoria, Integer idItem) throws Exception;
}
