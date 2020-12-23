package proteus.cheque.controller;

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

import proteus.cheque.model.Cheque;
import proteus.cheque.service.IChequeService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/cheques")
public class ChequeController {

	@Autowired
	private IChequeService chequeService;
	
	/**
	 * Obtiene todos los cheques en la base de datos
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllContable')")
	@GetMapping
	public ResponseEntity<List<Cheque>> getAll() throws Exception {
		List<Cheque> chequeList = chequeService.getAll();
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques en la base de datos");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques que tienen boletas
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/in-boletas")
	public ResponseEntity<List<Cheque>> getInBoletas() throws Exception {
		List<Cheque> chequeList = chequeService.getInBoletas();
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques con boletas");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques que tienen boletas
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/not-in-boletas")
	public ResponseEntity<List<Cheque>> getNotInBoletas() throws Exception {
		List<Cheque> chequeList = chequeService.getNotInBoletas();
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques sin boletas");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques que tienen boletas excepto un cheque
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/not-in-boletas/except/{idCheque}")
	public ResponseEntity<List<Cheque>> getNotInBoletasExceptCheque(@PathVariable("idCheque") Integer idCheque) throws Exception {
		List<Cheque> chequeList = chequeService.getNotInBoletasExceptCheque(idCheque);
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques sin boletas");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques por banco
	 * @param idBanco
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/banco/{idBanco}")
	public ResponseEntity<List<Cheque>> getByBanco(@PathVariable("idBanco") Integer idBanco) throws Exception {
		List<Cheque> chequeList = chequeService.getByBanco(idBanco);
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques por nombre
	 * @param nombre
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<List<Cheque>> getByNombre(@PathVariable("nombre") String nombre) throws Exception {
		List<Cheque> chequeList = chequeService.getByNombre(nombre);
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques por numero
	 * @param numero
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/numero/{numero}")
	public ResponseEntity<List<Cheque>> getByNumero(@PathVariable("numero") String numero) throws Exception {
		List<Cheque> chequeList = chequeService.getByNumero(numero);
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques por fecha de emision
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/fecha-emision/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Cheque>> getByFechaEmision(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Cheque> chequeList = chequeService.getByFechaEmision(fechaDesde, fechaHasta);
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques por fecha de deposito
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/fecha-deposito/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Cheque>> getByFechaDeposito(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Cheque> chequeList = chequeService.getByFechaDeposito(fechaDesde, fechaHasta);
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques que pagan una factura en segmento pago
	 * @param facturaNumero
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/segmento-pago/factura-numero/{facturaNumero}")
	public ResponseEntity<List<Cheque>> getByFacturaNumeroInSegmentoPago(@PathVariable("facturaNumero") String facturaNumero) throws Exception {
		List<Cheque> chequeList = chequeService.getByFacturaNumeroInSegmentoPago(facturaNumero);
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques que pagan una factura en pago proveedor
	 * @param facturaNumero
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/pago-proveedor/factura-codigo/{codigo}")
	public ResponseEntity<List<Cheque>> getByFacturaCodigoInPagoProveedor(@PathVariable("codigo") String codigo) throws Exception {
		List<Cheque> chequeList = chequeService.getByFacturaCodigoInPagoProveedor(codigo);
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los cheques por cuenta bancaria
	 * @param idCuentaBancaria
	 * @return Listado de cheques
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/cuenta-bancaria/{idCuentaBancaria}")
	public ResponseEntity<List<Cheque>> getByCuentaBancaria(@PathVariable("idCuentaBancaria") Integer idCuentaBancaria) throws Exception {
		List<Cheque> chequeList = chequeService.getByCuentaBancaria(idCuentaBancaria);
		if(chequeList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cheques");
		}
		return new ResponseEntity<List<Cheque>>(chequeList, HttpStatus.OK);
	}
	
	/**
	 * Busca un cheque por su id
	 * @param id
	 * @return Cheque
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdContable')")
	@GetMapping("/{id}")
	public ResponseEntity<Cheque> getById(@PathVariable("id") Integer id) throws Exception {
		Cheque cheque = chequeService.getById(id);
		if(cheque == null) {
			throw new ModelNotFoundException("Cheque con id " + id + " no encontrado");
		}
		return new ResponseEntity<Cheque>(cheque, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo cheque
	 * No lo guarda cuando encuentra otro cheque con el mismo numero y cuenta bancaria
	 * @param chequeNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createContable')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Cheque chequeNew) throws Exception {
		Cheque cheque = chequeService.create(chequeNew);
		if(cheque==null) {
			throw new Exception("No se ha podido crear el cheque");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del cheque buscandolo por su id
	 * @param chequeUp
	 * @return Cheque actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateContable')")
	@PutMapping
	public ResponseEntity<Cheque> update(@Valid @RequestBody Cheque chequeUp) throws Exception {
		Cheque cheque = chequeService.update(chequeUp);
		return new ResponseEntity<Cheque>(cheque, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un cheque de la base de datos por su id
	 * No lo borra si ya está asociado a un pago de proveedor o segmento
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteContable')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Cheque cheque = chequeService.getById(id);
		if(cheque == null) {
			throw new ModelNotFoundException("Cheque con id " + id + " no encontrado");
		}
		chequeService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
