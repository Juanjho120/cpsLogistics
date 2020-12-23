package proteus.modificacion.controller;

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
import proteus.modificacion.model.Modificacion;
import proteus.modificacion.service.IModificacionService;

@RestController
@RequestMapping("/modificaciones")
public class ModificacionController {

	@Autowired
	private IModificacionService modificacionService;
	
	/**
	 * Obtiene todas las modificaciones de la base de datos
	 * @return Listado de modificaciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllAdmin')")
	@GetMapping
	public ResponseEntity<List<Modificacion>> getAll() throws Exception {
		List<Modificacion> modificacionList = modificacionService.getAll();
		if(modificacionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran modificaciones en la base de datos");
		}
		return new ResponseEntity<List<Modificacion>>(modificacionList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las modificaciones de un usuario de la base de datos
	 * @param idUsuario
	 * @return Listado de modificaciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamAdmin')")
	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<List<Modificacion>> getByUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
		List<Modificacion> modificacionList = modificacionService.getByUsuario(idUsuario);
		if(modificacionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran modificaciones del usuario "+idUsuario+" en la base de datos");
		}
		return new ResponseEntity<List<Modificacion>>(modificacionList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las modificaciones por concepto de la base de datos
	 * @param idConcepto
	 * @return Listado de modificaciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamAdmin')")
	@GetMapping("/concepto/{idConcepto}")
	public ResponseEntity<List<Modificacion>> getByConcepto(@PathVariable("idConcepto") Integer idConcepto) throws Exception {
		List<Modificacion> modificacionList = modificacionService.getByConcepto(idConcepto);
		if(modificacionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran modificaciones del concepto "+idConcepto+" en la base de datos");
		}
		return new ResponseEntity<List<Modificacion>>(modificacionList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las modificaciones por tabla de la base de datos
	 * @param tabla
	 * @return Listado de modificaciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamAdmin')")
	@GetMapping("/tabla/{tabla}")
	public ResponseEntity<List<Modificacion>> getByTabla(@PathVariable("tabla") String tabla) throws Exception {
		List<Modificacion> modificacionList = modificacionService.getByTabla(tabla);
		if(modificacionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran modificaciones de la tabla "+tabla+" en la base de datos");
		}
		return new ResponseEntity<List<Modificacion>>(modificacionList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las modificaciones por concepto y tabla de la base de datos
	 * @param idConcepto
	 * @param tabla
	 * @return Listado de modificaciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamAdmin')")
	@GetMapping("/concepto/{idConcepto}/tabla/{tabla}")
	public ResponseEntity<List<Modificacion>> getByConceptoAndTabla(@PathVariable("idConcepto") Integer idConcepto, 
			@PathVariable("tabla") String tabla) throws Exception {
		List<Modificacion> modificacionList = modificacionService.getByConceptoAndTabla(idConcepto, tabla);
		if(modificacionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran modificaciones del concepto "+idConcepto+" y tabla "+tabla+" en la base de datos");
		}
		return new ResponseEntity<List<Modificacion>>(modificacionList, HttpStatus.OK);
	}
	
	/**
	 * Busca una modificacion por su id
	 * @param id
	 * @return Modificacion
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdAdmin')")
	@GetMapping("/{id}")
	public ResponseEntity<Modificacion> getById(@PathVariable("id") Integer id) throws Exception {
		Modificacion modificacion = modificacionService.getById(id);
		if(modificacion == null) {
			throw new ModelNotFoundException("Modificacion con id " + id + " no encontrado");
		}
		return new ResponseEntity<Modificacion>(modificacion, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva modificacion
	 * @param modificacionNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Modificacion modificacionNew) throws Exception {
		Modificacion modificacion = modificacionService.create(modificacionNew);
		if(modificacion==null) {
			throw new Exception("No se ha podido crear la modificacion");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la modificacion buscandolo por su id
	 * @param modificacionUp
	 * @return Modificacion actualizada
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateProgramador')")
	@PutMapping
	public ResponseEntity<Modificacion> update(@Valid @RequestBody Modificacion modificacionUp) throws Exception {
		Modificacion modificacion = modificacionService.update(modificacionUp);
		return new ResponseEntity<Modificacion>(modificacion, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una modificacion de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteProgramador')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Modificacion modificacion = modificacionService.getById(id);
		if(modificacion == null) {
			throw new ModelNotFoundException("Modificacion con id " + id + " no encontrado");
		}
		modificacionService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
