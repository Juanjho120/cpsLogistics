package proteus.servicio.controller;

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
import proteus.servicio.model.ServicioTipo;
import proteus.servicio.service.IServicioTipoService;

@RestController
@RequestMapping("/servicio-tipos")
public class ServicioTipoController {

	@Autowired
	private IServicioTipoService servicioTipoService;
	
	/**
	 * Obtiene todos los tipos de servicios de la base de datos
	 * @return Listado de tipos de servicios
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<ServicioTipo>> getAll() throws Exception {
		List<ServicioTipo> servicioTipoList = servicioTipoService.getAll();
		if(servicioTipoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran tipos de servicios en la base de datos");
		}
		return new ResponseEntity<List<ServicioTipo>>(servicioTipoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un tipo de servicio por su id
	 * @param id
	 * @return Tipo de servicio
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ServicioTipo> getById(@PathVariable("id") Integer id) throws Exception {
		ServicioTipo servicioTipo = servicioTipoService.getById(id);
		if(servicioTipo == null) {
			throw new ModelNotFoundException("Tipo de servicio con id " + id + " no encontrado");
		}
		return new ResponseEntity<ServicioTipo>(servicioTipo, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo tipo de servicio
	 * No lo guarda cuando encuentra otro tipo de servicio con el mismo nombre
	 * @param servicioTipoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ServicioTipo servicioTipoNew) throws Exception {
		ServicioTipo servicioTipo = servicioTipoService.create(servicioTipoNew);
		if(servicioTipo==null) {
			throw new Exception("No se ha podido crear el tipo de servicio");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del tipo de servicio buscandolo por su id
	 * @param servicioTipoUp
	 * @return Tipo de servicio actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<ServicioTipo> update(@Valid @RequestBody ServicioTipo servicioTipoUp) throws Exception {
		ServicioTipo servicioTipo = servicioTipoService.update(servicioTipoUp);
		return new ResponseEntity<ServicioTipo>(servicioTipo, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un tipo de servicio de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		ServicioTipo servicioTipo = servicioTipoService.getById(id);
		if(servicioTipo == null) {
			throw new ModelNotFoundException("Tipo de servicio con id " + id + " no encontrado");
		}
		servicioTipoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
