package proteus.transaccion.controller;

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
import proteus.transaccion.model.TransaccionEstado;
import proteus.transaccion.service.ITransaccionEstadoService;

@RestController
@RequestMapping("/transaccion-estados")
public class TransaccionEstadoController {

	@Autowired
	private ITransaccionEstadoService transaccionEstadoService;
	
	/**
	 * Obtiene todos los estados de transaccion en la base de datos
	 * @return Listado de estados de transaccion
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<TransaccionEstado>> getAll() throws Exception {
		List<TransaccionEstado> transaccionEstadoList = transaccionEstadoService.getAll();
		if(transaccionEstadoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran estados de transaccion en la base de datos");
		}
		return new ResponseEntity<List<TransaccionEstado>>(transaccionEstadoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un estado de transaccion por su id
	 * @param id
	 * @return TransaccionEstado
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TransaccionEstado> getById(@PathVariable("id") Integer id) throws Exception {
		TransaccionEstado transaccionEstado = transaccionEstadoService.getById(id);
		if(transaccionEstado == null) {
			throw new ModelNotFoundException("Estado de transaccion con id " + id + " no encontrado");
		}
		return new ResponseEntity<TransaccionEstado>(transaccionEstado, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo estado de transaccion
	 * No lo guarda cuando encuentra otro estado de transaccion con el mismo nombre
	 * @param transaccionEstadoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody TransaccionEstado transaccionEstadoNew) throws Exception {
		TransaccionEstado transaccionEstado = transaccionEstadoService.create(transaccionEstadoNew);
		if(transaccionEstado==null) {
			throw new Exception("No se ha podido crear el estado de transaccion");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del estado de transaccion buscandolo por su id
	 * @param transaccionEstadoUp
	 * @return TransaccionEstado actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<TransaccionEstado> update(@Valid @RequestBody TransaccionEstado transaccionEstadoUp) throws Exception {
		TransaccionEstado transaccionEstado = transaccionEstadoService.update(transaccionEstadoUp);
		return new ResponseEntity<TransaccionEstado>(transaccionEstado, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un estado de transaccion de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		TransaccionEstado transaccionEstado = transaccionEstadoService.getById(id);
		if(transaccionEstado == null) {
			throw new ModelNotFoundException("Estado de transaccion con id " + id + " no encontrado");
		}
		transaccionEstadoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
