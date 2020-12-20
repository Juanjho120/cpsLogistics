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
import proteus.rol.model.RolPermisoVentana;
import proteus.rol.service.IRolPermisoVentanaService;

@RestController
@RequestMapping("/roles-permisos-ventanas")
public class RolPermisoVentanaController {

	@Autowired
	private IRolPermisoVentanaService rolPermisoVentanaService;
	
	/**
	 * Obtiene todos los roles con permisos de cada ventana de la base de datos
	 * @return Listado de roles
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<RolPermisoVentana>> getAll() throws Exception {
		List<RolPermisoVentana> rolPermisoVentanaList = rolPermisoVentanaService.getAll();
		if(rolPermisoVentanaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran roles con permisos de ventanas en la base de datos");
		}
		return new ResponseEntity<List<RolPermisoVentana>>(rolPermisoVentanaList, HttpStatus.OK);
	}
	
	/**
	 * Busca un rol con permiso de una ventana por su id
	 * @param id
	 * @return RolPermisoVentana
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<RolPermisoVentana> getById(@PathVariable("id") Integer id) throws Exception {
		RolPermisoVentana rolPermisoVentana = rolPermisoVentanaService.getById(id);
		if(rolPermisoVentana == null) {
			throw new ModelNotFoundException("Rol con permiso de ventana con id " + id + " no encontrado");
		}
		return new ResponseEntity<RolPermisoVentana>(rolPermisoVentana, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo rol con permiso de una ventana
	 * @param rolPermisoVentanaNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody RolPermisoVentana rolPermisoVentanaNew) throws Exception {
		RolPermisoVentana rolPermisoVentana = rolPermisoVentanaService.create(rolPermisoVentanaNew);
		if(rolPermisoVentana==null) {
			throw new Exception("No se ha podido crear el rol con permiso de ventana");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del rol buscandolo por su id
	 * @param rolPermisoVentanaUp
	 * @return RolPermisoVentana actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<RolPermisoVentana> update(@Valid @RequestBody RolPermisoVentana rolPermisoVentanaUp) throws Exception {
		RolPermisoVentana rolPermisoVentana = rolPermisoVentanaService.update(rolPermisoVentanaUp);
		return new ResponseEntity<RolPermisoVentana>(rolPermisoVentana, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un rol con permiso de ventana de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		RolPermisoVentana rolPermisoVentana = rolPermisoVentanaService.getById(id);
		if(rolPermisoVentana == null) {
			throw new ModelNotFoundException("Rol con permiso de ventana con id " + id + " no encontrado");
		}
		rolPermisoVentanaService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
