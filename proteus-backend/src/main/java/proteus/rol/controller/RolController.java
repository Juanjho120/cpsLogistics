package proteus.rol.controller;

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
import proteus.rol.model.Rol;
import proteus.rol.service.IRolService;

@RestController
@RequestMapping("/roles")
public class RolController {

	@Autowired
	private IRolService rolService;
	
	/**
	 * Obtiene todos los roles de la base de datos
	 * @return Listado de roles
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Rol>> getAll() throws Exception {
		List<Rol> rolList = rolService.getAll();
		if(rolList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran roles en la base de datos");
		}
		return new ResponseEntity<List<Rol>>(rolList, HttpStatus.OK);
	}
	
	/**
	 * Busca un rol por su id
	 * @param id
	 * @return Rol
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Rol> getById(@PathVariable("id") Integer id) throws Exception {
		Rol rol = rolService.getById(id);
		if(rol == null) {
			throw new ModelNotFoundException("Rol con id " + id + " no encontrado");
		}
		return new ResponseEntity<Rol>(rol, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo rol
	 * No lo guarda cuando encuentra otro rol con el mismo nombre
	 * @param rolNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Rol rolNew) throws Exception {
		Rol rol = rolService.create(rolNew);
		if(rol==null) {
			throw new Exception("No se ha podido crear el rol");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del rol buscandolo por su id
	 * @param rolUp
	 * @return Rol actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Rol> update(@Valid @RequestBody Rol rolUp) throws Exception {
		Rol rol = rolService.update(rolUp);
		return new ResponseEntity<Rol>(rol, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un rol de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Rol rol = rolService.getById(id);
		if(rol == null) {
			throw new ModelNotFoundException("Rol con id " + id + " no encontrado");
		}
		rolService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
