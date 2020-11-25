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
import proteus.segmento.model.Segmento;
import proteus.segmento.model.SegmentoCredito;
import proteus.segmento.service.ISegmentoCreditoService;
import proteus.segmento.service.ISegmentoService;

@RestController
@RequestMapping("/segmento-creditos")
public class SegmentoCreditoController {

	@Autowired
	private ISegmentoCreditoService segmentoCreditoService;
	
	@Autowired
	private ISegmentoService segmentoService;
	
	/**
	 * Obtiene todos los creditos de segmentos de la base de datos
	 * @return Listado de creditos de segmentos
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<SegmentoCredito>> getAll() throws Exception {
		List<SegmentoCredito> segmentoCreditoList = segmentoCreditoService.getAll();
		if(segmentoCreditoList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran creditos de los segmentos en la base de datos");
		}
		return new ResponseEntity<List<SegmentoCredito>>(segmentoCreditoList, HttpStatus.OK);
	}
	
	/**
	 * Busca un credito de segmento por su id
	 * @param id
	 * @return Credito de segmento
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<SegmentoCredito> getById(@PathVariable("id") Integer id) throws Exception {
		SegmentoCredito segmentoCredito = segmentoCreditoService.getById(id);
		if(segmentoCredito == null) {
			throw new ModelNotFoundException("Credito del segmento con id " + id + " no encontrado");
		}
		return new ResponseEntity<SegmentoCredito>(segmentoCredito, HttpStatus.OK);
	}
	
	/**
	 * Busca un credito de segmento por segmento
	 * @param idSegmento
	 * @return Credito de segmento
	 * @throws Exception
	 */
	@GetMapping("/segmento/{idSegmento}")
	public ResponseEntity<SegmentoCredito> getBySegmento(@PathVariable("idSegmento") Integer idSegmento) throws Exception {
		SegmentoCredito segmentoCredito = segmentoCreditoService.getBySegmento(idSegmento);
		if(segmentoCredito == null) {
			throw new ModelNotFoundException("Credito del segmento no encontrado");
		}
		return new ResponseEntity<SegmentoCredito>(segmentoCredito, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo credito de segmento
	 * No lo guarda cuando encuentra otro credito de segmento con el mismo segmento
	 * @param segmentoCreditoNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody SegmentoCredito segmentoCreditoNew) throws Exception {
		SegmentoCredito segmentoCredito = segmentoCreditoService.create(segmentoCreditoNew);
		if(segmentoCredito==null) {
			throw new Exception("No se ha podido crear el credito del segmento");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del credito de segmento buscandolo por su id
	 * @param segmentoCreditoUp
	 * @return Credito de segmento actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<SegmentoCredito> update(@Valid @RequestBody SegmentoCredito segmentoCreditoUp) throws Exception {
		SegmentoCredito segmentoCredito = segmentoCreditoService.update(segmentoCreditoUp);
		return new ResponseEntity<SegmentoCredito>(segmentoCredito, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un credito de segmento de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		SegmentoCredito segmentoCredito = segmentoCreditoService.getById(id);
		if(segmentoCredito == null) {
			throw new ModelNotFoundException("Credito del segmento con id " + id + " no encontrado");
		}
		segmentoCreditoService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Elimina un credito de segmento de la base de datos por su segmento
	 * @param idSegmento
	 * @throws Exception
	 */
	@DeleteMapping("/segmento/{idSegmento}")
	public ResponseEntity<Void> deleteBySegmento(@PathVariable("idSegmento") Integer idSegmento) throws Exception {
		Segmento segmento = segmentoService.getById(idSegmento);
		if(segmento == null) {
			throw new ModelNotFoundException("Credito del segmento " + idSegmento + " no encontrado");
		}
		segmentoCreditoService.deleteBySegmento(idSegmento);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
