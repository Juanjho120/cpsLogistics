package proteus.concepto.controller;

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

import proteus.concepto.model.Concepto;
import proteus.concepto.service.IConceptoService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/conceptos")
public class ConceptoController {

	@Autowired
	private IConceptoService conceptoService;
	
	/**
	 * Obtiene todos los conceptos en la base de datos
	 * @return Listado de conceptos
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Concepto>> getAll() throws Exception {
		List<Concepto> conceptoList = conceptoService.getAll();
		if(conceptoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran conceptos en la base de datos");
		}
		return new ResponseEntity<List<Concepto>>(conceptoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un concepto por su id
	 * @param id
	 * @return Concepto
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Concepto> getById(@PathVariable("id") Integer id) throws Exception {
		Concepto concepto = conceptoService.getById(id);
		if(concepto == null) {
			throw new ModelNotFoundException("Concepto con id " + id + " no encontrado");
		}
		return new ResponseEntity<Concepto>(concepto, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo concepto
	 * No lo guarda cuando encuentra otro concepto con el mismo nombre
	 * @param conceptoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Concepto conceptoNew) throws Exception {
		Concepto concepto = conceptoService.create(conceptoNew);
		if(concepto==null) {
			throw new Exception("No se ha podido crear el concepto");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del concepto buscandolo por su id
	 * @param conceptoUp
	 * @return Concepto actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Concepto> update(@Valid @RequestBody Concepto conceptoUp) throws Exception {
		Concepto concepto = conceptoService.update(conceptoUp);
		return new ResponseEntity<Concepto>(concepto, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un concepto de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Concepto concepto = conceptoService.getById(id);
		if(concepto == null) {
			throw new ModelNotFoundException("Concepto con id " + id + " no encontrado");
		}
		conceptoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
