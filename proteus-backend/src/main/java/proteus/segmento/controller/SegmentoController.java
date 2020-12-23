package proteus.segmento.controller;

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
import proteus.segmento.model.Segmento;
import proteus.segmento.service.ISegmentoService;

@RestController
@RequestMapping("/segmentos")
public class SegmentoController {

	@Autowired
	private ISegmentoService segmentoService;
	
	/**
	 * Obtiene todos los segmentos de la base de datos
	 * @return Listado de segmentos
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAll')")
	@GetMapping
	public ResponseEntity<List<Segmento>> getAll() throws Exception {
		List<Segmento> segmentoList = segmentoService.getAll();
		if(segmentoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran segmentos en la base de datos");
		}
		return new ResponseEntity<List<Segmento>>(segmentoList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los segmentos con credito
	 * @return Listado de segmentos
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/with-credito")
	public ResponseEntity<List<Segmento>> getWithCredito() throws Exception {
		List<Segmento> segmentoList = segmentoService.getWithCredito();
		if(segmentoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran segmentos con credito");
		}
		return new ResponseEntity<List<Segmento>>(segmentoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un segmento por su id
	 * @param id
	 * @return Segmento
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getById')")
	@GetMapping("/{id}")
	public ResponseEntity<Segmento> getById(@PathVariable("id") Integer id) throws Exception {
		Segmento segmento = segmentoService.getById(id);
		if(segmento == null) {
			throw new ModelNotFoundException("Segmento con id " + id + " no encontrado");
		}
		return new ResponseEntity<Segmento>(segmento, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo segmento
	 * No lo guarda cuando encuentra otro segmento con el mismo telefono
	 * @param segmentoNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createAdmin')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Segmento segmentoNew) throws Exception {
		Segmento segmento = segmentoService.create(segmentoNew);
		if(segmento==null) {
			throw new Exception("No se ha podido crear el segmento");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del segmento buscandolo por su id
	 * @param segmentoUp
	 * @return Segmento actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateAdmin')")
	@PutMapping
	public ResponseEntity<Segmento> update(@Valid @RequestBody Segmento segmentoUp) throws Exception {
		Segmento segmento = segmentoService.update(segmentoUp);
		return new ResponseEntity<Segmento>(segmento, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un segmento de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteAdmin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Segmento segmento = segmentoService.getById(id);
		if(segmento == null) {
			throw new ModelNotFoundException("Segmento con id " + id + " no encontrado");
		}
		segmentoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
