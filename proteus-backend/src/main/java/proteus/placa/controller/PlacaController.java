package proteus.placa.controller;

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
import proteus.placa.model.Placa;
import proteus.placa.service.IPlacaService;

@RestController
@RequestMapping("/placas")
public class PlacaController {

	@Autowired
	private IPlacaService placaService;
	
	/**
	 * Obtiene todas las placas de la base de datos
	 * @return Listado de placas
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAll')")
	@GetMapping
	public ResponseEntity<List<Placa>> getAll() throws Exception {
		List<Placa> placaList = placaService.getAll();
		if(placaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran placas en la base de datos");
		}
		return new ResponseEntity<List<Placa>>(placaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las placas que no estan en servicio
	 * @return Listado de placas
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/not-in-service")
	public ResponseEntity<List<Placa>> getNotInService() throws Exception {
		List<Placa> placaList = placaService.getNotInService();
		if(placaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran placas libres");
		}
		return new ResponseEntity<List<Placa>>(placaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las placas que estan en servicio
	 * @return Listado de placas
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/in-service")
	public ResponseEntity<List<Placa>> getInService() throws Exception {
		List<Placa> placaList = placaService.getInService();
		if(placaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran placas en servicio");
		}
		return new ResponseEntity<List<Placa>>(placaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las placas de una marca de la base de datos
	 * @return Listado de placas
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/marca-auto/{idMarcaAuto}")
	public ResponseEntity<List<Placa>> getByMarcaAuto(@PathVariable("idMarcaAuto") Integer idMarcaAuto) throws Exception {
		List<Placa> placaList = placaService.getByMarcaAuto(idMarcaAuto);
		if(placaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran placas de la marca "+idMarcaAuto+" en la base de datos");
		}
		return new ResponseEntity<List<Placa>>(placaList, HttpStatus.OK);
	}
	
	/**
	 * Busca un credito de segmento por su id
	 * @param id
	 * @return Placa
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getById')")
	@GetMapping("/{id}")
	public ResponseEntity<Placa> getById(@PathVariable("id") Integer id) throws Exception {
		Placa placa = placaService.getById(id);
		if(placa == null) {
			throw new ModelNotFoundException("Placa con id " + id + " no encontrado");
		}
		return new ResponseEntity<Placa>(placa, HttpStatus.OK);
	}
	
	/**
	 * Busca una placa por su numero
	 * @param numero
	 * @return Placa
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/numero/{numero}")
	public ResponseEntity<Placa> getByNumero(@PathVariable("numero") String numero) throws Exception {
		Placa placa = placaService.getByNumero(numero);
		if(placa == null) {
			throw new ModelNotFoundException("Placa con numero " + numero + " no encontrada");
		}
		return new ResponseEntity<Placa>(placa, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva placa
	 * No lo guarda cuando encuentra otra placa con el mismo numero
	 * @param placaNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createServicio')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Placa placaNew) throws Exception {
		Placa placa = placaService.create(placaNew);
		if(placa==null) {
			throw new Exception("No se ha podido crear la placa");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la placa buscandolo por su id
	 * @param placaUp
	 * @return Placa actualizada
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateServicio')")
	@PutMapping
	public ResponseEntity<Placa> update(@Valid @RequestBody Placa placaUp) throws Exception {
		Placa placa = placaService.update(placaUp);
		return new ResponseEntity<Placa>(placa, HttpStatus.CREATED);
	}
	
	/**
	 * Actualiza el kilometraje de la placa buscandolo por su id
	 * @param placaUp
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateServicio')")
	@PutMapping("/kilometraje")
	public ResponseEntity<Void> updateUltimoKilometraje(@RequestBody Placa placa) throws Exception {
		placaService.updateUltimoKilometraje(placa.getIdPlaca(), placa.getUltimoKilometraje());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una placa de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteServicio')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Placa placa = placaService.getById(id);
		if(placa == null) {
			throw new ModelNotFoundException("Placa con id " + id + " no encontrado");
		}
		placaService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
