package proteus.inventario.controller;

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
import proteus.inventario.dto.InventarioEntradaSalidaDTO;
import proteus.inventario.model.Inventario;
import proteus.inventario.service.IInventarioService;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {

	@Autowired
	private IInventarioService inventarioService;
	
	/**
	 * Obtiene todos los inventarios de la base de datos
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Inventario>> getAll() throws Exception {
		List<Inventario> inventarioList = inventarioService.getAll();
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por concepto de la base de datos
	 * @param idConcepto
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/concepto/{idConcepto}")
	public ResponseEntity<List<Inventario>> getByConcepto(@PathVariable("idConcepto") Integer idConcepto) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByConcepto(idConcepto);
		if(inventarioList.isEmpty()) {
			//return new ResponseEntity<List<Inventario>>(HttpStatus.NO_CONTENT);
			throw new ModelNotFoundException("No se encuentran inventarios del concepto "+idConcepto+" en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por concepto y repuesto de la base de datos
	 * @param idConcepto
	 * @param idRepuesto
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/concepto/{idConcepto}/repuesto/{idRepuesto}")
	public ResponseEntity<List<Inventario>> getByConceptoAndRepuesto(@PathVariable("idConcepto") Integer idConcepto, 
			@PathVariable("idRepuesto") Integer idRepuesto) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByConceptoAndRepuesto(idConcepto, idRepuesto);
		if(inventarioList.isEmpty()) {
			//return new ResponseEntity<List<Inventario>>(HttpStatus.NO_CONTENT);
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por concepto y usuario de la base de datos
	 * @param idConcepto
	 * @param idUsuario
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/concepto/{idConcepto}/usuario/{idUsuario}")
	public ResponseEntity<List<Inventario>> getByConceptoAndUsuario(@PathVariable("idConcepto") Integer idConcepto, 
			@PathVariable("idUsuario") Integer idUsuario) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByConceptoAndUsuario(idConcepto, idUsuario);
		if(inventarioList.isEmpty()) {
			//return new ResponseEntity<List<Inventario>>(HttpStatus.NO_CONTENT);
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por concepto, repuesto y usuario de la base de datos
	 * @param idConcepto
	 * @param idRepuesto
	 * @param idUsuario
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/concepto/{idConcepto}/repuesto/{idRepuesto}/usuario/{idUsuario}")
	public ResponseEntity<List<Inventario>> getByConceptoAndRepuestoAndUsuario(@PathVariable("idConcepto") Integer idConcepto, 
			@PathVariable("idRepuesto") Integer idRepuesto, @PathVariable("idUsuario") Integer idUsuario) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByConceptoAndRepuestoAndUsuario(idConcepto, idRepuesto, idUsuario);
		if(inventarioList.isEmpty()) {
			//return new ResponseEntity<List<Inventario>>(HttpStatus.NO_CONTENT);
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por concepto, repuesto y fecha de la base de datos
	 * @param idConcepto
	 * @param idRepuesto
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/concepto/{idConcepto}/repuesto/{idRepuesto}/fecha-rango/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Inventario>> getByConceptoAndRepuestoAndFecha(@PathVariable("idConcepto") Integer idConcepto, 
			@PathVariable("idRepuesto") Integer idRepuesto, @PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByConceptoAndRepuestoAndFecha(idConcepto, idRepuesto, fechaDesde, fechaHasta);
		if(inventarioList.isEmpty()) {
			//return new ResponseEntity<List<Inventario>>(HttpStatus.NO_CONTENT);
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por concepto, repuesto y fecha de la base de datos
	 * @param idConcepto
	 * @param idRepuesto
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idUsuario
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/concepto/{idConcepto}/repuesto/{idRepuesto}/fecha-rango/{fechaDesde}/{fechaHasta}/usuario/{idUsuario}")
	public ResponseEntity<List<Inventario>> getByConceptoAndRepuestoAndFechaAndUsuario(@PathVariable("idConcepto") Integer idConcepto, 
			@PathVariable("idRepuesto") Integer idRepuesto, @PathVariable("idUsuario") Integer idUsuario, 
			@PathVariable("fechaDesde") String fechaDesde, @PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByConceptoAndRepuestoAndFechaAndUsuario(idConcepto, idRepuesto, 
				fechaDesde, fechaHasta, idUsuario);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por repuesto de la base de datos
	 * @param idRepuesto
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/repuesto/{idRepuesto}")
	public ResponseEntity<List<Inventario>> getByRepuesto(@PathVariable("idRepuesto") Integer idRepuesto) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByRepuesto(idRepuesto);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios con repuesto "+idRepuesto+" en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por repuesto y usuario de la base de datos
	 * @param idRepuesto
	 * @param idUsuario
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/repuesto/{idRepuesto}/usuario/{idUsuario}")
	public ResponseEntity<List<Inventario>> getByRepuestoAndUsuario(@PathVariable("idRepuesto") Integer idRepuesto, 
			@PathVariable("idUsuario") Integer idUsuario) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByRepuestoAndUsuario(idRepuesto, idUsuario);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por repuesto y fecha de la base de datos
	 * @param idRepuesto
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/repuesto/{idRepuesto}/fecha-rango/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Inventario>> getByRepuestoAndFechaRango(@PathVariable("idRepuesto") Integer idRepuesto, 
			@PathVariable("fechaDesde") String fechaDesde, @PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByRepuestoAndFechaRango(idRepuesto, fechaDesde, fechaHasta);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por repuesto, fecha y usuario de la base de datos
	 * @param idRepuesto
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idUsuario
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/repuesto/{idRepuesto}/fecha-rango/{fechaDesde}/{fechaHasta}/usuario/{idUsuario}")
	public ResponseEntity<List<Inventario>> getByRepuestoAndFechaAndUsuario(@PathVariable("idRepuesto") Integer idRepuesto, 
			@PathVariable("fechaDesde") String fechaDesde, @PathVariable("fechaHasta") String fechaHasta, 
			@PathVariable("idUsuario") Integer idUsuario) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByRepuestoAndFechaAndUsuario(idRepuesto, fechaDesde, fechaHasta, idUsuario);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por usuario de la base de datos
	 * @param idUsuario
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<List<Inventario>> getByUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByUsuario(idUsuario);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios del usuario "+idUsuario+" en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por usuario y fecha de la base de datos
	 * @param idUsuario
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/usuario/{idUsuario}/fecha-rango/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Inventario>> getByUsuarioAndFechaRango(@PathVariable("idUsuario") Integer idUsuario, 
			@PathVariable("fechaDesde") String fechaDesde, @PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByUsuarioAndFechaRango(idUsuario, fechaDesde, fechaHasta);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por rango de fecha de la base de datos
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/fecha-rango/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Inventario>> getByFechaRango(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByFechaRango(fechaDesde, fechaHasta);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios en el rango de fecha "+fechaDesde
					+" / "+fechaHasta+" en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los repuestos con la cantidad total de entradas y salidas que se tuvieron en un rango de fechas
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/entradas-salidas/fecha-rango/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<InventarioEntradaSalidaDTO>> getInventarioEntradaSalidaByFechaRango(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<InventarioEntradaSalidaDTO> inventarioList = inventarioService.getInventarioEntradaSalidaByFechaRango(fechaDesde, fechaHasta);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<InventarioEntradaSalidaDTO>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por concepto y rango de fecha de la base de datos
	 * @param idConcepto
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/concepto/{idConcepto}/fecha-rango/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Inventario>> getByConceptoAndFechaRango(@PathVariable("idConcepto") Integer idConcepto, 
			@PathVariable("fechaDesde") String fechaDesde, @PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByConceptoAndFechaRango(idConcepto, fechaDesde, fechaHasta);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios en el rango de fecha "+fechaDesde
					+" / "+fechaHasta+" y concepto "+idConcepto+" en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los inventarios por concepto, rango de fecha y usuario de la base de datos
	 * @param idConcepto
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param idUsuario
	 * @return Listado de inventarios
	 * @throws Exception
	 */
	@GetMapping("/concepto/{idConcepto}/fecha-rango/{fechaDesde}/{fechaHasta}/usuario/{idUsuario}")
	public ResponseEntity<List<Inventario>> getByConceptoAndFechaAndUsuario(@PathVariable("idConcepto") Integer idConcepto, 
			@PathVariable("fechaDesde") String fechaDesde, @PathVariable("fechaHasta") String fechaHasta, 
			@PathVariable("idUsuario") Integer idUsuario) throws Exception {
		List<Inventario> inventarioList = inventarioService.getByConceptoAndFechaAndUsuario(idConcepto, fechaDesde, fechaHasta, idUsuario);
		if(inventarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran inventarios en la base de datos");
		}
		return new ResponseEntity<List<Inventario>>(inventarioList, HttpStatus.OK);
	}
	
	/**
	 * Busca un inventario por su id
	 * @param id
	 * @return Inventario
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Inventario> getById(@PathVariable("id") Integer id) throws Exception {
		Inventario inventario = inventarioService.getById(id);
		if(inventario == null) {
			throw new ModelNotFoundException("Inventario con id " + id + " no encontrado");
		}
		return new ResponseEntity<Inventario>(inventario, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo inventario
	 * @param inventarioNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Inventario inventarioNew) throws Exception {
		Inventario inventario = inventarioService.create(inventarioNew);
		if(inventario==null) {
			throw new Exception("No se ha podido crear el inventario");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del inventario buscandolo por su id
	 * @param inventarioUp
	 * @return Inventario actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Inventario> update(@Valid @RequestBody Inventario inventarioUp) throws Exception {
		Inventario inventario = inventarioService.update(inventarioUp);
		return new ResponseEntity<Inventario>(inventario, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un inventario de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Inventario inventario = inventarioService.getById(id);
		if(inventario == null) {
			throw new ModelNotFoundException("Inventario con id " + id + " no encontrado");
		}
		inventarioService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
