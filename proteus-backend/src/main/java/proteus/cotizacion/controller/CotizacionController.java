package proteus.cotizacion.controller;

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

import proteus.cotizacion.model.Cotizacion;
import proteus.cotizacion.service.ICotizacionService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/cotizaciones")
public class CotizacionController {

	@Autowired
	private ICotizacionService cotizacionService;
	
	/**
	 * Obtiene todas las cotizaciones de la base de datos
	 * @return Listado de cotizaciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllServicio')")
	@GetMapping
	public ResponseEntity<List<Cotizacion>> getAll() throws Exception {
		List<Cotizacion> cotizacionList = cotizacionService.getAll();
		if(cotizacionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cotizaciones en la base de datos");
		}
		return new ResponseEntity<List<Cotizacion>>(cotizacionList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las cotizaciones por segmento de la base de datos
	 * @param idSegmento
	 * @return Listado de cotizaciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/segmento/{idSegmento}")
	public ResponseEntity<List<Cotizacion>> getBySegmento(@PathVariable("idSegmento") Integer idSegmento) throws Exception {
		List<Cotizacion> cotizacionList = cotizacionService.getBySegmento(idSegmento);
		if(cotizacionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cotizaciones del segmento "+idSegmento+" en la base de datos");
		}
		return new ResponseEntity<List<Cotizacion>>(cotizacionList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las cotizaciones por usuario de la base de datos
	 * @param idUsuario
	 * @return Listado de cotizaciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<List<Cotizacion>> getByUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
		List<Cotizacion> cotizacionList = cotizacionService.getByUsuario(idUsuario);
		if(cotizacionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cotizaciones del usuario "+idUsuario+" en la base de datos");
		}
		return new ResponseEntity<List<Cotizacion>>(cotizacionList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las cotizaciones por fecha de la base de datos
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de cotizaciones
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/fecha/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Cotizacion>> getByUsuario(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Cotizacion> cotizacionList = cotizacionService.getByFecha(fechaDesde, fechaHasta);
		if(cotizacionList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cotizaciones dentro de las fechas "+fechaDesde+" / "+fechaHasta+" en la base de datos");
		}
		return new ResponseEntity<List<Cotizacion>>(cotizacionList, HttpStatus.OK);
	}
	
	/**
	 * Busca una cotizacion por su id
	 * @param id
	 * @return Cotizacion
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdServicio')")
	@GetMapping("/{id}")
	public ResponseEntity<Cotizacion> getById(@PathVariable("id") Integer id) throws Exception {
		Cotizacion cotizacion = cotizacionService.getById(id);
		if(cotizacion == null) {
			throw new ModelNotFoundException("Cotizacion con id " + id + " no encontrado");
		}
		return new ResponseEntity<Cotizacion>(cotizacion, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva cotizacion
	 * @param cotizacionNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createServicio')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Cotizacion cotizacionNew) throws Exception {
		Cotizacion cotizacion = cotizacionService.create(cotizacionNew);
		if(cotizacion==null) {
			throw new Exception("No se ha podido crear la cotizacion");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la cotizacion buscandolo por su id
	 * @param cotizacionUp
	 * @return Cotizacion actualizada
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateServicio')")
	@PutMapping
	public ResponseEntity<Cotizacion> update(@Valid @RequestBody Cotizacion cotizacionUp) throws Exception {
		Cotizacion cotizacion = cotizacionService.update(cotizacionUp);
		return new ResponseEntity<Cotizacion>(cotizacion, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una cotizacion de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteServicio')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Cotizacion cotizacion = cotizacionService.getById(id);
		if(cotizacion == null) {
			throw new ModelNotFoundException("Cotizacion con id " + id + " no encontrado");
		}
		cotizacionService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
