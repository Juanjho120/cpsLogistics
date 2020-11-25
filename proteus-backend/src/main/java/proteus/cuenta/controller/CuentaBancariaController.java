package proteus.cuenta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proteus.cuenta.model.CuentaBancaria;
import proteus.cuenta.service.ICuentaBancariaService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/cuentas-bancarias")
public class CuentaBancariaController {

	@Autowired
	private ICuentaBancariaService cuentaBancariaService;
	
	/**
	 * Obtiene todas las cuentas bancarias en la base de datos
	 * @return Listado de cuentas bancarias
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<CuentaBancaria>> getAll() throws Exception {
		List<CuentaBancaria> cuentaBancariaList = cuentaBancariaService.getAll();
		if(cuentaBancariaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cuentas bancarias en la base de datos");
		}
		return new ResponseEntity<List<CuentaBancaria>>(cuentaBancariaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las cuentas bancarias de una categoria
	 * @param idCategoria
	 * @return Listado de cuentas bancarias
	 * @throws Exception
	 */
	@GetMapping("/categoria/{idCategoria}")
	public ResponseEntity<List<CuentaBancaria>> getByCategoria(@PathVariable("idCategoria") Integer idCategoria) throws Exception {
		List<CuentaBancaria> cuentaBancariaList = cuentaBancariaService.getByCategoria(idCategoria);
		if(cuentaBancariaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cuentas bancarias para la categoria "+idCategoria+" en la base de datos");
		}
		return new ResponseEntity<List<CuentaBancaria>>(cuentaBancariaList, HttpStatus.OK);
	}
	
	/**
	 * Busca una cuenta bancaria por su id
	 * @param id
	 * @return Cuenta bancaria
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CuentaBancaria> getById(@PathVariable("id") Integer id) throws Exception {
		CuentaBancaria cuentaBancaria = cuentaBancariaService.getById(id);
		if(cuentaBancaria == null) {
			throw new ModelNotFoundException("Cuenta bancaria con id " + id + " no encontrado");
		}
		return new ResponseEntity<CuentaBancaria>(cuentaBancaria, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las cuentas bancarias de una categoria e item
	 * @param idCategoria
	 * @param idItem
	 * @return Listado de cuentas bancarias
	 * @throws Exception
	 */
	@GetMapping("/categoria/{idCategoria}/item/{idItem}")
	public ResponseEntity<List<CuentaBancaria>> getByCategoriaAndItem(@PathVariable("idCategoria") Integer idCategoria, 
			@PathVariable("idItem") Integer idItem) throws Exception {
		List<CuentaBancaria> cuentaBancariaList = cuentaBancariaService.getByCategoriaAndIdItem(idCategoria, idItem);
		if(cuentaBancariaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cuentas bancarias para la categoria " + idCategoria 
					+ "  y el item " + idItem + " en la base de datos");
		}
		return new ResponseEntity<List<CuentaBancaria>>(cuentaBancariaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las cuentas bancarias de un tipo
	 * @param idCuentaBancariaTipo
	 * @return Listado de cuentas bancarias
	 * @throws Exception
	 */
	@GetMapping("/tipo/{idCuentaBancariaTipo}")
	public ResponseEntity<List<CuentaBancaria>> getByCuentaBancariaTipo(@PathVariable("idCuentaBancariaTipo")
				Integer idCuentaBancariaTipo) throws Exception {
		List<CuentaBancaria> cuentaBancariaList = cuentaBancariaService.getByCuentaBancariaTipo(idCuentaBancariaTipo);
		if(cuentaBancariaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cuentas bancarias del tipo " + idCuentaBancariaTipo + " en la base de datos");
		}
		return new ResponseEntity<List<CuentaBancaria>>(cuentaBancariaList, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva cuenta bancaria
	 * No lo guarda cuando encuentra otra cuenta bancaria con el mismo numero y banco
	 * @param cuentaBancariaNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody CuentaBancaria cuentaBancariaNew) throws Exception {
		CuentaBancaria cuentaBancaria = cuentaBancariaService.create(cuentaBancariaNew);
		if(cuentaBancaria==null) {
			throw new Exception("No se ha podido crear la cuenta bancaria");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la cuenta bancaria buscandolo por su id
	 * @param cuentaBancariaUp
	 * @return Cuenta bancaria actualizada
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<CuentaBancaria> update(@Valid @RequestBody CuentaBancaria cuentaBancariaUp) throws Exception {
		CuentaBancaria cuentaBancaria = cuentaBancariaService.update(cuentaBancariaUp);
		return new ResponseEntity<CuentaBancaria>(cuentaBancaria, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una cuenta bancaria de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		CuentaBancaria cuentaBancaria = cuentaBancariaService.getById(id);
		if(cuentaBancaria == null) {
			throw new ModelNotFoundException("Cuenta bancaria con id " + id + " no encontrado");
		}
		cuentaBancariaService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Elimina las cuentas bancarias de la base de datos pertenecientes a una categoria e item
	 * @param idCategoria
	 * @param idItem
	 * @throws Exception
	 */
	@DeleteMapping("/categoria/{idCategoria}/item/{idItem}")
	public ResponseEntity<Void> deleteByCategoriaAndItem(@PathVariable("idCategoria") Integer idCategoria, 
			@PathVariable("idItem") Integer idItem) throws Exception {
		List<CuentaBancaria> cuentaBancariaList = cuentaBancariaService.getByCategoriaAndIdItem(idCategoria, idItem);
		if(cuentaBancariaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cuentas bancarias para la categoria " + idCategoria 
					+ "  y el item " + idItem + " en la base de datos");
		}
		cuentaBancariaService.deleteByCategoriaAndIdItem(idCategoria, idItem);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
