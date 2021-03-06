package proteus.placa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.placa.model.Placa;

/**
 * Repository for Placa Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IPlacaRepository extends IGenericRepository<Placa, Integer> {

	Placa findByNumero(String numero);
	
	@Query("FROM Placa WHERE marcaAuto.idMarcaAuto = :idMarcaAuto")
	List<Placa> findByMarcaAuto(Integer idMarcaAuto);
	
	@Query("FROM Placa WHERE idPlaca NOT IN (SELECT s.placa.idPlaca FROM Servicio s WHERE s.finalizado = 0)")
	List<Placa> findNotInService();
	
	@Query("FROM Placa WHERE idPlaca IN (SELECT s.placa.idPlaca FROM Servicio s WHERE s.finalizado = 0)")
	List<Placa> findInService();
	
}
