package proteus.permiso.controller;

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
import proteus.permiso.model.Permiso;
import proteus.permiso.service.IPermisoService;

@RestController
@RequestMapping("/permisos")
public class PermisoController {

	@Autowired
	private IPermisoService permisoService;
	
	/**
	 * Obtiene todas los permisos de la base de datos
	 * @return Listado de permisos
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Permiso>> getAll() throws Exception {
		List<Permiso> permisoList = permisoService.getAll();
		if(permisoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran permisos en la base de datos");
		}
		return new ResponseEntity<List<Permiso>>(permisoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un permiso por su id
	 * @param id
	 * @return Permiso
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Permiso> getById(@PathVariable("id") Integer id) throws Exception {
		Permiso permisos = permisoService.getById(id);
		if(permisos == null) {
			throw new ModelNotFoundException("Permiso con id " + id + " no encontrado");
		}
		return new ResponseEntity<Permiso>(permisos, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo permiso
	 * @param permisoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Permiso permisoNew) throws Exception {
		Permiso permiso = permisoService.create(permisoNew);
		if(permiso==null) {
			throw new Exception("No se ha podido crear el permiso");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del permiso buscandolo por su id
	 * @param permisoUp
	 * @return Permiso actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Permiso> update(@Valid @RequestBody Permiso permisoUp) throws Exception {
		Permiso permiso = permisoService.update(permisoUp);
		return new ResponseEntity<Permiso>(permiso, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un permiso de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Permiso permiso = permisoService.getById(id);
		if(permiso == null) {
			throw new ModelNotFoundException("Permiso con id " + id + " no encontrado");
		}
		permisoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
