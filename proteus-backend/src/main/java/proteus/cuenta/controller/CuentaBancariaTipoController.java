package proteus.cuenta.controller;

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

import proteus.cuenta.model.CuentaBancariaTipo;
import proteus.cuenta.service.ICuentaBancariaTipoService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/cuenta-bancaria-tipos")
public class CuentaBancariaTipoController {

	@Autowired
	private ICuentaBancariaTipoService cuentaBancariaTipoService;
	
	/**
	 * Obtiene todas los tipos de cuentas bancarias en la base de datos
	 * @return Listado de tipos de cuentas bancarias
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllContable')")
	@GetMapping
	public ResponseEntity<List<CuentaBancariaTipo>> getAll() throws Exception {
		List<CuentaBancariaTipo> cuentaBancariaTipoList = cuentaBancariaTipoService.getAll();
		if(cuentaBancariaTipoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran tipos de cuenta bancaria en la base de datos");
		}
		return new ResponseEntity<List<CuentaBancariaTipo>>(cuentaBancariaTipoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un tipo de cuenta bancaria por su id
	 * @param id
	 * @return Tipo de cuenta bancaria
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdContable')")
	@GetMapping("/{id}")
	public ResponseEntity<CuentaBancariaTipo> getById(@PathVariable("id") Integer id) throws Exception {
		CuentaBancariaTipo cuentaBancariaTipo = cuentaBancariaTipoService.getById(id);
		if(cuentaBancariaTipo == null) {
			throw new ModelNotFoundException("Tipo de cuenta bancaria con id " + id + " no encontrado");
		}
		return new ResponseEntity<CuentaBancariaTipo>(cuentaBancariaTipo, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo tipo de cuenta bancaria
	 * No lo guarda cuando encuentra otro tipo de cuenta bancaria con el mismo nombre
	 * @param cuentaBancariaTipoNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createContable')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody CuentaBancariaTipo cuentaBancariaTipoNew) throws Exception {
		CuentaBancariaTipo cuentaBancariaTipo = cuentaBancariaTipoService.create(cuentaBancariaTipoNew);
		if(cuentaBancariaTipo==null) {
			throw new Exception("No se ha podido crear el tipo de cuenta bancaria");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del tipo de cuenta bancaria buscandolo por su id
	 * @param cuentaBancariaTipoUp
	 * @return Tipo de cuenta bancaria actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateContable')")
	@PutMapping
	public ResponseEntity<CuentaBancariaTipo> update(@Valid @RequestBody CuentaBancariaTipo cuentaBancariaTipoUp) throws Exception {
		CuentaBancariaTipo cuentaBancariaTipo = cuentaBancariaTipoService.update(cuentaBancariaTipoUp);
		return new ResponseEntity<CuentaBancariaTipo>(cuentaBancariaTipo, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un tipo de cuenta bancaria de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteContable')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		CuentaBancariaTipo cuentaBancariaTipo = cuentaBancariaTipoService.getById(id);
		if(cuentaBancariaTipo == null) {
			throw new ModelNotFoundException("Tipo de cuenta bancaria con id " + id + " no encontrado");
		}
		cuentaBancariaTipoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
