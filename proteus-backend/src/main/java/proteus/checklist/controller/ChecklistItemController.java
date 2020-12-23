package proteus.checklist.controller;

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

import proteus.checklist.model.ChecklistItem;
import proteus.checklist.service.IChecklistItemService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/checklist-items")
public class ChecklistItemController {

	@Autowired
	private IChecklistItemService checklistItemService;
	
	/**
	 * Obtiene todos los items de checklist en la base de datos
	 * @return Listado de items de checklist
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAll')")
	@GetMapping
	public ResponseEntity<List<ChecklistItem>> getAll() throws Exception {
		List<ChecklistItem> checklistList = checklistItemService.getAll();
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran los item del checklist en la base de datos");
		}
		return new ResponseEntity<List<ChecklistItem>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Busca un item de checklist por su id
	 * @param id
	 * @return Item de checklist
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getById')")
	@GetMapping("/{id}")
	public ResponseEntity<ChecklistItem> getById(@PathVariable("id") Integer id) throws Exception {
		ChecklistItem checklistItem = checklistItemService.getById(id);
		if(checklistItem == null) {
			throw new ModelNotFoundException("Item del checklist con id " + id + " no encontrado");
		}
		return new ResponseEntity<ChecklistItem>(checklistItem, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo item de checklist
	 * No lo guarda cuando encuentra otro item con el mismo nombre
	 * @param checklistItemNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createServicio')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ChecklistItem checklistItemNew) throws Exception {
		ChecklistItem checklistItem = checklistItemService.create(checklistItemNew);
		if(checklistItem==null) {
			throw new Exception("No se ha podido crear el item del checklist");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del item del checklist buscandolo por su id
	 * @param checklistItemUp
	 * @return Item del checklist actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateServicio')")
	@PutMapping
	public ResponseEntity<ChecklistItem> update(@Valid @RequestBody ChecklistItem checklistItemUp) throws Exception {
		ChecklistItem checklistItem = checklistItemService.update(checklistItemUp);
		return new ResponseEntity<ChecklistItem>(checklistItem, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un item de checklist de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteServicio')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		ChecklistItem checklistItem = checklistItemService.getById(id);
		if(checklistItem == null) {
			throw new ModelNotFoundException("Item del checklist con id " + id + " no encontrado");
		}
		checklistItemService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
