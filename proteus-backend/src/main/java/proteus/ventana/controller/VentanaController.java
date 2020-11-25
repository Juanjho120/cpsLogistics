package proteus.ventana.controller;

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

import proteus.exception.ModelNotFoundException;
import proteus.ventana.model.Ventana;
import proteus.ventana.service.IVentanaService;

@RestController
@RequestMapping("/ventanas")
public class VentanaController {

	@Autowired
	private IVentanaService ventanaService;
	
	/**
	 * Obtiene todas las ventanas de la base de datos
	 * @return Listado de ventanas
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Ventana>> getAll() throws Exception {
		List<Ventana> ventanaList = ventanaService.getAll();
		if(ventanaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran ventanas en la base de datos");
		}
		return new ResponseEntity<List<Ventana>>(ventanaList, HttpStatus.OK);
	}
	
	/**
	 * Busca una ventana por su id
	 * @param id
	 * @return Ventana
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Ventana> getById(@PathVariable("id") Integer id) throws Exception {
		Ventana ventana = ventanaService.getById(id);
		if(ventana == null) {
			throw new ModelNotFoundException("Ventana con id " + id + " no encontrado");
		}
		return new ResponseEntity<Ventana>(ventana, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva ventana
	 * @param ventanaNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Ventana ventanaNew) throws Exception {
		Ventana ventana = ventanaService.create(ventanaNew);
		if(ventana==null) {
			throw new Exception("No se ha podido crear la ventana");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la ventana buscandola por su id
	 * @param ventanaUp
	 * @return Ventana actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Ventana> update(@Valid @RequestBody Ventana ventanaUp) throws Exception {
		Ventana ventana = ventanaService.update(ventanaUp);
		return new ResponseEntity<Ventana>(ventana, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una ventana de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Ventana ventana = ventanaService.getById(id);
		if(ventana == null) {
			throw new ModelNotFoundException("Ventana con id " + id + " no encontrado");
		}
		ventanaService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
