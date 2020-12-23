package proteus.personal.controller;

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
import proteus.personal.model.PersonalPuesto;
import proteus.personal.service.IPersonalPuestoService;

@RestController
@RequestMapping("/personal-puestos")
public class PersonalPuestoController {

	@Autowired
	private IPersonalPuestoService personalPuestoService;
	
	/**
	 * Obtiene todos los puestos de personal en la base de datos
	 * @return Listado de puestos de personal
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllAdmin')")
	@GetMapping
	public ResponseEntity<List<PersonalPuesto>> getAll() throws Exception {
		List<PersonalPuesto> personalPuestoList = personalPuestoService.getAll();
		if(personalPuestoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran puestos de personal en la base de datos");
		}
		return new ResponseEntity<List<PersonalPuesto>>(personalPuestoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un puesto de personal por su id
	 * @param id
	 * @return Puesto de personal
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdAdmin')")
	@GetMapping("/{id}")
	public ResponseEntity<PersonalPuesto> getById(@PathVariable("id") Integer id) throws Exception {
		PersonalPuesto personalPuesto = personalPuestoService.getById(id);
		if(personalPuesto == null) {
			throw new ModelNotFoundException("Puesto de personal con id " + id + " no encontrado");
		}
		return new ResponseEntity<PersonalPuesto>(personalPuesto, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo puesto de personal
	 * No lo guarda cuando encuentra otro puesto de personal con el mismo nombre
	 * @param personalPuestoNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createAdmin')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody PersonalPuesto personalPuestoNew) throws Exception {
		PersonalPuesto personalPuesto = personalPuestoService.create(personalPuestoNew);
		if(personalPuesto==null) {
			throw new Exception("No se ha podido crear el puesto de personal");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del puesto de personal buscandolo por su id
	 * @param personalPuestoUp
	 * @return Puesto de personal actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateAdmin')")
	@PutMapping
	public ResponseEntity<PersonalPuesto> update(@Valid @RequestBody PersonalPuesto personalPuestoUp) throws Exception {
		PersonalPuesto personalPuesto = personalPuestoService.update(personalPuestoUp);
		return new ResponseEntity<PersonalPuesto>(personalPuesto, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un puesto de personal de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteAdmin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		PersonalPuesto personalPuesto = personalPuestoService.getById(id);
		if(personalPuesto == null) {
			throw new ModelNotFoundException("Puesto de personal con id " + id + " no encontrado");
		}
		personalPuestoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
