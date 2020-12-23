package proteus.pago.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proteus.exception.ModelNotFoundException;
import proteus.pago.dto.PagoProveedorTransaccionChequeDTO;
import proteus.pago.model.PagoProveedor;
import proteus.pago.service.IPagoProveedorService;

@RestController
@RequestMapping("/pagos-proveedores")
public class PagoProveedorController {

	@Autowired
	private IPagoProveedorService pagoProveedorService;
	
	/**
	 * Obtiene todos los pagos a proveedores de la base de datos
	 * @return Listado de pagos a proveedores
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllContable')")
	@GetMapping
	public ResponseEntity<List<PagoProveedor>> getAll() throws Exception {
		List<PagoProveedor> pagoProveedorList = pagoProveedorService.getAll();
		if(pagoProveedorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran pagos a proveedores en la base de datos");
		}
		return new ResponseEntity<List<PagoProveedor>>(pagoProveedorList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los pagos a proveedores por proveedor de la base de datos
	 * @param idProveedor
	 * @return Listado de pagos a proveedores
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/proveedor/{idProveedor}")
	public ResponseEntity<List<PagoProveedor>> getByProveedor(@PathVariable("idProveedor") Integer idProveedor) throws Exception {
		List<PagoProveedor> pagoProveedorList = pagoProveedorService.getByProveedor(idProveedor);
		if(pagoProveedorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran pagos para el proveedor "+idProveedor+" en la base de datos");
		}
		return new ResponseEntity<List<PagoProveedor>>(pagoProveedorList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los pagos a proveedores por credito proveedor de la base de datos
	 * @param idCreditoProveedor
	 * @return Listado de pagos a proveedores
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/credito-proveedor/{idCreditoProveedor}")
	public ResponseEntity<List<PagoProveedor>> getByCreditoProveedor(@PathVariable("idCreditoProveedor") Integer idCreditoProveedor) throws Exception {
		List<PagoProveedor> pagoProveedorList = pagoProveedorService.getByCreditoProveedor(idCreditoProveedor);
		if(pagoProveedorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran pagos para dicho credito");
		}
		return new ResponseEntity<List<PagoProveedor>>(pagoProveedorList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los pagos a proveedores por fecha de la base de datos
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de pagos a proveedores
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/fecha-pago/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<PagoProveedor>> getByFechaPago(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<PagoProveedor> pagoProveedorList = pagoProveedorService.getByFechaPago(fechaDesde, fechaHasta);
		if(pagoProveedorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran pagos dentro de las fechas "+fechaDesde+" / "+fechaHasta+" en la base de datos");
		}
		return new ResponseEntity<List<PagoProveedor>>(pagoProveedorList, HttpStatus.OK);
	}
	
	/**
	 * Busca un pago a proveedor por su id
	 * @param id
	 * @return Pago a proveedor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdContable')")
	@GetMapping("/{id}")
	public ResponseEntity<PagoProveedor> getById(@PathVariable("id") Integer id) throws Exception {
		PagoProveedor pagoProveedor = pagoProveedorService.getById(id);
		if(pagoProveedor == null) {
			throw new ModelNotFoundException("Pago al proveedor con id " + id + " no encontrado");
		}
		return new ResponseEntity<PagoProveedor>(pagoProveedor, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo pago para un proveedor junto con sus documentos (cheque, transaccion o efectivo)
	 * No lo guarda cuando ya esta pagado en su totalidad
	 * @param pagoProveedorDtoNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createContable')")
	@PostMapping("/dto")
	public ResponseEntity<Void> createDTO(@Valid @RequestBody PagoProveedorTransaccionChequeDTO pagoProveedorDtoNew) throws Exception {
		pagoProveedorService.createDTO(pagoProveedorDtoNew);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un pago a proveedor de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteContable')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		PagoProveedor pagoProveedor = pagoProveedorService.getById(id);
		if(pagoProveedor == null) {
			throw new ModelNotFoundException("Pago al proveedor con id " + id + " no encontrado");
		}
		pagoProveedorService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
