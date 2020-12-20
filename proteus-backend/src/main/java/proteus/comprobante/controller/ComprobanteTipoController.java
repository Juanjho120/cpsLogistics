package proteus.comprobante.controller;

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

import proteus.comprobante.model.ComprobanteTipo;
import proteus.comprobante.service.IComprobanteTipoService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/comprobante-tipos")
public class ComprobanteTipoController {

	@Autowired
	private IComprobanteTipoService comprobanteTipoService;
	
	/**
	 * Obtiene todos los tipos de comprobante en la base de datos
	 * @return Listado de tipos de comprobante
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<ComprobanteTipo>> getAll() throws Exception {
		List<ComprobanteTipo> comprobanteTipoList = comprobanteTipoService.getAll();
		if(comprobanteTipoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran tipos de comprobante en la base de datos");
		}
		return new ResponseEntity<List<ComprobanteTipo>>(comprobanteTipoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un tipo de comprobante por su id
	 * @param id
	 * @return ComprobanteTipo
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ComprobanteTipo> getById(@PathVariable("id") Integer id) throws Exception {
		ComprobanteTipo comprobanteTipo = comprobanteTipoService.getById(id);
		if(comprobanteTipo == null) {
			throw new ModelNotFoundException("Tipo de comprobante con id " + id + " no encontrado");
		}
		return new ResponseEntity<ComprobanteTipo>(comprobanteTipo, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo tipo de comprobante
	 * No lo guarda cuando encuentra otro tipo de comprobante con el mismo nombre
	 * @param comprobanteTipoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ComprobanteTipo comprobanteTipoNew) throws Exception {
		ComprobanteTipo comprobanteTipo = comprobanteTipoService.create(comprobanteTipoNew);
		if(comprobanteTipo==null) {
			throw new Exception("No se ha podido crear el tipo de comprobante");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del tipo de comprobante buscandolo por su id
	 * @param comprobanteTipoUp
	 * @return Tipo de comprobante actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<ComprobanteTipo> update(@Valid @RequestBody ComprobanteTipo comprobanteTipoUp) throws Exception {
		ComprobanteTipo comprobanteTipo = comprobanteTipoService.update(comprobanteTipoUp);
		return new ResponseEntity<ComprobanteTipo>(comprobanteTipo, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un tipo de comprobante de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		ComprobanteTipo comprobanteTipo = comprobanteTipoService.getById(id);
		if(comprobanteTipo == null) {
			throw new ModelNotFoundException("Tipo de comprobante con id " + id + " no encontrado");
		}
		comprobanteTipoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
