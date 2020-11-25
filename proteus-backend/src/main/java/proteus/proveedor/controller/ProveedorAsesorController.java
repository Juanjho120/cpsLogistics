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
import proteus.proveedor.model.ProveedorAsesor;
import proteus.proveedor.service.IProveedorAsesorService;

@RestController
@RequestMapping("/proveedor-asesores")
public class ProveedorAsesorController {

	@Autowired
	private IProveedorAsesorService proveedorAsesorService;
	
	/**
	 * Obtiene todos los asesores de proveedores en la base de datos
	 * @return Listado de asesores de proveedores
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<ProveedorAsesor>> getAll() throws Exception {
		List<ProveedorAsesor> proveedorAsesorList = proveedorAsesorService.getAll();
		if(proveedorAsesorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran asesores de proveedores en la base de datos");
		}
		return new ResponseEntity<List<ProveedorAsesor>>(proveedorAsesorList, HttpStatus.OK);
	}
	
	/**
	 * Busca un asesor de proveedor por su id
	 * @param id
	 * @return Asesor de proveedor
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ProveedorAsesor> getById(@PathVariable("id") Integer id) throws Exception {
		ProveedorAsesor proveedorAsesor = proveedorAsesorService.getById(id);
		if(proveedorAsesor == null) {
			throw new ModelNotFoundException("Asesor de proveedor con id " + id + " no encontrado");
		}
		return new ResponseEntity<ProveedorAsesor>(proveedorAsesor, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los asesores por proveedor
	 * @param idProveedor
	 * @return Listado de asesores
	 * @throws Exception
	 */
	@GetMapping("/proveedor/{idProveedor}")
	public ResponseEntity<List<ProveedorAsesor>> getByProveedor(@PathVariable("idProveedor") Integer idProveedor) throws Exception {
		List<ProveedorAsesor> proveedorAsesorList = proveedorAsesorService.getByProveedor(idProveedor);
		if(proveedorAsesorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran asesores para el proveedor solicitado");
		}
		return new ResponseEntity<List<ProveedorAsesor>>(proveedorAsesorList, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo asesor de proveedor
	 * No lo guarda cuando encuentra otro asesor proveedor con el mismo telefono o 
	 * los mismos valores de nombre, proveedor y telefono
	 * @param proveedorAsesorNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ProveedorAsesor proveedorAsesorNew) throws Exception {
		ProveedorAsesor proveedorAsesor = proveedorAsesorService.create(proveedorAsesorNew);
		if(proveedorAsesor==null) {
			throw new Exception("No se ha podido crear el asesor del proveedor");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del asesor del proveedor buscandolo por su id
	 * @param proveedorAsesorUp
	 * @return Asesor de proveedor actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<ProveedorAsesor> update(@Valid @RequestBody ProveedorAsesor proveedorAsesorUp) throws Exception {
		ProveedorAsesor proveedorAsesor = proveedorAsesorService.update(proveedorAsesorUp);
		return new ResponseEntity<ProveedorAsesor>(proveedorAsesor, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un asesor de proveedor de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		ProveedorAsesor proveedorAsesor = proveedorAsesorService.getById(id);
		if(proveedorAsesor == null) {
			throw new ModelNotFoundException("Asesor de proveedor con id " + id + " no encontrado");
		}
		proveedorAsesorService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Elimina los asesores pertenecientes a un proveedor
	 * @param idProveedor
	 * @throws Exception
	 */
	@DeleteMapping("/proveedor/{idProveedor}")
	public ResponseEntity<Void> deleteByProveedor(@PathVariable("idProveedor") Integer idProveedor) throws Exception {
		List<ProveedorAsesor> proveedorAsesorList = proveedorAsesorService.getByProveedor(idProveedor);
		if(proveedorAsesorList.isEmpty()) {
			throw new ModelNotFoundException("Asesores del proveedor con id " + idProveedor + " no se han encontrado");
		}
		proveedorAsesorService.deleteByProveedor(idProveedor);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
