package proteus.transaccion.controller;

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
import proteus.transaccion.model.Transaccion;
import proteus.transaccion.service.ITransaccionService;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

	@Autowired
	private ITransaccionService transaccionService;
	
	/**
	 * Obtiene todas las transacciones en la base de datos
	 * @return Listado de transacciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllContable')")
	@GetMapping
	public ResponseEntity<List<Transaccion>> getAll() throws Exception {
		List<Transaccion> transaccionList = transaccionService.getAll();
		if(transaccionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran transacciones en la base de datos");
		}
		return new ResponseEntity<List<Transaccion>>(transaccionList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las transacciones que pagan una factura
	 * @param facturaNumero
	 * @return Listado de transacciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/factura-numero/{facturaNumero}")
	public ResponseEntity<List<Transaccion>> getByFacturaNumeroInSegmentoPago(@PathVariable("facturaNumero") String facturaNumero) throws Exception {
		List<Transaccion> transaccionList = transaccionService.getByFacturaNumeroInSegmentoPago(facturaNumero);
		if(transaccionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran transacciones");
		}
		return new ResponseEntity<List<Transaccion>>(transaccionList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las transacciones por fecha de aprobacion
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de transacciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/fecha-aprobacion/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Transaccion>> getByFechaAprobacion(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Transaccion> transaccionList = transaccionService.getByFechaAprobacion(fechaDesde, fechaHasta);
		if(transaccionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran transacciones");
		}
		return new ResponseEntity<List<Transaccion>>(transaccionList, HttpStatus.OK);
	}
	
	/**
	 * Busca una transaccion por su id
	 * @param id
	 * @return Transaccion
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/{id}")
	public ResponseEntity<Transaccion> getById(@PathVariable("id") Integer id) throws Exception {
		Transaccion transaccion = transaccionService.getById(id);
		if(transaccion == null) {
			throw new ModelNotFoundException("Transaccion con id " + id + " no encontrado");
		}
		return new ResponseEntity<Transaccion>(transaccion, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva transaccion
	 * No lo guarda cuando encuentra otra transaccion con el mismo numero de referencia
	 * @param transaccionNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createContable')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Transaccion transaccionNew) throws Exception {
		Transaccion transaccion = transaccionService.create(transaccionNew);
		if(transaccion==null) {
			throw new Exception("No se ha podido crear la transaccion");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la transaccion buscandolo por su id
	 * @param transaccionUp
	 * @return Transaccion actualizada
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateContable')")
	@PutMapping
	public ResponseEntity<Transaccion> update(@Valid @RequestBody Transaccion transaccionUp) throws Exception {
		Transaccion transaccion = transaccionService.update(transaccionUp);
		return new ResponseEntity<Transaccion>(transaccion, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una transaccion de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteContable')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Transaccion transaccion = transaccionService.getById(id);
		if(transaccion == null) {
			throw new ModelNotFoundException("Transaccion con id " + id + " no encontrado");
		}
		transaccionService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
