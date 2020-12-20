package proteus.boleta.controller;

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

import proteus.boleta.model.BoletaTipoDocumento;
import proteus.boleta.service.IBoletaTipoDocumentoService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/boleta-tipos-documentos")
public class BoletaTipoDocumentoController {

	@Autowired
	private IBoletaTipoDocumentoService boletaTipoDocumentoService;
	
	/**
	 * Obtiene todos los tipos de documentos de boleta en la base de datos
	 * @return Listado de tipos de documentos de boleta
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<BoletaTipoDocumento>> getAll() throws Exception {
		List<BoletaTipoDocumento> boletaTipoDocumentoList = boletaTipoDocumentoService.getAll();
		if(boletaTipoDocumentoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran bancos en la base de datos");
		}
		return new ResponseEntity<List<BoletaTipoDocumento>>(boletaTipoDocumentoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un tipo de documento de boleta por su id
	 * @param id
	 * @return BoletaTipoDocumento
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<BoletaTipoDocumento> getById(@PathVariable("id") Integer id) throws Exception {
		BoletaTipoDocumento boletaTipoDocumento = boletaTipoDocumentoService.getById(id);
		if(boletaTipoDocumento == null) {
			throw new ModelNotFoundException("Banco con id " + id + " no encontrado");
		}
		return new ResponseEntity<BoletaTipoDocumento>(boletaTipoDocumento, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo tipo de documento de boleta
	 * No lo guarda cuando encuentra otro tipo de documento de boleta con el mismo nombre
	 * @param boletaTipoDocumentoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody BoletaTipoDocumento boletaTipoDocumentoNew) throws Exception {
		BoletaTipoDocumento boletaTipoDocumento = boletaTipoDocumentoService.create(boletaTipoDocumentoNew);
		if(boletaTipoDocumento==null) {
			throw new Exception("No se ha podido crear el banco");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del tipo de documento de boleta buscandolo por su id
	 * @param boletaTipoDocumentoUp
	 * @return Tipo de documento de boleta actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<BoletaTipoDocumento> update(@Valid @RequestBody BoletaTipoDocumento boletaTipoDocumentoUp) throws Exception {
		BoletaTipoDocumento boletaTipoDocumento = boletaTipoDocumentoService.update(boletaTipoDocumentoUp);
		return new ResponseEntity<BoletaTipoDocumento>(boletaTipoDocumento, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un tipo de documento de boleta de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		BoletaTipoDocumento boletaTipoDocumento = boletaTipoDocumentoService.getById(id);
		if(boletaTipoDocumento == null) {
			throw new ModelNotFoundException("Banco con id " + id + " no encontrado");
		}
		boletaTipoDocumentoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
