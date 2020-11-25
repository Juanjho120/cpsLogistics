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
	@GetMapping("/proveedor/{idProveedor}")
	public ResponseEntity<List<PagoProveedor>> getByProveedor(@PathVariable("idProveedor") Integer idProveedor) throws Exception {
		List<PagoProveedor> pagoProveedorList = pagoProveedorService.getByProveedor(idProveedor);
		if(pagoProveedorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran pagos para el proveedor "+idProveedor+" en la base de datos");
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
	@GetMapping("/{id}")
	public ResponseEntity<PagoProveedor> getById(@PathVariable("id") Integer id) throws Exception {
		PagoProveedor pagoProveedor = pagoProveedorService.getById(id);
		if(pagoProveedor == null) {
			throw new ModelNotFoundException("Pago al proveedor con id " + id + " no encontrado");
		}
		return new ResponseEntity<PagoProveedor>(pagoProveedor, HttpStatus.OK);
	}
	
	/**
	 * Busca un pago a proveedor por su numero de documento
	 * @param noDocumento
	 * @return Pago a proveedor
	 * @throws Exception
	 */
	@GetMapping("/no-documento/{noDocumento}")
	public ResponseEntity<PagoProveedor> getByNoDocumento(@PathVariable("noDocumento") String noDocumento) throws Exception {
		PagoProveedor pagoProveedor = pagoProveedorService.getByNoDocumento(noDocumento);
		if(pagoProveedor == null) {
			throw new ModelNotFoundException("Pago al proveedor con numero de documento " + noDocumento + " no encontrado");
		}
		return new ResponseEntity<PagoProveedor>(pagoProveedor, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo pago a proveedor
	 * Por el momento va a seguir guardando hasta que se tenga saldo a favor (signo negativo)
	 * No lo guarda si encuentra otro pago con el mismo numero de documento
	 * @param pagoProveedorNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody PagoProveedor pagoProveedorNew) throws Exception {
		PagoProveedor pagoProveedor = pagoProveedorService.create(pagoProveedorNew);
		if(pagoProveedor==null) {
			throw new Exception("No se ha podido crear el pago al proveedor");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza solo la el monto y observaciones del detalle de credito al proveedor buscandolo por su id
	 * @param pagoProveedorUp
	 * @return Detalle de credito a proveedor actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<PagoProveedor> update(@Valid @RequestBody PagoProveedor pagoProveedorUp) throws Exception {
		PagoProveedor pagoProveedor = pagoProveedorService.update(pagoProveedorUp);
		return new ResponseEntity<PagoProveedor>(pagoProveedor, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un pago a proveedor de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
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
