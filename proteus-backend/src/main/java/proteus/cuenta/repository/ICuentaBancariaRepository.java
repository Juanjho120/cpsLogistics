package proteus.cuenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.cuenta.model.CuentaBancaria;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for CuentaBancaria Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICuentaBancariaRepository extends IGenericRepository<CuentaBancaria, Integer> {

	@Query("FROM CuentaBancaria WHERE numero = :numero AND banco.idBanco = :idBanco")
	CuentaBancaria findByNumeroAndBanco(String numero, Integer idBanco);
	
	@Query("FROM CuentaBancaria WHERE categoria.idCategoria = :idCategoria")
	List<CuentaBancaria> findByCategoria(Integer idCategoria);
	
	@Query("FROM CuentaBancaria WHERE categoria.idCategoria = :idCategoria AND idItem = :idItem")
	List<CuentaBancaria> findByCategoriaAndIdItem(Integer idCategoria, Integer idItem);
	
	@Query("FROM CuentaBancaria WHERE cuentaBancariaTipo.idCuentaBancariaTipo = :idCuentaBancariaTipo")
	List<CuentaBancaria> findByCuentaBancariaTipo(Integer idCuentaBancariaTipo);
	
	@Modifying
	@Query("DELETE FROM CuentaBancaria WHERE categoria.idCategoria = :idCategoria AND idItem = :idItem")
	void deleteByCategoriaAndIdItem(Integer idCategoria, Integer idItem);
	
}
