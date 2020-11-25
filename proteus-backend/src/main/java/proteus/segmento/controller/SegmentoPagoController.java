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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proteus.exception.ModelNotFoundException;
import proteus.segmento.dto.SegmentoPagoDTO;
import proteus.segmento.model.SegmentoPago;
import proteus.segmento.service.ISegmentoPagoService;

@RestController
@RequestMapping("/segmento-pagos")
public class SegmentoPagoController {

	@Autowired
	private ISegmentoPagoService segmentoPagoService;
	
	/**
	 * Obtiene todos los pagos de segmentos de la base de datos
	 * @return Listado de pagos de segmentos
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<SegmentoPago>> getAll() throws Exception {
		List<SegmentoPago> segmentoPagoList = segmentoPagoService.getAll();
		if(segmentoPagoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran pagos de los segmentos en la base de datos");
		}
		return new ResponseEntity<List<SegmentoPago>>(segmentoPagoList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los pagos de segmentos por segmento de la base de datos
	 * @param idSegmento
	 * @return Listado de pagos de segmentos
	 * @throws Exception
	 */
	@GetMapping("/segmento/{idSegmento}")
	public ResponseEntity<List<SegmentoPago>> getBySegmento(@PathVariable("idSegmento") Integer idSegmento) throws Exception {
		List<SegmentoPago> segmentoPagoList = segmentoPagoService.getBySegmento(idSegmento);
		if(segmentoPagoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran pagos del segmento "+idSegmento+" en la base de datos");
		}
		return new ResponseEntity<List<SegmentoPago>>(segmentoPagoList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los pagos de segmentos por segmento de la base de datos
	 * @param idSegmento
	 * @return Listado de pagos de segmentos
	 * @throws Exception
	 */
	@GetMapping("/dto/segmento/{idSegmento}")
	public ResponseEntity<List<SegmentoPagoDTO>> getDTOBySegmento(@PathVariable("idSegmento") Integer idSegmento) throws Exception {
		List<SegmentoPagoDTO> segmentoPagoList = segmentoPagoService.getDTOBySegmento(idSegmento);
		if(segmentoPagoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran pagos del segmento en la base de datos");
		}
		return new ResponseEntity<List<SegmentoPagoDTO>>(segmentoPagoList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los pagos de segmentos por fecha de la base de datos
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Listado de pagos de segmentos
	 * @throws Exception
	 */
	@GetMapping("/fecha-pago/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<SegmentoPago>> getByFechaPago(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaHasta") String fechaHasta) throws Exception {
		List<SegmentoPago> segmentoPagoList = segmentoPagoService.getByFechaPago(fechaDesde, fechaHasta);
		if(segmentoPagoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran pagos en las fechas "+fechaDesde+" / "+fechaHasta+" en la base de datos");
		}
		return new ResponseEntity<List<SegmentoPago>>(segmentoPagoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un pago de segmento por su id
	 * @param id
	 * @return Pago de segmento
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<SegmentoPago> getById(@PathVariable("id") Integer id) throws Exception {
		SegmentoPago segmentoPago = segmentoPagoService.getById(id);
		if(segmentoPago == null) {
			throw new ModelNotFoundException("Pago del segmento con id " + id + " no encontrado");
		}
		return new ResponseEntity<SegmentoPago>(segmentoPago, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo pago de un segmento
	 * No lo guarda cuando el pago excede el total restante del detalle de credito o si ya esta pagado en su totalidad
	 * @param segmentoPagoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody SegmentoPago segmentoPagoNew) throws Exception {
		SegmentoPago segmentoPago = segmentoPagoService.create(segmentoPagoNew);
		if(segmentoPago==null) {
			throw new Exception("No se ha podido crear el pago del segmento");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Elimina un pago de un segmento de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		SegmentoPago segmentoPago = segmentoPagoService.getById(id);
		if(segmentoPago == null) {
			throw new ModelNotFoundException("Pago del segmento con id " + id + " no encontrado");
		}
		segmentoPagoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
