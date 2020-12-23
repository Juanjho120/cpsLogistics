package proteus.repuesto.controller;

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
import proteus.repuesto.model.Repuesto;
import proteus.repuesto.service.IRepuestoService;

@RestController
@RequestMapping("/repuestos")
public class RepuestoController {

	@Autowired
	private IRepuestoService repuestoService;
	
	/**
	 * Obtiene todos los repuestos de la base de datos
	 * @return Listado de repuestos
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAll')")
	@GetMapping
	public ResponseEntity<List<Repuesto>> getAll() throws Exception {
		List<Repuesto> repuestoList = repuestoService.getAll();
		if(repuestoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran repuestos en la base de datos");
		}
		return new ResponseEntity<List<Repuesto>>(repuestoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un repuesto por su id
	 * @param id
	 * @return Repuesto
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getById')")
	@GetMapping("/{id}")
	public ResponseEntity<Repuesto> getById(@PathVariable("id") Integer id) throws Exception {
		Repuesto repuesto = repuestoService.getById(id);
		if(repuesto == null) {
			throw new ModelNotFoundException("Repuesto con id " + id + " no encontrado");
		}
		return new ResponseEntity<Repuesto>(repuesto, HttpStatus.OK);
	}
	
	/**
	 * Busca un repuesto por su codigo
	 * @param codigo
	 * @return Repuesto
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<List<Repuesto>> getByCodigo(@PathVariable("codigo") String codigo) throws Exception {
		List<Repuesto> repuestoList = repuestoService.getByCodigo(codigo);
		if(repuestoList.isEmpty()) {
			throw new ModelNotFoundException("Repuesto con codigo " + codigo + " no encontrado");
		}
		return new ResponseEntity<List<Repuesto>>(repuestoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un repuesto por su codigo
	 * @param codigoBarra
	 * @return Repuesto
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/codigo-barra/{codigoBarra}")
	public ResponseEntity<List<Repuesto>> getByCodigoBarra(@PathVariable("codigoBarra") String codigoBarra) throws Exception {
		List<Repuesto> repuestoList = repuestoService.getByCodigoBarra(codigoBarra);
		if(repuestoList.isEmpty()) {
			throw new ModelNotFoundException("Repuesto con codigo de barra " + codigoBarra + " no encontrado");
		}
		return new ResponseEntity<List<Repuesto>>(repuestoList, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo repuesto
	 * No lo guarda cuando encuentra otro repuesto con el mismo codigo y codigo de barra
	 * @param repuestoNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createBodega')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Repuesto repuestoNew) throws Exception {
		Repuesto repuesto = repuestoService.create(repuestoNew);
		if(repuesto==null) {
			throw new Exception("No se ha podido crear el repuesto");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del repuesto buscandolo por su id
	 * No lo actualiza cuando encuentra otro repuesto con el mismo codigo y codigo de barra
	 * @param repuestoUp
	 * @return Repuesto actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateBodega')")
	@PutMapping
	public ResponseEntity<Repuesto> update(@Valid @RequestBody Repuesto repuestoUp) throws Exception {
		Repuesto repuesto = repuestoService.update(repuestoUp);
		if(repuesto==null) {
			throw new Exception("No se ha podido actualizar el repuesto, recuerde que no se permiten codigos repetidos");
		}
		return new ResponseEntity<Repuesto>(repuesto, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un repuesto de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteAdmin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Repuesto repuesto = repuestoService.getById(id);
		if(repuesto == null) {
			throw new ModelNotFoundException("Repuesto con id " + id + " no encontrado");
		}
		repuestoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Elimina un repuesto de la base de datos por su codigo
	 * @param codigo
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteAdmin')")
	@DeleteMapping("codigo/{codigo}")
	public ResponseEntity<Void> deleteByCodigo(@PathVariable("codigo") String codigo) throws Exception {
		List<Repuesto> repuestoList = repuestoService.getByCodigo(codigo);
		if(repuestoList.isEmpty()) {
			throw new ModelNotFoundException("Repuesto con codigo "+codigo+" no encontrado");
		}
		repuestoService.deleteByCodigo(codigo);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Elimina un repuesto de la base de datos por su codigo de barra
	 * @param codigoBarra
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteAdmin')")
	@DeleteMapping("codigo-barra/{codigoBarra}")
	public ResponseEntity<Void> deleteByCodigoBarra(@PathVariable("codigoBarra") String codigoBarra) throws Exception {
		List<Repuesto> repuestoList = repuestoService.getByCodigoBarra(codigoBarra);
		if(repuestoList.isEmpty()) {
			throw new ModelNotFoundException("Repuesto con codigo de barra "+codigoBarra+" no encontrado");
		}
		repuestoService.deleteByCodigoBarra(codigoBarra);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
