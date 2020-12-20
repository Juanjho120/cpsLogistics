package proteus.boleta.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.boleta.model.Boleta;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for Boleta Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IBoletaRepository extends IGenericRepository<Boleta, Integer> {

	@Query("FROM Boleta WHERE numero = :numero AND cuentaBancaria.idCuentaBancaria = :idCuentaBancaria")
	Boleta findByNumeroAndCuentaBancaria(String numero, Integer idCuentaBancaria);
	
	@Query("FROM Boleta WHERE cuentaBancaria.idCuentaBancaria = :idCuentaBancaria")
	List<Boleta> findByCuentaBancaria(Integer idCuentaBancaria);
	
	@Query("FROM Boleta WHERE cuentaBancaria.banco.idBanco = :idBanco")
	List<Boleta> findByBanco(Integer idBanco);
	
	@Query("FROM Boleta WHERE fecha BETWEEN :fechaDesde AND :fechaHasta")
	List<Boleta> findByFecha(LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query("FROM Boleta WHERE cheque.idCheque = :idCheque")
	List<Boleta> findByCheque(Integer idCheque);
	
	@Query("FROM Boleta WHERE boletaTipoDocumento.idBoletaTipoDocumento = :idBoletaTipoDocumento")
	List<Boleta> findByBoletaTipoDocumento(Integer idBoletaTipoDocumento);
	
	@Query("FROM Boleta WHERE numero LIKE :numero")
	List<Boleta> findByNumero(String numero);
	
}
