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

import proteus.checklist.model.ChecklistEvaluacion;
import proteus.checklist.service.IChecklistEvaluacionService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/checklist-evaluaciones")
public class ChecklistEvaluacionController {

	@Autowired
	private IChecklistEvaluacionService checklistEvaluacionService;
	
	/**
	 * Obtiene todas las evaluaciones de checklist en la base de datos
	 * @return Listado de evaluaciones de checklist
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<ChecklistEvaluacion>> getAll() throws Exception {
		List<ChecklistEvaluacion> checklistEvaluacionList = checklistEvaluacionService.getAll();
		if(checklistEvaluacionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran evaluaciones de checklist en la base de datos");
		}
		return new ResponseEntity<List<ChecklistEvaluacion>>(checklistEvaluacionList, HttpStatus.OK);
	}
	
	/**
	 * Busca una evaluacion de checklist por su id
	 * @param id
	 * @return Evaluacion de checklist
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ChecklistEvaluacion> getById(@PathVariable("id") Integer id) throws Exception {
		ChecklistEvaluacion checklistEvaluacion = checklistEvaluacionService.getById(id);
		if(checklistEvaluacion == null) {
			throw new ModelNotFoundException("Evaluacion de checklist con id " + id + " no encontrado");
		}
		return new ResponseEntity<ChecklistEvaluacion>(checklistEvaluacion, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva evaluacion de checklist
	 * No lo guarda cuando encuentra otra evaluacion con el mismo nombre
	 * @param checklistEvaluacionNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ChecklistEvaluacion checklistEvaluacionNew) throws Exception {
		ChecklistEvaluacion checklistEvaluacion = checklistEvaluacionService.create(checklistEvaluacionNew);
		if(checklistEvaluacion==null) {
			throw new Exception("No se ha podido crear la evaluacion de checklist");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la evaluacion del checklist buscandolo por su id
	 * @param checklistEvaluacionUp
	 * @return Evaluacion del checklist actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<ChecklistEvaluacion> update(@Valid @RequestBody ChecklistEvaluacion checklistEvaluacionUp) throws Exception {
		ChecklistEvaluacion checklistEvaluacion = checklistEvaluacionService.update(checklistEvaluacionUp);
		return new ResponseEntity<ChecklistEvaluacion>(checklistEvaluacion, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una evaluacion de checklist de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		ChecklistEvaluacion checklistEvaluacion = checklistEvaluacionService.getById(id);
		if(checklistEvaluacion == null) {
			throw new ModelNotFoundException("Evaluacion de checklist con id " + id + " no encontrado");
		}
		checklistEvaluacionService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
