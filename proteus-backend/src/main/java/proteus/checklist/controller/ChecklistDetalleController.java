package proteus.checklist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proteus.checklist.model.ChecklistDetalle;
import proteus.checklist.service.IChecklistDetalleService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/checklist-detalles")
public class ChecklistDetalleController {

	@Autowired
	private IChecklistDetalleService checklistDetalleService;
	
	/**
	 * Creacion del detalle de un checklist dependiendo del tipo de servicio
	 * @param idChecklistTipo
	 * @return ChecklistDetalle lista
	 * @throws Exception
	 */
	@GetMapping("/checklist-tipo/{idChecklistTipo}")
	public ResponseEntity<List<ChecklistDetalle>> createByChecklistTipo(@PathVariable("idChecklistTipo") Integer idChecklistTipo) throws Exception {
		List<ChecklistDetalle> checklistDetalleList = checklistDetalleService.createByChecklistTipo(idChecklistTipo);
		if(checklistDetalleList.isEmpty()) {
			throw new ModelNotFoundException("Detalle de checklist no fue creado");
		}
		return new ResponseEntity<List<ChecklistDetalle>>(checklistDetalleList, HttpStatus.OK);
	}
	
}
