package proteus.credito.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proteus.credito.model.CreditoProveedor;
import proteus.credito.service.ICreditoProveedorService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/creditos-proveedores")
public class CreditoProveedorController {

	@Autowired
	private ICreditoProveedorService creditoProveedorService;
	
	/**
	 * Obtiene todos los creditos a proveedores de la base de datos
	 * @return Listado de creditos a proveedores
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllContable')")
	@GetMapping
	public ResponseEntity<List<CreditoProveedor>> getAll() throws Exception {
		List<CreditoProveedor> creditoProveedorList = creditoProveedorService.getAll();
		if(creditoProveedorList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran creditos de proveedores en la base de datos");
		}
		return new ResponseEntity<List<CreditoProveedor>>(creditoProveedorList, HttpStatus.OK);
	}
	
	/**
	 * Busca un credito a proveedor por su id
	 * @param id
	 * @return Credito a proveedor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdContable')")
	@GetMapping("/{id}")
	public ResponseEntity<CreditoProveedor> getById(@PathVariable("id") Integer id) throws Exception {
		CreditoProveedor creditoProveedor = creditoProveedorService.getById(id);
		if(creditoProveedor == null) {
			throw new ModelNotFoundException("Credito al proveedor con id " + id + " no encontrado");
		}
		return new ResponseEntity<CreditoProveedor>(creditoProveedor, HttpStatus.OK);
	}
	
	/**
	 * Busca un credito a proveedor por proveedor
	 * @param idProveedor
	 * @return Credito a proveedor
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamContable')")
	@GetMapping("/proveedor/{idProveedor}")
	public ResponseEntity<CreditoProveedor> getByProveedor(@PathVariable("idProveedor") Integer idProveedor) throws Exception {
		CreditoProveedor creditoProveedor = creditoProveedorService.getByProveedor(idProveedor);
		if(creditoProveedor == null) {
			throw new ModelNotFoundException("Credito al proveedor del proveedor" + idProveedor + " no encontrado");
		}
		return new ResponseEntity<CreditoProveedor>(creditoProveedor, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo credito a proveedor
	 * No lo guarda si encuentra un credito a proveedor con el mismo proveedor
	 * @param creditoProveedorNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createContable')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody CreditoProveedor creditoProveedorNew) throws Exception {
		CreditoProveedor creditoProveedor = creditoProveedorService.create(creditoProveedorNew);
		if(creditoProveedor==null) {
			throw new Exception("No se ha podido crear el credito al proveedor");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
}
