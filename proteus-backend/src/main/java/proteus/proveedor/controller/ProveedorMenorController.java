package proteus.proveedor.controller;

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
import proteus.proveedor.model.ProveedorMenor;
import proteus.proveedor.service.IProveedorMenorService;

@RestController
@RequestMapping("/proveedores-menores")
public class ProveedorMenorController {

	@Autowired
	private IProveedorMenorService proveedorMenorService;
	
	/**
	 * Obtiene todos los proveedores menores en la base de datos
	 * @return Listado de proveedores menores
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAll')")
	@GetMapping
	public ResponseEntity<List<ProveedorMenor>> getAll() throws Exception {
		List<ProveedorMenor> proveedorMenorList = proveedorMenorService.getAll();
		if(proveedorMenorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran proveedores menores en la base de datos");
		}
		return new ResponseEntity<List<ProveedorMenor>>(proveedorMenorList, HttpStatus.OK);
	}
	
	/**
	 * Busca un proveedor menor por su id
	 * @param id
	 * @return ProveedorMenor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getById')")
	@GetMapping("/{id}")
	public ResponseEntity<ProveedorMenor> getById(@PathVariable("id") Integer id) throws Exception {
		ProveedorMenor proveedorMenor = proveedorMenorService.getById(id);
		if(proveedorMenor == null) {
			throw new ModelNotFoundException("Proveedor menor con id " + id + " no encontrado");
		}
		return new ResponseEntity<ProveedorMenor>(proveedorMenor, HttpStatus.OK);
	}
	
	/**
	 * Busca un proveedor menor por nombre
	 * @param nombre
	 * @return ProveedorMenor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<ProveedorMenor> getById(@PathVariable("nombre") String nombre) throws Exception {
		ProveedorMenor proveedorMenor = proveedorMenorService.getByNombre(nombre);
		if(proveedorMenor == null) {
			throw new ModelNotFoundException("Proveedor menor no encontrado");
		}
		return new ResponseEntity<ProveedorMenor>(proveedorMenor, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo proveedor menor
	 * No lo guarda cuando encuentra otro proveedor con el mismo nombre
	 * @param proveedorMenorNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createCompra')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ProveedorMenor proveedoMenorNew) throws Exception {
		ProveedorMenor proveedoMenor = proveedorMenorService.create(proveedoMenorNew);
		if(proveedoMenor==null) {
			throw new Exception("No se ha podido crear el proveedor menor");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del proveedor menor buscandolo por su id
	 * @param proveedorMenorUp
	 * @return ProveedorMenor actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateCompra')")
	@PutMapping
	public ResponseEntity<ProveedorMenor> update(@Valid @RequestBody ProveedorMenor proveedorMenorUp) throws Exception {
		ProveedorMenor proveedorMenor = proveedorMenorService.update(proveedorMenorUp);
		return new ResponseEntity<ProveedorMenor>(proveedorMenor, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un proveedor menor de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteCompra')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		ProveedorMenor proveedorMenor = proveedorMenorService.getById(id);
		if(proveedorMenor == null) {
			throw new ModelNotFoundException("Proveedor menor con id " + id + " no encontrado");
		}
		proveedorMenorService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
