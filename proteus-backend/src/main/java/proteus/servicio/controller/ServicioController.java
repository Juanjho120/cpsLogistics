package proteus.servicio.controller;

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

import proteus.exception.ModelNotFoundException;
import proteus.servicio.model.Servicio;
import proteus.servicio.service.IServicioService;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

	@Autowired
	private IServicioService servicioService;
	
	/**
	 * Obtiene todos los servicios de la base de datos
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAllServicio')")
	@GetMapping
	public ResponseEntity<List<Servicio>> getAll() throws Exception {
		List<Servicio> servicioList = servicioService.getAll();
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios que no tengan checklist
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/not-in-checklist")
	public ResponseEntity<List<Servicio>> getNotInChecklist() throws Exception {
		List<Servicio> servicioList = servicioService.getNotInChecklist();
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios sin checklist");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por placa de la base de datos
	 * @param idPlaca
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/placa/{idPlaca}")
	public ResponseEntity<List<Servicio>> getByPlaca(@PathVariable("idPlaca") Integer idPlaca) throws Exception {
		List<Servicio> servicioList = servicioService.getByPlaca(idPlaca);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios de la placa "+idPlaca+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por finalizacion de la base de datos
	 * @param finalizado
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/finalizado/{finalizado}")
	public ResponseEntity<List<Servicio>> getByFinalizado(@PathVariable("finalizado") Boolean finalizado) throws Exception {
		List<Servicio> servicioList = servicioService.getByFinalizado(finalizado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios con finalizacion "+finalizado+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por finalizacion y facturacion de la base de datos
	 * @param finalizado
	 * @param facturado
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/finalizado/{finalizado}/facturado/{facturado}")
	public ResponseEntity<List<Servicio>> getByFinalizadoAndFacturado(@PathVariable("finalizado") Boolean finalizado, 
			@PathVariable("facturado") Boolean facturado) throws Exception {
		List<Servicio> servicioList = servicioService.getByFinalizadoAndFacturado(finalizado, facturado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios con finalizacion "+finalizado+" y facturacion "+facturado+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por facturacion de la base de datos
	 * @param facturado
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/facturado/{facturado}")
	public ResponseEntity<List<Servicio>> getByFacturado(@PathVariable("facturado") Boolean facturado) throws Exception {
		List<Servicio> servicioList = servicioService.getByFacturado(facturado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios con facturacion "+facturado+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por segmento de la base de datos
	 * @param idSegmento
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/segmento/{idSegmento}")
	public ResponseEntity<List<Servicio>> getBySegmento(@PathVariable("idSegmento") Integer idSegmento) throws Exception {
		List<Servicio> servicioList = servicioService.getBySegmento(idSegmento);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios del segmento "+idSegmento+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por tipo de la base de datos
	 * @param idServicioTipo
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/servicio-tipo/{idServicioTipo}")
	public ResponseEntity<List<Servicio>> getByServicioTipo(@PathVariable("idServicioTipo") Integer idServicioTipo) throws Exception {
		List<Servicio> servicioList = servicioService.getByServicioTipo(idServicioTipo);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios del tipo "+idServicioTipo+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por tipo, finalizado y facturado de la base de datos
	 * @param idServicioTipo
	 * @param finalizado
	 * @param facturado
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/servicio-tipo/{idServicioTipo}/finalizado/{finalizado}/facturado/{facturado}")
	public ResponseEntity<List<Servicio>> getByServicioTipoAndFinalizadoAndFacturado(@PathVariable("idServicioTipo") Integer idServicioTipo, 
			@PathVariable("finalizado") Boolean finalizado, @PathVariable("facturado") Boolean facturado) throws Exception {
		List<Servicio> servicioList = servicioService.getByServicioTipoAndFinalizadoAndFacturado(idServicioTipo, finalizado, facturado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por cotizacion de la base de datos
	 * @param idCotizacion
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/cotizacion/{idCotizacion}")
	public ResponseEntity<List<Servicio>> getByCotizacion(@PathVariable("idCotizacion") Integer idCotizacion) throws Exception {
		List<Servicio> servicioList = servicioService.getByCotizacion(idCotizacion);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios para la cotizacion "+idCotizacion+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por rango de fecha de la base de datos
	 * @param servicioDto
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/fecha/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Servicio>> getByFechaRango(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<Servicio> servicioList = servicioService.getByFechaRango(fechaDesde, fechaHasta);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios dentro del rango de fechas "+fechaDesde
					+" / "+fechaHasta+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por rango de fecha, finalizado y facturado de la base de datos
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param finalizado
	 * @param facturado
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/fecha/{fechaDesde}/{fechaHasta}/finalizado/{finalizado}/facturado/{facturado}")
	public ResponseEntity<List<Servicio>> getByFechaAndFinalizadoAndFacturado(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta, @PathVariable("finalizado") Boolean finalizado, 
			@PathVariable("facturado") Boolean facturado) throws Exception {
		List<Servicio> servicioList = servicioService.getByFechaAndFinalizadoAndFacturado(fechaDesde, fechaHasta, finalizado, facturado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios dentro del rango de fechas "+fechaDesde
					+" / "+fechaHasta+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por placa y finalizado de la base de datos
	 * @param servicioDto
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/placa/{idPlaca}/finalizado/{finalizado}")
	public ResponseEntity<List<Servicio>> getByPlacaAndFinalizado(@PathVariable("idPlaca") Integer idPlaca, 
			@PathVariable("finalizado") Boolean finalizado) throws Exception {
		List<Servicio> servicioList = servicioService.getByPlacaAndFinalizado(idPlaca, finalizado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios de la placa " + idPlaca
					+ " con finalizacion " + finalizado + " en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por placa y facturado de la base de datos
	 * @param servicioDto
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/placa/{idPlaca}/facturado/{facturado}")
	public ResponseEntity<List<Servicio>> getByPlacaAndFacturado(@PathVariable("idPlaca") Integer idPlaca, 
			@PathVariable("facturado") Boolean facturado) throws Exception {
		List<Servicio> servicioList = servicioService.getByPlacaAndFacturado(idPlaca, facturado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios de la placa "+ idPlaca
					+" con facturacion "+facturado+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por placa, finalizado y facturado de la base de datos
	 * @param servicioDto
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/placa/{idPlaca}/finalizado/{finalizado}/facturado/{facturado}")
	public ResponseEntity<List<Servicio>> getByPlacaAndFinalizadoAndFacturado(@PathVariable("idPlaca") Integer idPlaca, 
			@PathVariable("finalizado") Boolean finalizado, @PathVariable("facturado") Boolean facturado) throws Exception {
		List<Servicio> servicioList = servicioService.getByPlacaAndFinalizadoAndFacturado(idPlaca, finalizado, facturado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios de la placa "+idPlaca
					+" con finalizacion "+finalizado+" y facturacion "+facturado+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por segmento y finalizado de la base de datos
	 * @param servicioDto
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/segmento/{idSegmento}/finalizado/{finalizado}")
	public ResponseEntity<List<Servicio>> getBySegmentoAndFinalizado(@PathVariable("idSegmento") Integer idSegmento, 
			@PathVariable("finalizado") Boolean finalizado) throws Exception {
		List<Servicio> servicioList = servicioService.getBySegmentoAndFinalizado(idSegmento, finalizado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios del segmento "+idSegmento
					+" con finalizacion "+finalizado+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por segmento y facturado de la base de datos
	 * @param servicioDto
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/segmento/{idSegmento}/facturado/{facturado}")
	public ResponseEntity<List<Servicio>> getBySegmentoAndFacturado(@PathVariable("idSegmento") Integer idSegmento, 
			@PathVariable("facturado") Boolean facturado) throws Exception {
		List<Servicio> servicioList = servicioService.getBySegmentoAndFacturado(idSegmento, facturado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios del segmento "+idSegmento
					+" con facturacion "+facturado+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por segmento, finalizado y facturado de la base de datos
	 * @param servicioDto
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/segmento/{idSegmento}/finalizado/{finalizado}/facturado/{facturado}")
	public ResponseEntity<List<Servicio>> getBySegmentoAndFinalizadoAndFacturado(@PathVariable("idSegmento") Integer idSegmento, 
			@PathVariable("finalizado") Boolean finalizado, @PathVariable("facturado") Boolean facturado) throws Exception {
		List<Servicio> servicioList = servicioService.getBySegmentoAndFinalizadoAndFacturado(idSegmento, finalizado, facturado);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios del segmento "+idSegmento
					+" con finalizacion "+finalizado+" y facturacion "+facturado+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los servicios por segmento y placa de la base de datos
	 * @param servicioDto
	 * @return Listado de servicios
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParamServicio')")
	@GetMapping("/segmento/{idSegmento}/placa/{idPlaca}")
	public ResponseEntity<List<Servicio>> getBySegmentoAndPlaca(@PathVariable("idSegmento") Integer idSegmento, 
			@PathVariable("idPlaca") Integer idPlaca) throws Exception {
		List<Servicio> servicioList = servicioService.getBySegmentoAndPlaca(idSegmento, idPlaca);
		if(servicioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran servicios del segmento "+idSegmento
					+" y placa "+idPlaca+" en la base de datos");
		}
		return new ResponseEntity<List<Servicio>>(servicioList, HttpStatus.OK);
	}
	
	/**
	 * Busca un servicio por su id
	 * @param id
	 * @return Servicio
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByIdServicio')")
	@GetMapping("/{id}")
	public ResponseEntity<Servicio> getById(@PathVariable("id") Integer id) throws Exception {
		Servicio servicio = servicioService.getById(id);
		if(servicio == null) {
			throw new ModelNotFoundException("Servicio con id " + id + " no encontrado");
		}
		return new ResponseEntity<Servicio>(servicio, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo servicio
	 * No lo guarda cuando encuentra otro servicio con la misma placa y sin finalizar
	 * o cuando no hay suficiente stock para asignarle los repuestos
	 * @param servicioNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createServicio')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Servicio servicioNew) throws Exception {
		Servicio servicio = servicioService.create(servicioNew);
		if(servicio==null) {
			throw new Exception("No se ha podido crear el servicio");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del servicio buscandolo por su id
	 * No lo actualiza si ya esta finalizado el proceso
	 * @param servicioUp
	 * @return Servicio actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateServicio')")
	@PutMapping
	public ResponseEntity<Servicio> update(@Valid @RequestBody Servicio servicioUp) throws Exception {
		Servicio servicio = servicioService.update(servicioUp);
		if(servicio==null) {
			throw new Exception("No se ha podido actualizar el servicio");
		}
		return new ResponseEntity<Servicio>(servicio, HttpStatus.CREATED);
	}
	
	/**
	 * Actualiza el estado finalizado del servicio por id
	 * @param servicio
	 * @return Mensaje de finalizacion
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateServicio')")
	@PutMapping("/finalizado")
	public ResponseEntity<Void> updateFinalizado(@RequestBody Servicio servicio) throws Exception {
		servicioService.updateFinalizado(servicio.getIdServicio(), servicio.getFinalizado());
		/*String mensaje = "Servicio "+servicio.getIdServicio();
		if(servicio.getFinalizado()) {
			mensaje += " ahora esta finalizado";
		} else {
			mensaje += " ahora esta sin finalizar";
		}*/
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un servicio de la base de datos por su id
	 * No lo elimina si ya tiene una factura
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteServicio')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Servicio servicio = servicioService.getById(id);
		if(servicio == null) {
			throw new ModelNotFoundException("Servicio con id " + id + " no encontrado");
		}
		servicioService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
