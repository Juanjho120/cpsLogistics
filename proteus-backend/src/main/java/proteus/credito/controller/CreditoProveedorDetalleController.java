package proteus.credito.controller;

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

import proteus.credito.dto.FacturaProveedorDTO;
import proteus.credito.model.CreditoProveedorDetalle;
import proteus.credito.service.ICreditoProveedorDetalleService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/credito-proveedor-detalles")
public class CreditoProveedorDetalleController {

	@Autowired
	private ICreditoProveedorDetalleService creditoProveedorDetalleService;
	
	/**
	 * Obtiene todos los detalles de creditos a proveedores de la base de datos
	 * @return Listado de detalles de creditos a proveedores
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllContable')")
	@GetMapping
	public ResponseEntity<List<CreditoProveedorDetalle>> getAll() throws Exception {
		List<CreditoProveedorDetalle> creditoProveedorDetalleList = creditoProveedorDetalleService.getAll();
		if(creditoProveedorDetalleList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran detalles de creditos al proveedor en la base de datos");
		}
		return new ResponseEntity<List<CreditoProveedorDetalle>>(creditoProveedorDetalleList, HttpStatus.OK);
	}
	
	/**
	 * Busca un detalle de credito a proveedor por su id
	 * @param id
	 * @return Detalle de credito a proveedor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdContable')")
	@GetMapping("/{id}")
	public ResponseEntity<CreditoProveedorDetalle> getById(@PathVariable("id") Integer id) throws Exception {
		CreditoProveedorDetalle creditoProveedorDetalle = creditoProveedorDetalleService.getById(id);
		if(creditoProveedorDetalle == null) {
			throw new ModelNotFoundException("Detalle del credito al proveedor con id " + id + " no encontrado");
		}
		return new ResponseEntity<CreditoProveedorDetalle>(creditoProveedorDetalle, HttpStatus.OK);
	}
	
	/**
	 * Busca un detalle de credito a proveedor por factura de compra
	 * @param idFacturaCompra
	 * @return Detalle de credito a proveedor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/factura-compra/{idFacturaCompra}")
	public ResponseEntity<CreditoProveedorDetalle> getByFacturaCompra(@PathVariable("idFacturaCompra") Integer idFacturaCompra) throws Exception {
		CreditoProveedorDetalle creditoProveedorDetalle = creditoProveedorDetalleService.getByFacturaCompra(idFacturaCompra);
		if(creditoProveedorDetalle == null) {
			throw new ModelNotFoundException("Detalle del credito al proveedor con factura de compra " + idFacturaCompra + " no encontrado");
		}
		return new ResponseEntity<CreditoProveedorDetalle>(creditoProveedorDetalle, HttpStatus.OK);
	}
	
	/**
	 * Busca un detalle de credito a proveedor por credito
	 * @param idCreditoProveedor
	 * @return Detalle de credito a proveedor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/credito-proveedor/{idCreditoProveedor}")
	public ResponseEntity<List<CreditoProveedorDetalle>> getByCreditoProveedor(@PathVariable("idCreditoProveedor") Integer idCreditoProveedor) throws Exception {
		List<CreditoProveedorDetalle> creditoProveedorDetalleList = creditoProveedorDetalleService.getByCreditoProveedor(idCreditoProveedor);
		if(creditoProveedorDetalleList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran detalles de crédito para dicho crédito");
		}
		return new ResponseEntity<List<CreditoProveedorDetalle>>(creditoProveedorDetalleList, HttpStatus.OK);
	}
	
	/**
	 * Busca un detalle de credito a proveedor por credito y si estan o no pagadas las facturas
	 * @param idCreditoProveedor
	 * @param pagada
	 * @return Detalle de credito a proveedor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/credito-proveedor/{idCreditoProveedor}/pagada/{pagada}")
	public ResponseEntity<List<CreditoProveedorDetalle>> getByCreditoProveedorAndPagada(@PathVariable("idCreditoProveedor") Integer idCreditoProveedor,
			@PathVariable("pagada") Boolean pagada) throws Exception {
		List<CreditoProveedorDetalle> creditoProveedorDetalleList = creditoProveedorDetalleService.getByCreditoProveedorAndPagada(idCreditoProveedor, pagada);
		if(creditoProveedorDetalleList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran detalles de crédito");
		}
		return new ResponseEntity<List<CreditoProveedorDetalle>>(creditoProveedorDetalleList, HttpStatus.OK);
	}
	
	/**
	 * Busca los detalles de creditos por el estado pagada
	 * @param pagada
	 * @return Detalle de credito a proveedor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/pagada/{pagada}")
	public ResponseEntity<List<CreditoProveedorDetalle>> getByPagada(@PathVariable("pagada") Boolean pagada) throws Exception {
		List<CreditoProveedorDetalle> creditoProveedorDetalleList = creditoProveedorDetalleService.getByPagada(pagada);
		if(creditoProveedorDetalleList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran detalles de crédito");
		}
		return new ResponseEntity<List<CreditoProveedorDetalle>>(creditoProveedorDetalleList, HttpStatus.OK);
	}
	
	/**
	 * Busca los detalles de creditos como factura por proveedor
	 * @param pagada
	 * @return Detalle de credito como factura por proveedor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/factura/proveedor/{idProveedor}")
	public ResponseEntity<List<FacturaProveedorDTO>> getFacturaByProveedor(@PathVariable("idProveedor") Integer idProveedor) throws Exception {
		List<FacturaProveedorDTO> facturaDtoList = creditoProveedorDetalleService.getFacturaByProveedor(idProveedor);
		if(facturaDtoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran facturas");
		}
		return new ResponseEntity<List<FacturaProveedorDTO>>(facturaDtoList, HttpStatus.OK);
	}
	
	/**
	 * Busca los detalles de creditos como factura por fecha
	 * @param pagada
	 * @return Detalle de credito como factura por fecha
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/factura/fecha-factura/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<FacturaProveedorDTO>> getFacturaByFecha(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<FacturaProveedorDTO> facturaDtoList = creditoProveedorDetalleService.getFacturaByFecha(fechaDesde, fechaHasta);
		if(facturaDtoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran facturas");
		}
		return new ResponseEntity<List<FacturaProveedorDTO>>(facturaDtoList, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo detalle de credito a proveedor
	 * No se puede crear si existe otro detalle de credito con la misma factura
	 * @param creditoProveedorDetalleNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createContable')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody CreditoProveedorDetalle creditoProveedorDetalleNew) throws Exception {
		CreditoProveedorDetalle creditoProveedorDetalle = creditoProveedorDetalleService.create(creditoProveedorDetalleNew);
		if(creditoProveedorDetalle==null) {
			throw new Exception("No se ha podido crear el detalle del credito al proveedor");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza solo la descripcion y observaciones del detalle de credito al proveedor buscandolo por su id
	 * @param creditoProveedorDetalleUp
	 * @return Detalle de credito a proveedor actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateContable')")
	@PutMapping
	public ResponseEntity<CreditoProveedorDetalle> update(@RequestBody CreditoProveedorDetalle creditoProveedorDetalleUp) throws Exception {
		CreditoProveedorDetalle creditoProveedorDetalle = creditoProveedorDetalleService.update(creditoProveedorDetalleUp);
		return new ResponseEntity<CreditoProveedorDetalle>(creditoProveedorDetalle, HttpStatus.CREATED);
	}
	
	/**
	 * Revisa las fechas de todas las facturas para determinar si ya vencieron
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateContable')")
	@PutMapping("/check-vencimiento")
	public ResponseEntity<Void> checkVencimiento() throws Exception {
		creditoProveedorDetalleService.checkFacturasVencidas();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un detalle de credito al proveedor de la base de datos por su id
	 * No puede eliminarse si ya tiene un pago asociado
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteContable')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		CreditoProveedorDetalle creditoProveedorDetalle = creditoProveedorDetalleService.getById(id);
		if(creditoProveedorDetalle == null) {
			throw new ModelNotFoundException("Detalle del credito al proveedor con id " + id + " no encontrado");
		}
		creditoProveedorDetalleService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
