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

import proteus.boleta.model.Boleta;
import proteus.boleta.service.IBoletaService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/boletas")
public class BoletaController {

	@Autowired
	private IBoletaService boletaService;
	
	/**
	 * Obtiene todos las boletas en la base de datos
	 * @return Listado de boletas
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Boleta>> getAll() throws Exception {
		List<Boleta> boletaList = boletaService.getAll();
		if(boletaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran boletas en la base de datos");
		}
		return new ResponseEntity<List<Boleta>>(boletaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos las boletas en la base de datos
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de boletas
	 * @throws Exception
	 */
	@GetMapping("/fecha/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Boleta>> getByFecha(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Boleta> boletaList = boletaService.getByFecha(fechaDesde, fechaHasta);
		if(boletaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran boletas");
		}
		return new ResponseEntity<List<Boleta>>(boletaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos las boletas por banco
	 * @param idBanco
	 * @return Listado de boletas
	 * @throws Exception
	 */
	@GetMapping("/banco/{idBanco}")
	public ResponseEntity<List<Boleta>> getByBanco(@PathVariable("idBanco") Integer idBanco) throws Exception {
		List<Boleta> boletaList = boletaService.getByBanco(idBanco);
		if(boletaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran boletas");
		}
		return new ResponseEntity<List<Boleta>>(boletaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos las boletas por cheque
	 * @param idCheque
	 * @return Listado de boletas
	 * @throws Exception
	 */
	@GetMapping("/cheque/{idCheque}")
	public ResponseEntity<List<Boleta>> getByCheque(@PathVariable("idCheque") Integer idCheque) throws Exception {
		List<Boleta> boletaList = boletaService.getByCheque(idCheque);
		if(boletaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran boletas");
		}
		return new ResponseEntity<List<Boleta>>(boletaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos las boletas por numero
	 * @param numero
	 * @return Listado de boletas
	 * @throws Exception
	 */
	@GetMapping("/numero/{numero}")
	public ResponseEntity<List<Boleta>> getByNumero(@PathVariable("numero") String numero) throws Exception {
		List<Boleta> boletaList = boletaService.getByNumero(numero);
		if(boletaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran boletas");
		}
		return new ResponseEntity<List<Boleta>>(boletaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos las boletas por tipo de documento
	 * @param idBoletaTipoDocumento
	 * @return Listado de boletas
	 * @throws Exception
	 */
	@GetMapping("/boleta-tipo-documento/{idBoletaTipoDocumento}")
	public ResponseEntity<List<Boleta>> getByBoletaTipoDocumento(@PathVariable("idBoletaTipoDocumento") Integer idBoletaTipoDocumento) throws Exception {
		List<Boleta> boletaList = boletaService.getByBoletaTipoDocumento(idBoletaTipoDocumento);
		if(boletaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran boletas");
		}
		return new ResponseEntity<List<Boleta>>(boletaList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos las boletas por cuenta bancaria
	 * @param idCuentaBancaria
	 * @return Listado de boletas
	 * @throws Exception
	 */
	@GetMapping("/cuenta-bancaria/{idCuentaBancaria}")
	public ResponseEntity<List<Boleta>> getByCuentaBancaria(@PathVariable("idCuentaBancaria") Integer idCuentaBancaria) throws Exception {
		List<Boleta> boletaList = boletaService.getByCuentaBancaria(idCuentaBancaria);
		if(boletaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran boletas");
		}
		return new ResponseEntity<List<Boleta>>(boletaList, HttpStatus.OK);
	}
	
	/**
	 * Busca una boleta por su id
	 * @param id
	 * @return Boleta
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Boleta> getById(@PathVariable("id") Integer id) throws Exception {
		Boleta boleta = boletaService.getById(id);
		if(boleta == null) {
			throw new ModelNotFoundException("Boleta con id " + id + " no encontrado");
		}
		return new ResponseEntity<Boleta>(boleta, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva boleta
	 * No lo guarda cuando encuentra otra boleta con el mismo numero y de la misma cuenta bancaria
	 * @param boletaNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Boleta boletaNew) throws Exception {
		Boleta boleta = boletaService.create(boletaNew);
		if(boleta==null) {
			throw new Exception("No se ha podido crear la boleta");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la boleta buscandolo por su id
	 * @param boletaUp
	 * @return Boleta actualizada
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Boleta> update(@Valid @RequestBody Boleta boletaUp) throws Exception {
		Boleta boleta = boletaService.update(boletaUp);
		return new ResponseEntity<Boleta>(boleta, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una boleta de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Boleta boleta = boletaService.getById(id);
		if(boleta == null) {
			throw new ModelNotFoundException("Boleta con id " + id + " no encontrado");
		}
		boletaService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
