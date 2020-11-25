package proteus.caja.controller;

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

import proteus.caja.model.CajaChica;
import proteus.caja.service.ICajaChicaService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/cajas-chicas")
public class CajaChicaController {

	@Autowired
	private ICajaChicaService cajaChicaService;
	
	/**
	 * Obtiene todas las cajas chicas de la base de datos
	 * @return Listado de cajas chicas
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<CajaChica>> getAll() throws Exception {
		List<CajaChica> cajaChicaList = cajaChicaService.getAll();
		if(cajaChicaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cajas chicas en la base de datos");
		}
		return new ResponseEntity<List<CajaChica>>(cajaChicaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las cajas chicas por servicio de la base de datos
	 * @param idServicio
	 * @return Listado de cajas chicas
	 * @throws Exception
	 */
	@GetMapping("/servicio/{idServicio}")
	public ResponseEntity<List<CajaChica>> getByServicio(@PathVariable("idServicio") Integer idServicio) throws Exception {
		List<CajaChica> cajaChicaList = cajaChicaService.getByServicio(idServicio);
		if(cajaChicaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cajas chicas para el servicio "+idServicio+" en la base de datos");
		}
		return new ResponseEntity<List<CajaChica>>(cajaChicaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las cajas chicas por proveedor de la base de datos
	 * @param idProveedor
	 * @return Listado de cajas chicas
	 * @throws Exception
	 */
	@GetMapping("/proveedor/{idProveedor}")
	public ResponseEntity<List<CajaChica>> getByProveedor(@PathVariable("idProveedor") Integer idProveedor) throws Exception {
		List<CajaChica> cajaChicaList = cajaChicaService.getByProveedor(idProveedor);
		if(cajaChicaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cajas chicas para el proveedor "+idProveedor+" en la base de datos");
		}
		return new ResponseEntity<List<CajaChica>>(cajaChicaList, HttpStatus.OK);
	}
	
	/**
	 * Busca una caja chica por su id
	 * @param id
	 * @return Caja chica
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CajaChica> getById(@PathVariable("id") Integer id) throws Exception {
		CajaChica cajaChica = cajaChicaService.getById(id);
		if(cajaChica == null) {
			throw new ModelNotFoundException("Caja chica con id " + id + " no encontrado");
		}
		return new ResponseEntity<CajaChica>(cajaChica, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva caja chica
	 * No lo guarda cuando encuentra otra caja chica con el mismo proveedor y codigo de factura
	 * @param cajaChicaNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody CajaChica cajaChicaNew) throws Exception {
		CajaChica cajaChica = cajaChicaService.create(cajaChicaNew);
		if(cajaChica==null) {
			throw new Exception("No se ha podido crear la caja chica");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la caja chica buscandolo por su id
	 * @param cajaChicaUp
	 * @return Caja chica actualizada
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<CajaChica> update(@Valid @RequestBody CajaChica cajaChicaUp) throws Exception {
		CajaChica cajaChica = cajaChicaService.update(cajaChicaUp);
		return new ResponseEntity<CajaChica>(cajaChica, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una caja chica de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		CajaChica cajaChica = cajaChicaService.getById(id);
		if(cajaChica == null) {
			throw new ModelNotFoundException("Caja chica con id " + id + " no encontrado");
		}
		cajaChicaService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
