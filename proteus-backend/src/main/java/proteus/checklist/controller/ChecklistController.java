package proteus.checklist.controller;

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

import proteus.checklist.dto.ChecklistChecklistEvaluacionDTO;
import proteus.checklist.model.Checklist;
import proteus.checklist.service.IChecklistService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/checklists")
public class ChecklistController {

	@Autowired
	private IChecklistService checklistService;
	
	/**
	 * Obtiene todos los checklists de la base de datos
	 * @return Listado de checklists
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getAll')")
	@GetMapping
	public ResponseEntity<List<Checklist>> getAll() throws Exception {
		List<Checklist> checklistList = checklistService.getAll();
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran checklists en la base de datos");
		}
		return new ResponseEntity<List<Checklist>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene todos los checklists con servicios que no estan finalizados
	 * @return Listado de checklists
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/not-finalizado")
	public ResponseEntity<List<Checklist>> getAllNotFinalizado() throws Exception {
		List<Checklist> checklistList = checklistService.getAllNotFinalizado();
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran checklists en la base de datos");
		}
		return new ResponseEntity<List<Checklist>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Busca un checklist por su id
	 * @param id
	 * @return Checklist
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getById')")
	@GetMapping("/{id}")
	public ResponseEntity<Checklist> getById(@PathVariable("id") Integer id) throws Exception {
		Checklist checklist = checklistService.getById(id);
		if(checklist == null) {
			throw new ModelNotFoundException("Checklist con id " + id + " no encontrado");
		}
		return new ResponseEntity<Checklist>(checklist, HttpStatus.OK);
	}
	
	/**
	 * Busca un checklist por servicio
	 * @param idServicio
	 * @return Checklist
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/servicio/{idServicio}")
	public ResponseEntity<Checklist> getByServicio(@PathVariable("idServicio") Integer idServicio) throws Exception {
		Checklist checklist = checklistService.getByServicio(idServicio);
		if(checklist == null) {
			throw new ModelNotFoundException("Checklist para el servicio " + idServicio + " no encontrado");
		}
		return new ResponseEntity<Checklist>(checklist, HttpStatus.OK);
	}
	
	/**
	 * Busca un checklist por servicio finalizado
	 * @param idServicio
	 * @return Checklist
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/servicio/finalizado/{idServicio}")
	public ResponseEntity<Checklist> getByServicioFinalizado(@PathVariable("idServicio") Integer idServicio) throws Exception {
		Checklist checklist = checklistService.getByServicioFinalizado(idServicio);
		if(checklist == null) {
			throw new ModelNotFoundException("Checklist para el servicio " + idServicio + " y finalizado no encontrado");
		}
		return new ResponseEntity<Checklist>(checklist, HttpStatus.OK);
	}
	
	/**
	 * Busca un checklist o listado de checklists por placa y servicio finalizado
	 * @param idPlaca
	 * @param finalizado
	 * @return Checklist List
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/placa/{idPlaca}/finalizado/{finalizado}")
	public ResponseEntity<List<Checklist>> getByPlacaAndServicioFinalizado(@PathVariable("idPlaca") Integer idPlaca, 
			@PathVariable("finalizado") Boolean finalizado) throws Exception {
		List<Checklist> checklistList = checklistService.getByPlacaAndServicioFinalizado(idPlaca, finalizado);
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("Checklists no encontrados");
		}
		return new ResponseEntity<List<Checklist>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Busca un checklist o listado de checklists por placa
	 * @param idPlaca
	 * @return Checklist List
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/placa/{idPlaca}")
	public ResponseEntity<List<Checklist>> getByPlaca(@PathVariable("idPlaca") Integer idPlaca) throws Exception {
		List<Checklist> checklistList = checklistService.getByPlaca(idPlaca);
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("Checklists no encontrados");
		}
		return new ResponseEntity<List<Checklist>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Busca un checklist por numero de orden de trabajo
	 * @param noOrdenTrabajo
	 * @return Checklist
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/no-orden-trabajo/{noOrdenTrabajo}")
	public ResponseEntity<Checklist> getByNoOrdenTrabajo(@PathVariable("noOrdenTrabajo") String noOrdenTrabajo) throws Exception {
		Checklist checklist = checklistService.getByNoOrdenTrabajo(noOrdenTrabajo);
		if(checklist == null) {
			throw new ModelNotFoundException("Checklist con numero de trabajo " + noOrdenTrabajo + " no encontrado");
		}
		return new ResponseEntity<Checklist>(checklist, HttpStatus.OK);
	}
	
	/**
	 * Busca un checklist por fecha de ingreso
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Lista de checklists
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/fecha-ingreso/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Checklist>> getByFechaHoraIngreso(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaDesde") String fechaHasta) throws Exception {
		List<Checklist> checklistList = checklistService.getByFechaHoraIngreso(fechaDesde, fechaHasta);
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("Checklists con fecha de ingreso entre " + fechaDesde + " / " + fechaHasta + " no encontrados");
		}
		return new ResponseEntity<List<Checklist>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Busca un checklist por fecha de revision
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return Lista de checklists
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/fecha-revision/{fechaDesde}/{fechaHasta}")
	public ResponseEntity<List<Checklist>> getByFechaRevision(@PathVariable("fechaDesde") String fechaDesde, 
			@PathVariable("fechaDesde") String fechaHasta) throws Exception {
		List<Checklist> checklistList = checklistService.getByFechaHoraIngreso(fechaDesde, fechaHasta);
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("Checklists con fecha de revision entre " + fechaDesde + " / " + fechaHasta + " no encontrados");
		}
		return new ResponseEntity<List<Checklist>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene los checklists por mecanico de la base de datos
	 * @param idPersonal
	 * @return Listado de checklists
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/mecanico/{idPersonal}")
	public ResponseEntity<List<Checklist>> getByMecanico(@PathVariable("idPersonal") Integer idPersonal) throws Exception {
		List<Checklist> checklistList = checklistService.getByMecanico(idPersonal);
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran checklists para el mecanico "+idPersonal+" en la base de datos");
		}
		return new ResponseEntity<List<Checklist>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene los checklists por supervisor de la base de datos
	 * @param idPersonal
	 * @return Listado de checklists
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/supervisor/{idPersonal}")
	public ResponseEntity<List<Checklist>> getBySupervisor(@PathVariable("idPersonal") Integer idPersonal) throws Exception {
		List<Checklist> checklistList = checklistService.getBySupervisor(idPersonal);
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran checklists para el supervisor "+idPersonal+" en la base de datos");
		}
		return new ResponseEntity<List<Checklist>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene los checklists por usuario de la base de datos
	 * @param idUsuario
	 * @return Listado de checklists
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<List<Checklist>> getByUsuarioIngreso(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
		List<Checklist> checklistList = checklistService.getByUsuarioIngreso(idUsuario);
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran checklists para el usuario "+idUsuario+" en la base de datos");
		}
		return new ResponseEntity<List<Checklist>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Obtiene los checklists por tipo de servicio de la base de datos
	 * @param idChecklistServicioTipo
	 * @return Listado de checklists
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('getByParam')")
	@GetMapping("/checklist-servicio-tipo/{idChecklistServicioTipo}")
	public ResponseEntity<List<Checklist>> getByChecklistServicioTipo(@PathVariable("idChecklistServicioTipo") Integer idChecklistServicioTipo) throws Exception {
		List<Checklist> checklistList = checklistService.getByChecklistServicioTipo(idChecklistServicioTipo);
		if(checklistList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran checklists del tipo de servicio "+idChecklistServicioTipo+" en la base de datos");
		}
		return new ResponseEntity<List<Checklist>>(checklistList, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo checklist
	 * No lo guarda cuando encuentra otro checklist con el mismo servicio
	 * @param checklistNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createServicio')")
	@PostMapping("/dto")
	public ResponseEntity<Void> createDTO(@Valid @RequestBody ChecklistChecklistEvaluacionDTO checklistNew) throws Exception {
		Checklist checklist = checklistService.createDTO(checklistNew);
		if(checklist==null) {
			throw new Exception("No se ha podido crear el checklist");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Guarda un nuevo checklist
	 * No lo guarda cuando encuentra otro checklist con el mismo servicio
	 * @param checklistNew
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('createServicio')")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Checklist checklistNew) throws Exception {
		Checklist checklist = checklistService.create(checklistNew);
		if(checklist==null) {
			throw new Exception("No se ha podido crear el checklist");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del checklist buscandolo por su id
	 * @param checklistUp
	 * @return Checklist actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateServicio')")
	@PutMapping("/dto")
	public ResponseEntity<Checklist> updateDTO(@Valid @RequestBody ChecklistChecklistEvaluacionDTO checklistUp) throws Exception {
		Checklist checklist = checklistService.updateDTO(checklistUp);
		return new ResponseEntity<Checklist>(checklist, HttpStatus.CREATED);
	}
	
	/**
	 * Actualiza todos los valores del checklist buscandolo por su id
	 * @param checklistUp
	 * @return Checklist actualizado
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('updateServicio')")
	@PutMapping
	public ResponseEntity<Checklist> update(@Valid @RequestBody Checklist checklistUp) throws Exception {
		Checklist checklist = checklistService.update(checklistUp);
		return new ResponseEntity<Checklist>(checklist, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un checklist de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@PreAuthorize("@authServiceImpl.tieneAcceso('deleteServicio')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Checklist checklist = checklistService.getById(id);
		if(checklist == null) {
			throw new ModelNotFoundException("Checklist con id " + id + " no encontrado");
		}
		checklistService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
