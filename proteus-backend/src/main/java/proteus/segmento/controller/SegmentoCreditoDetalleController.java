package proteus.segmento.controller;

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
import proteus.segmento.dto.SegmentoCreditoDetalleDTO;
import proteus.segmento.model.SegmentoCreditoDetalle;
import proteus.segmento.service.ISegmentoCreditoDetalleService;

@RestController
@RequestMapping("/segmento-credito-detalles")
public class SegmentoCreditoDetalleController {

	@Autowired
	private ISegmentoCreditoDetalleService segmentoCreditoDetalleService;
	
	/**
	 * Obtiene todos los detalles de credito de un segmento de la base de datos
	 * @return Listado de detalles de credito de un segmento
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<SegmentoCreditoDetalle>> getAll() throws Exception {
		List<SegmentoCreditoDetalle> segmentoCreditoDetalleList = segmentoCreditoDetalleService.getAll();
		if(segmentoCreditoDetalleList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran detalles de creditos de los segmentos en la base de datos");
		}
		return new ResponseEntity<List<SegmentoCreditoDetalle>>(segmentoCreditoDetalleList, HttpStatus.OK);
	}
	
	/**
	 * Busca un detalle de credito de un segmento por su id
	 * @param id
	 * @return Detalle de credito de un segmento
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<SegmentoCreditoDetalle> getById(@PathVariable("id") Integer id) throws Exception {
		SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.getById(id);
		if(segmentoCreditoDetalle == null) {
			throw new ModelNotFoundException("Detalle de credito del segmento con id " + id + " no encontrado");
		}
		return new ResponseEntity<SegmentoCreditoDetalle>(segmentoCreditoDetalle, HttpStatus.OK);
	}
	
	/**
	 * Busca un detalle de credito de un segmento por servicio
	 * @param idServicio
	 * @return Detalle de credito de un segmento
	 * @throws Exception
	 */
	@GetMapping("/servicio/{idServicio}")
	public ResponseEntity<SegmentoCreditoDetalle> getByServicio(@PathVariable("idServicio") Integer idServicio) throws Exception {
		SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.getByServicio(idServicio);
		if(segmentoCreditoDetalle == null) {
			throw new ModelNotFoundException("Detalle de credito del segmento del servicio " + idServicio + " no encontrado");
		}
		return new ResponseEntity<SegmentoCreditoDetalle>(segmentoCreditoDetalle, HttpStatus.OK);
	}
	
	/**
	 * Busca un detalle de credito de un segmento por segmento
	 * @param idSegmento
	 * @return Lista detalles de credito de un segmento
	 * @throws Exception
	 */
	@GetMapping("/segmento/{idSegmento}")
	public ResponseEntity<List<SegmentoCreditoDetalle>> getBySegmento(@PathVariable("idSegmento") Integer idSegmento) throws Exception {
		List<SegmentoCreditoDetalle> segmentoCreditoDetalleList = segmentoCreditoDetalleService.getBySegmento(idSegmento);
		if(segmentoCreditoDetalleList.isEmpty()) {
			throw new ModelNotFoundException("Detalles de credito del segmento " + idSegmento + " no encontrado");
		}
		return new ResponseEntity<List<SegmentoCreditoDetalle>>(segmentoCreditoDetalleList, HttpStatus.OK);
	}
	
	/**
	 * Busca un detalle de credito de un segmento por segmento
	 * @param idSegmento
	 * @return Lista detalles de credito de un segmento
	 * @throws Exception
	 */
	@GetMapping("/dto/segmento/{idSegmento}")
	public ResponseEntity<List<SegmentoCreditoDetalleDTO>> getDTOBySegmento(@PathVariable("idSegmento") Integer idSegmento) throws Exception {
		List<SegmentoCreditoDetalleDTO> segmentoCreditoDetalleList = segmentoCreditoDetalleService.getDTOBySegmento(idSegmento);
		if(segmentoCreditoDetalleList.isEmpty()) {
			throw new ModelNotFoundException("Detalles de credito no encontrado");
		}
		return new ResponseEntity<List<SegmentoCreditoDetalleDTO>>(segmentoCreditoDetalleList, HttpStatus.OK);
	}
	
	/**
	 * Busca un detalle de credito de un segmento por fecha de emision
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Lista detalles de credito de un segmento
	 * @throws Exception
	 */
	@GetMapping("/fecha-emision/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<SegmentoCreditoDetalle>> getByFechaEmision(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<SegmentoCreditoDetalle> segmentoCreditoDetalleList = segmentoCreditoDetalleService.getByFechaEmision(fechaDesde, fechaHasta);
		if(segmentoCreditoDetalleList.isEmpty()) {
			throw new ModelNotFoundException("Detalles de credito del segmento entre fechas de emision " + fechaDesde + 
					" / " + fechaHasta + " no encontrados");
		}
		return new ResponseEntity<List<SegmentoCreditoDetalle>>(segmentoCreditoDetalleList, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo detalle de credito de un segmento
	 * No lo guarda cuando encuentra otro detalle de credito con el mismo servicio
	 * @param segmentoCreditoDetalleNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody SegmentoCreditoDetalle segmentoCreditoDetalleNew) throws Exception {
		SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.create(segmentoCreditoDetalleNew);
		if(segmentoCreditoDetalle==null) {
			throw new Exception("No se ha podido crear el detalle de credito del segmento");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del detalle de credito de un segmento buscandolo por su id
	 * @param segmentoCreditoDetalleUp
	 * @return Detalle de credito del segmento actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<SegmentoCreditoDetalle> update(@Valid @RequestBody SegmentoCreditoDetalle segmentoCreditoDetalleUp) throws Exception {
		SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.update(segmentoCreditoDetalleUp);
		return new ResponseEntity<SegmentoCreditoDetalle>(segmentoCreditoDetalle, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un detalle de credito de la base de datos por su id
	 * No lo puede eliminar si ya existen pagos hechos
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.getById(id);
		if(segmentoCreditoDetalle == null) {
			throw new ModelNotFoundException("Detalle de credito del segmento con id " + id + " no encontrado");
		}
		segmentoCreditoDetalleService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
