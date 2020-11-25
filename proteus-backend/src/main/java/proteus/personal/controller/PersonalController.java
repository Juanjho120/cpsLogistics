package proteus.personal.controller;

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
import proteus.personal.model.Personal;
import proteus.personal.service.IPersonalService;

@RestController
@RequestMapping("/personal")
public class PersonalController {

	@Autowired
	private IPersonalService personalService;
	
	/**
	 * Obtiene todo el personal de la base de datos
	 * @return Listado de personal
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Personal>> getAll() throws Exception {
		List<Personal> personalList = personalService.getAll();
		if(personalList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentra personal en la base de datos");
		}
		return new ResponseEntity<List<Personal>>(personalList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todo el personal por puesto de la base de datos
	 * @param idPersonalPuesto
	 * @return Listado de personal
	 * @throws Exception
	 */
	@GetMapping("/puesto/{idPersonalPuesto}")
	public ResponseEntity<List<Personal>> getByPersonalPuesto(@PathVariable("idPersonalPuesto") Integer idPersonalPuesto) throws Exception {
		List<Personal> personalList = personalService.getByPersonalPuesto(idPersonalPuesto);
		if(personalList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentra personal con el puesto "+idPersonalPuesto+" en la base de datos");
		}
		return new ResponseEntity<List<Personal>>(personalList, HttpStatus.OK);
	}
	
	/**
	 * Busca un personal por su id
	 * @param id
	 * @return Personal
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Personal> getById(@PathVariable("id") Integer id) throws Exception {
		Personal personal = personalService.getById(id);
		if(personal == null) {
			throw new ModelNotFoundException("Personal con id " + id + " no encontrado");
		}
		return new ResponseEntity<Personal>(personal, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo personal
	 * No lo guarda cuando encuentra otro puesto de personal con el mismo telefono
	 * @param personalNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Personal personalNew) throws Exception {
		Personal personal = personalService.create(personalNew);
		if(personal==null) {
			throw new Exception("No se ha podido crear el personal");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del personal buscandolo por su id
	 * @param personalUp
	 * @return Personal actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Personal> update(@Valid @RequestBody Personal personalUp) throws Exception {
		Personal personal = personalService.update(personalUp);
		return new ResponseEntity<Personal>(personal, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un personal de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Personal personal = personalService.getById(id);
		if(personal == null) {
			throw new ModelNotFoundException("Personal con id " + id + " no encontrado");
		}
		personalService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
