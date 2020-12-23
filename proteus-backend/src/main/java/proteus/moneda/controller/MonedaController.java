package proteus.moneda.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proteus.exception.ModelNotFoundException;
import proteus.moneda.model.Moneda;
import proteus.moneda.service.IMonedaService;

@RestController
@RequestMapping("/monedas")
public class MonedaController {

	@Autowired
	private IMonedaService monedaService;
	
	/**
	 * Obtiene todas las monedas en la base de datos
	 * @return Listado de monedas
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllContable')")
	@GetMapping
	public ResponseEntity<List<Moneda>> getAll() throws Exception {
		List<Moneda> monedaList = monedaService.getAll();
		if(monedaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran monedas en la base de datos");
		}
		return new ResponseEntity<List<Moneda>>(monedaList, HttpStatus.OK);
	}
	
	/**
	 * Busca una moneda por su id
	 * @param id
	 * @return Moneda
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdContable')")
	@GetMapping("/{id}")
	public ResponseEntity<Moneda> getById(@PathVariable("id") Integer id) throws Exception {
		Moneda moneda = monedaService.getById(id);
		if(moneda == null) {
			throw new ModelNotFoundException("Moneda con id " + id + " no encontrado");
		}
		return new ResponseEntity<Moneda>(moneda, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva moneda
	 * No lo guarda cuando encuentra otra moneda con el mismo nombre y simbolo
	 * @param monedaNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createContable')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Moneda monedaNew) throws Exception {
		Moneda moneda = monedaService.create(monedaNew);
		if(moneda==null) {
			throw new Exception("No se ha podido crear la moneda");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la moneda buscandolo por su id
	 * @param monedaUp
	 * @return Moneda actualizada
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateContable')")
	@PutMapping
	public ResponseEntity<Moneda> update(@Valid @RequestBody Moneda monedaUp) throws Exception {
		Moneda moneda = monedaService.update(monedaUp);
		return new ResponseEntity<Moneda>(moneda, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una moneda de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteContable')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Moneda moneda = monedaService.getById(id);
		if(moneda == null) {
			throw new ModelNotFoundException("Moneda con id " + id + " no encontrado");
		}
		monedaService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
