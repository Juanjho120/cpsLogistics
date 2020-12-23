package proteus.nota.controller;

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
import proteus.nota.model.NotaCredito;
import proteus.nota.service.INotaCreditoService;

@RestController
@RequestMapping("/notas-credito")
public class NotaCreditoController {

	@Autowired
	private INotaCreditoService notaCreditoService;
	
	/**
	 * Obtiene todas las notas de credito en la base de datos
	 * @return Listado de notas de credito
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllContable')")
	@GetMapping
	public ResponseEntity<List<NotaCredito>> getAll() throws Exception {
		List<NotaCredito> notaCreditoList = notaCreditoService.getAll();
		if(notaCreditoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran notas de credito en la base de datos");
		}
		return new ResponseEntity<List<NotaCredito>>(notaCreditoList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las notas de credito por factura
	 * @param idFacturaCompra
	 * @return Listado de notas de credito
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/factura-compra/{idFacturaCompra}")
	public ResponseEntity<List<NotaCredito>> getByFacturaCompra(@PathVariable("idFacturaCompra") Integer idFacturaCompra) throws Exception {
		List<NotaCredito> notaCreditoList = notaCreditoService.getByFacturaCompra(idFacturaCompra);
		if(notaCreditoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran notas de credito");
		}
		return new ResponseEntity<List<NotaCredito>>(notaCreditoList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las notas de credito por fecha de aprobacion
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de notas de credito
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/fecha-ingreso/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<NotaCredito>> getByFechaIngreso(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<NotaCredito> notaCreditoList = notaCreditoService.getByFechaIngreso(fechaDesde, fechaHasta);
		if(notaCreditoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran notas de credito");
		}
		return new ResponseEntity<List<NotaCredito>>(notaCreditoList, HttpStatus.OK);
	}
	
	/**
	 * Busca una nota de credito por su id
	 * @param id
	 * @return NotaCredito
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdContable')")
	@GetMapping("/{id}")
	public ResponseEntity<NotaCredito> getById(@PathVariable("id") Integer id) throws Exception {
		NotaCredito notaCredito = notaCreditoService.getById(id);
		if(notaCredito == null) {
			throw new ModelNotFoundException("Nota de credito con id " + id + " no encontrado");
		}
		return new ResponseEntity<NotaCredito>(notaCredito, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva nota de credito
	 * No se guarda cuando ya existe otra nota de credito con el mismo codigo
	 * @param notaCreditoNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createContable')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody NotaCredito notaCreditoNew) throws Exception {
		NotaCredito notaCredito = notaCreditoService.create(notaCreditoNew);
		if(notaCredito==null) {
			throw new Exception("No se ha podido crear la nota de credito");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la nota de credito buscandolo por su id
	 * @param notaCreditoUp
	 * @return Nota de credito actualizada
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateContable')")
	@PutMapping
	public ResponseEntity<NotaCredito> update(@Valid @RequestBody NotaCredito notaCreditoUp) throws Exception {
		NotaCredito notaCredito = notaCreditoService.update(notaCreditoUp);
		return new ResponseEntity<NotaCredito>(notaCredito, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una nota de credito de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteContable')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		NotaCredito notaCredito = notaCreditoService.getById(id);
		if(notaCredito == null) {
			throw new ModelNotFoundException("Nota de credito con id " + id + " no encontrado");
		}
		notaCreditoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
