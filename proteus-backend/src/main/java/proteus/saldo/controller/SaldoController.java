package proteus.saldo.controller;

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

import proteus.exception.ModelNotFoundException;
import proteus.saldo.model.Saldo;
import proteus.saldo.service.ISaldoService;

@RestController
@RequestMapping("/saldos")
public class SaldoController {

	@Autowired
	private ISaldoService saldoService;
	
	/**
	 * Obtiene todos los saldos en la base de datos
	 * @return Listado de saldos
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Saldo>> getAll() throws Exception {
		List<Saldo> saldoList = saldoService.getAll();
		if(saldoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran saldos en la base de datos");
		}
		return new ResponseEntity<List<Saldo>>(saldoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un saldo por su id
	 * @param id
	 * @return Saldo
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Saldo> getById(@PathVariable("id") Integer id) throws Exception {
		Saldo saldo = saldoService.getById(id);
		if(saldo == null) {
			throw new ModelNotFoundException("Saldo con id " + id + " no encontrado");
		}
		return new ResponseEntity<Saldo>(saldo, HttpStatus.OK);
	}
	
	/**
	 * Busca un saldo por su nombre
	 * @param nombre
	 * @return Saldo
	 * @throws Exception
	 */
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<Saldo> getByNombre(@PathVariable("nombre") String nombre) throws Exception {
		Saldo saldo = saldoService.getByNombre(nombre);
		if(saldo == null) {
			throw new ModelNotFoundException("Saldo no encontrado");
		}
		return new ResponseEntity<Saldo>(saldo, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo saldo
	 * No lo guarda cuando encuentra otro saldo con el mismo nombre
	 * @param saldoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Saldo saldoNew) throws Exception {
		Saldo saldo = saldoService.create(saldoNew);
		if(saldo==null) {
			throw new Exception("No se ha podido crear el saldo");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del saldo buscandolo por su id
	 * @param saldoUp
	 * @return Saldo actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Saldo> update(@Valid @RequestBody Saldo saldoUp) throws Exception {
		Saldo saldo = saldoService.update(saldoUp);
		return new ResponseEntity<Saldo>(saldo, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un saldo de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Saldo saldo = saldoService.getById(id);
		if(saldo == null) {
			throw new ModelNotFoundException("Saldo con id " + id + " no encontrado");
		}
		saldoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
