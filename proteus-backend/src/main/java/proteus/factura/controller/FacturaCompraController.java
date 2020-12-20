package proteus.factura.controller;

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
import proteus.factura.model.FacturaCompra;
import proteus.factura.service.IFacturaCompraService;

@RestController
@RequestMapping("/facturas-compras")
public class FacturaCompraController {

	@Autowired
	private IFacturaCompraService facturaCompraService;
	
	/**
	 * Obtiene todas las facturas de compra de la base de datos
	 * @return Listado de facturas de compra
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<FacturaCompra>> getAll() throws Exception {
		List<FacturaCompra> facturaCompraList = facturaCompraService.getAll();
		if(facturaCompraList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran facturas de compras en la base de datos");
		}
		return new ResponseEntity<List<FacturaCompra>>(facturaCompraList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las facturas de compra que no estan asignadas a un credito de proveedor
	 * @return Listado de facturas de compra
	 * @throws Exception
	 */
	@GetMapping("/not-in-credito-proveedor")
	public ResponseEntity<List<FacturaCompra>> getNotInCreditoProveedorDetalle() throws Exception {
		List<FacturaCompra> facturaCompraList = facturaCompraService.getNotInCreditoProveedorDetalle();
		if(facturaCompraList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran facturas de compras sin asignar");
		}
		return new ResponseEntity<List<FacturaCompra>>(facturaCompraList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las facturas de compra por proveedor de la base de datos
	 * @param idProveedor
	 * @return Listado de facturas de compra
	 * @throws Exception
	 */
	@GetMapping("/proveedor/{idProveedor}")
	public ResponseEntity<List<FacturaCompra>> getByProveedor(@PathVariable("idProveedor") Integer idProveedor) throws Exception {
		List<FacturaCompra> facturaCompraList = facturaCompraService.getByProveedor(idProveedor);
		if(facturaCompraList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran facturas de compras del proveedor "+idProveedor+" en la base de datos");
		}
		return new ResponseEntity<List<FacturaCompra>>(facturaCompraList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las facturas de compra por repuesto de la base de datos
	 * @param idRepuesto
	 * @return Listado de facturas de compra
	 * @throws Exception
	 */
	@GetMapping("/repuesto/{idRepuesto}")
	public ResponseEntity<List<FacturaCompra>> getByRepuesto(@PathVariable("idRepuesto") Integer idRepuesto) throws Exception {
		List<FacturaCompra> facturaCompraList = facturaCompraService.getByRepuesto(idRepuesto);
		if(facturaCompraList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran facturas de compras que contengan el repuesto "+idRepuesto+" en la base de datos");
		}
		return new ResponseEntity<List<FacturaCompra>>(facturaCompraList, HttpStatus.OK);
	}
	
	/**
	 * Busca una factura de compra por su id
	 * @param id
	 * @return Factura de compra
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<FacturaCompra> getById(@PathVariable("id") Integer id) throws Exception {
		FacturaCompra facturaCompra = facturaCompraService.getById(id);
		if(facturaCompra == null) {
			throw new ModelNotFoundException("Factura de compra con id " + id + " no encontrado");
		}
		return new ResponseEntity<FacturaCompra>(facturaCompra, HttpStatus.OK);
	}
	
	/**
	 * Busca una factura de compra por su codigo y proveedor
	 * @param idProveedor
	 * @param codigo
	 * @return Factura de compra
	 * @throws Exception
	 */
	@GetMapping("/proveedor/{idProveedor}/codigo/{codigo}")
	public ResponseEntity<FacturaCompra> getById(@PathVariable("idProveedor") Integer idProveedor, 
			@PathVariable("codigo") String codigo) throws Exception {
		FacturaCompra facturaCompra = facturaCompraService.getByCodigoAndProveedor(codigo, idProveedor);
		if(facturaCompra == null) {
			throw new ModelNotFoundException("Factura de compra con codigo " + codigo + " y del proveedor "+idProveedor+" no encontrado");
		}
		return new ResponseEntity<FacturaCompra>(facturaCompra, HttpStatus.OK);
	}
	
	/**
	 * Busca una factura de compra por fecha
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Lista de facturas de compra
	 * @throws Exception
	 */
	@GetMapping("/fecha/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<FacturaCompra>> getByFecha(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<FacturaCompra> facturaCompraList = facturaCompraService.getByFecha(fechaDesde, fechaHasta);
		if(facturaCompraList.isEmpty()) {
			throw new ModelNotFoundException("Facturas de compra en las fechas " + fechaDesde + " / "+fechaHasta+" no encontradas");
		}
		return new ResponseEntity<List<FacturaCompra>>(facturaCompraList, HttpStatus.OK);
	}
	
	/**
	 * Busca una factura de compra por su vencimiento
	 * @param vencida
	 * @return Lista de facturas de compra
	 * @throws Exception
	 */
	@GetMapping("/vencimiento/{vencida}")
	public ResponseEntity<List<FacturaCompra>> getByVencimiento(@PathVariable("vencida") Boolean vencida) throws Exception {
		List<FacturaCompra> facturaCompraList = facturaCompraService.getByVencimiento(vencida);
		if(facturaCompraList.isEmpty() && vencida) {
			throw new ModelNotFoundException("No se encuentran facturas vencidas");
		} else if(facturaCompraList.isEmpty() && !vencida) {
			throw new ModelNotFoundException("No se encuentran facturas no vencidas");
		}
		return new ResponseEntity<List<FacturaCompra>>(facturaCompraList, HttpStatus.OK);
	}
	
	/**
	 * Busca una factura de compra por estado pagada
	 * @param pagada
	 * @return Lista de facturas de compra
	 * @throws Exception
	 */
	@GetMapping("/pagada/{pagada}")
	public ResponseEntity<List<FacturaCompra>> getByPagada(@PathVariable("pagada") Boolean pagada) throws Exception {
		List<FacturaCompra> facturaCompraList = facturaCompraService.getByPagada(pagada);
		if(facturaCompraList.isEmpty() && pagada) {
			throw new ModelNotFoundException("No se encuentran facturas pagadas");
		} else if(facturaCompraList.isEmpty() && !pagada) {
			throw new ModelNotFoundException("No se encuentran facturas no pagadas");
		}
		return new ResponseEntity<List<FacturaCompra>>(facturaCompraList, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva factura
	 * No lo guarda si encuentra otra factura con el mismo codigo y proveedor
	 * @param facturaCompraNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody FacturaCompra facturaCompraNew) throws Exception {
		FacturaCompra facturaCompra = facturaCompraService.create(facturaCompraNew);
		if(facturaCompra==null) {
			throw new Exception("No se ha podido crear la factura de compra");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la factura de compra buscandolo por su id
	 * @param facturaCompraUp
	 * @return Factura de compra actualizada
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<FacturaCompra> update(@Valid @RequestBody FacturaCompra facturaCompraUp) throws Exception {
		FacturaCompra facturaCompra = facturaCompraService.update(facturaCompraUp);
		return new ResponseEntity<FacturaCompra>(facturaCompra, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una factura de compra de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		FacturaCompra facturaCompra = facturaCompraService.getById(id);
		if(facturaCompra == null) {
			throw new ModelNotFoundException("Factura de compra con id " + id + " no encontrado");
		}
		facturaCompraService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
