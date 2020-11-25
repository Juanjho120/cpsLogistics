package proteus.marca.controller;

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
import proteus.marca.model.MarcaAuto;
import proteus.marca.service.IMarcaAutoService;

@RestController
@RequestMapping("/marcas-autos")
public class MarcaAutoController {

	@Autowired
	private IMarcaAutoService marcaAutoService;
	
	/**
	 * Obtiene todas las marcas de autos en la base de datos
	 * @return Listado de marcas de autos
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<MarcaAuto>> getAll() throws Exception {
		List<MarcaAuto> marcaAutoList = marcaAutoService.getAll();
		if(marcaAutoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran marcas de autos en la base de datos");
		}
		return new ResponseEntity<List<MarcaAuto>>(marcaAutoList, HttpStatus.OK);
	}
	
	/**
	 * Busca una marca de auto por su id
	 * @param id
	 * @return Marca de auto
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<MarcaAuto> getById(@PathVariable("id") Integer id) throws Exception {
		MarcaAuto marcaAuto = marcaAutoService.getById(id);
		if(marcaAuto == null) {
			throw new ModelNotFoundException("Marca de auto con id " + id + " no encontrado");
		}
		return new ResponseEntity<MarcaAuto>(marcaAuto, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva marca de auto
	 * No lo guarda cuando encuentra otra marca de auto con el mismo nombre
	 * @param marcaAutoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody MarcaAuto marcaAutoNew) throws Exception {
		MarcaAuto marcaAuto = marcaAutoService.create(marcaAutoNew);
		if(marcaAuto==null) {
			throw new Exception("No se ha podido crear la marca de auto");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la marca de auto buscandolo por su id
	 * @param marcaAutoUp
	 * @return Marca de auto actualizada
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<MarcaAuto> update(@Valid @RequestBody MarcaAuto marcaAutoUp) throws Exception {
		MarcaAuto marcaAuto = marcaAutoService.update(marcaAutoUp);
		return new ResponseEntity<MarcaAuto>(marcaAuto, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una marca de auto de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		MarcaAuto marcaAuto = marcaAutoService.getById(id);
		if(marcaAuto == null) {
			throw new ModelNotFoundException("Marca de auto con id " + id + " no encontrado");
		}
		marcaAutoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
