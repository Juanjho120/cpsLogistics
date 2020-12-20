package proteus.pago.controller;

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
import proteus.pago.model.PagoTipo;
import proteus.pago.service.IPagoTipoService;

@RestController
@RequestMapping("/pago-tipos")
public class PagoTipoController {

	@Autowired
	private IPagoTipoService pagoTipoService;
	
	/**
	 * Obtiene todos los tipos de pagos en la base de datos
	 * @return Listado de tipos de pagos
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<PagoTipo>> getAll() throws Exception {
		List<PagoTipo> pagoTipoList = pagoTipoService.getAll();
		if(pagoTipoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran tipos de pagos en la base de datos");
		}
		return new ResponseEntity<List<PagoTipo>>(pagoTipoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un tipo de pago por su id
	 * @param id
	 * @return PagoTipo
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PagoTipo> getById(@PathVariable("id") Integer id) throws Exception {
		PagoTipo pagoTipo = pagoTipoService.getById(id);
		if(pagoTipo == null) {
			throw new ModelNotFoundException("Tipo de pago con id " + id + " no encontrado");
		}
		return new ResponseEntity<PagoTipo>(pagoTipo, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo tipo de pago
	 * No lo guarda cuando encuentra otro tipo de pago con el mismo nombre
	 * @param pagoTipoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody PagoTipo pagoTipoNew) throws Exception {
		PagoTipo pagoTipo = pagoTipoService.create(pagoTipoNew);
		if(pagoTipo==null) {
			throw new Exception("No se ha podido crear el tipo de pago");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del tipo de pago buscandolo por su id
	 * @param pagoTipoUp
	 * @return Tipo de pago actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<PagoTipo> update(@Valid @RequestBody PagoTipo pagoTipoUp) throws Exception {
		PagoTipo pagoTipo = pagoTipoService.update(pagoTipoUp);
		return new ResponseEntity<PagoTipo>(pagoTipo, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un tipo de pago de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		PagoTipo pagoTipo = pagoTipoService.getById(id);
		if(pagoTipo == null) {
			throw new ModelNotFoundException("Tipo de pago del segmento con id " + id + " no encontrado");
		}
		pagoTipoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
