package proteus.marca.controller;

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
import proteus.marca.model.MarcaRepuesto;
import proteus.marca.service.IMarcaRepuestoService;

@RestController
@RequestMapping("/marcas-repuestos")
public class MarcaRepuestoController {

	@Autowired
	private IMarcaRepuestoService marcaRepuestoService;
	
	/**
	 * Obtiene todas las marcas de repuestos en la base de datos
	 * @return Listado de marcas de repuestos
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAll')")
	@GetMapping
	public ResponseEntity<List<MarcaRepuesto>> getAll() throws Exception {
		List<MarcaRepuesto> marcaRepuestoList = marcaRepuestoService.getAll();
		if(marcaRepuestoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran marcas de repuestos en la base de datos");
		}
		return new ResponseEntity<List<MarcaRepuesto>>(marcaRepuestoList, HttpStatus.OK);
	}
	
	/**
	 * Busca una marca de repuesto por su id
	 * @param id
	 * @return Marca de repuesto
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getById')")
	@GetMapping("/{id}")
	public ResponseEntity<MarcaRepuesto> getById(@PathVariable("id") Integer id) throws Exception {
		MarcaRepuesto marcaRepuesto = marcaRepuestoService.getById(id);
		if(marcaRepuesto == null) {
			throw new ModelNotFoundException("Marca de repuesto con id " + id + " no encontrado");
		}
		return new ResponseEntity<MarcaRepuesto>(marcaRepuesto, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva marca de repuesto
	 * No lo guarda cuando encuentra otra marca de repuesto con el mismo nombre
	 * @param marcaRepuestoNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createAdmin')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody MarcaRepuesto marcaRepuestoNew) throws Exception {
		MarcaRepuesto marcaRepuesto = marcaRepuestoService.create(marcaRepuestoNew);
		if(marcaRepuesto==null) {
			throw new Exception("No se ha podido crear la marca de repuesto");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la marca de repuesto buscandolo por su id
	 * @param marcaRepuestoUp
	 * @return Marca de repuesto actualizada
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateAdmin')")
	@PutMapping
	public ResponseEntity<MarcaRepuesto> update(@Valid @RequestBody MarcaRepuesto marcaRepuestoUp) throws Exception {
		MarcaRepuesto marcaRepuesto = marcaRepuestoService.update(marcaRepuestoUp);
		return new ResponseEntity<MarcaRepuesto>(marcaRepuesto, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una marca de repuesto de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteAdmin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		MarcaRepuesto marcaRepuesto = marcaRepuestoService.getById(id);
		if(marcaRepuesto == null) {
			throw new ModelNotFoundException("Marca de repuesto con id " + id + " no encontrado");
		}
		marcaRepuestoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
