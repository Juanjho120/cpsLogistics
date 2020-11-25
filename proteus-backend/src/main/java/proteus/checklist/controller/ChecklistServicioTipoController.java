package proteus.checklist.controller;

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

import proteus.checklist.model.ChecklistServicioTipo;
import proteus.checklist.service.IChecklistServicioTipoService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/checklist-servicio-tipos")
public class ChecklistServicioTipoController {

	@Autowired
	private IChecklistServicioTipoService checklistServicioTipoService;
	
	/**
	 * Obtiene todos los tipos de servicio de checklist en la base de datos
	 * @return Listado de tipos de servicio de checklist
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<ChecklistServicioTipo>> getAll() throws Exception {
		List<ChecklistServicioTipo> checklistServicioTipoList = checklistServicioTipoService.getAll();
		if(checklistServicioTipoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran tipos de servicio del checklist en la base de datos");
		}
		return new ResponseEntity<List<ChecklistServicioTipo>>(checklistServicioTipoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un tipo de servicio de checklist por su id
	 * @param id
	 * @return Tipo de servicio de checklist
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ChecklistServicioTipo> getById(@PathVariable("id") Integer id) throws Exception {
		ChecklistServicioTipo checklistServicioTipo = checklistServicioTipoService.getById(id);
		if(checklistServicioTipo == null) {
			throw new ModelNotFoundException("Tipo de servicio del checklist con id " + id + " no encontrado");
		}
		return new ResponseEntity<ChecklistServicioTipo>(checklistServicioTipo, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo tipo de servicio de checklist
	 * No lo guarda cuando encuentra otro tipo de servicio con el mismo nombre
	 * @param checklistServicioTipoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ChecklistServicioTipo checklistServicioTipoNew) throws Exception {
		ChecklistServicioTipo checklistServicioTipo = checklistServicioTipoService.create(checklistServicioTipoNew);
		if(checklistServicioTipo==null) {
			throw new Exception("No se ha podido crear el tipo de servicio del checklist");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del tipo de servicio del checklist buscandolo por su id
	 * @param checklistServicioTipoUp
	 * @return Tipo de servicio del checklist actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<ChecklistServicioTipo> update(@Valid @RequestBody ChecklistServicioTipo checklistServicioTipoUp) throws Exception {
		ChecklistServicioTipo checklistServicioTipo = checklistServicioTipoService.update(checklistServicioTipoUp);
		return new ResponseEntity<ChecklistServicioTipo>(checklistServicioTipo, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un tipo de servicio de checklist de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		ChecklistServicioTipo checklistServicioTipo = checklistServicioTipoService.getById(id);
		if(checklistServicioTipo == null) {
			throw new ModelNotFoundException("Tipo de servicio del checklist con id " + id + " no encontrado");
		}
		checklistServicioTipoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
