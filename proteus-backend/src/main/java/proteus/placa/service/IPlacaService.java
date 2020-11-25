package proteus.placa.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.placa.model.Placa;

/**
 * Services for Placa Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IPlacaService extends ICRUD<Placa, Integer> {

	Placa getByNumero(String numero) throws Exception;
	List<Placa> getByMarcaAuto(Integer idMarcaAuto) throws Exception;
	void updateUltimoKilometraje(Integer idPlaca, Integer ultimoKilometraje) throws Exception;
	
}
