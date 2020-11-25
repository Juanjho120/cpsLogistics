package proteus.proveedor.controller;

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
import proteus.proveedor.dto.ProveedorDTO;
import proteus.proveedor.model.Proveedor;
import proteus.proveedor.service.IProveedorService;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

	@Autowired
	private IProveedorService proveedorService;
	
	/**
	 * Obtiene todos los proveedores en la base de datos
	 * @return Listado de proveedores
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Proveedor>> getAll() throws Exception {
		List<Proveedor> proveedorList = proveedorService.getAll();
		if(proveedorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran proveedores en la base de datos");
		}
		return new ResponseEntity<List<Proveedor>>(proveedorList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los proveedores con sus asesores en la base de datos
	 * @return Listado de proveedores
	 * @throws Exception
	 */
	@GetMapping("/dto")
	public ResponseEntity<List<ProveedorDTO>> getAllDTO() throws Exception {
		List<ProveedorDTO> proveedorList = proveedorService.getAllDTO();
		if(proveedorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran proveedores en la base de datos");
		}
		return new ResponseEntity<List<ProveedorDTO>>(proveedorList, HttpStatus.OK);
	}
	
	/**
	 * Busca un proveedor por su id
	 * @param id
	 * @return Proveedor
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Proveedor> getById(@PathVariable("id") Integer id) throws Exception {
		Proveedor proveedor = proveedorService.getById(id);
		if(proveedor == null) {
			throw new ModelNotFoundException("Proveedor con id " + id + " no encontrado");
		}
		return new ResponseEntity<Proveedor>(proveedor, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo proveedor
	 * No lo guarda cuando encuentra otro proveedor con el mismo nombre y nit
	 * @param proveedorNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Proveedor proveedorNew) throws Exception {
		Proveedor proveedor = proveedorService.create(proveedorNew);
		if(proveedor==null) {
			throw new Exception("No se ha podido crear el proveedor");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del proveedor buscandolo por su id
	 * @param proveedorUp
	 * @return Proveedor actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Proveedor> update(@Valid @RequestBody Proveedor proveedorUp) throws Exception {
		Proveedor proveedor = proveedorService.update(proveedorUp);
		return new ResponseEntity<Proveedor>(proveedor, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un proveedor de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Proveedor proveedor = proveedorService.getById(id);
		if(proveedor == null) {
			throw new ModelNotFoundException("Proveedor con id " + id + " no encontrado");
		}
		proveedorService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
