package proteus.banco.controller;

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

import proteus.banco.model.Banco;
import proteus.banco.service.IBancoService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/bancos")
public class BancoController {

	@Autowired
	private IBancoService bancoService;
	
	/**
	 * Obtiene todos los bancos en la base de datos
	 * @return Listado de bancos
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAll')")
	@GetMapping
	public ResponseEntity<List<Banco>> getAll() throws Exception {
		List<Banco> bancoList = bancoService.getAll();
		if(bancoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran bancos en la base de datos");
		}
		return new ResponseEntity<List<Banco>>(bancoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un banco por su id
	 * @param id
	 * @return Banco
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getById')")
	@GetMapping("/{id}")
	public ResponseEntity<Banco> getById(@PathVariable("id") Integer id) throws Exception {
		Banco banco = bancoService.getById(id);
		if(banco == null) {
			throw new ModelNotFoundException("Banco con id " + id + " no encontrado");
		}
		return new ResponseEntity<Banco>(banco, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo banco
	 * No lo guarda cuando encuentra otro banco con el mismo nombre
	 * @param bancoNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createContable')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Banco bancoNew) throws Exception {
		Banco banco = bancoService.create(bancoNew);
		if(banco==null) {
			throw new Exception("No se ha podido crear el banco");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del banco buscandolo por su id
	 * @param bancoUp
	 * @return Banco actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateContable')")
	@PutMapping
	public ResponseEntity<Banco> update(@Valid @RequestBody Banco bancoUp) throws Exception {
		Banco banco = bancoService.update(bancoUp);
		return new ResponseEntity<Banco>(banco, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un banco de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteContable')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Banco banco = bancoService.getById(id);
		if(banco == null) {
			throw new ModelNotFoundException("Banco con id " + id + " no encontrado");
		}
		bancoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
