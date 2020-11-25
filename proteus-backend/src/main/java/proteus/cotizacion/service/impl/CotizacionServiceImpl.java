package proteus.cotizacion.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.cotizacion.model.Cotizacion;
import proteus.cotizacion.model.CotizacionRepuesto;
import proteus.cotizacion.model.CotizacionTrabajo;
import proteus.cotizacion.repository.ICotizacionRepository;
import proteus.cotizacion.service.ICotizacionRepuestoService;
import proteus.cotizacion.service.ICotizacionService;
import proteus.cotizacion.service.ICotizacionTrabajoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for Cotizacion Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class CotizacionServiceImpl extends CRUDImpl<Cotizacion, Integer> implements ICotizacionService {

	@Autowired
	private ICotizacionRepository cotizacionRepository;
	
	@Autowired
	private ICotizacionTrabajoService cotizacionTrabajoService;
	
	@Autowired
	private ICotizacionRepuestoService cotizacionRepuestoService;

	@Override
	protected IGenericRepository<Cotizacion, Integer> getRepository() {
		return cotizacionRepository;
	}
	
	@Override
	public Cotizacion create(Cotizacion cotizacion) throws Exception {
		Double totalCotizacion = 0.0;
		Double totalRepuesto = 0.0;
		
		//Asignamos la cotizacion a cada trabajo de cotizacion
		//Extraemos el costo de cada trabajo para sumarlo al total de la cotizacion
		for(CotizacionTrabajo cotizacionTrabajo : cotizacion.getCotizacionTrabajo()) {
			cotizacionTrabajo.setCotizacion(cotizacion);
			totalCotizacion += cotizacionTrabajo.getCosto();
		}
		
		//Aisgnamos la cotizacion a cada repuesto de cotizacion
		//Calculamos el costo total de cada detalle y lo sumamos al total de la cotizacion
		for(CotizacionRepuesto cotizacionRepuesto : cotizacion.getCotizacionRepuesto()) {
			cotizacionRepuesto.setCotizacion(cotizacion);
			
			totalRepuesto = cotizacionRepuesto.getCantidad() * cotizacionRepuesto.getCostoUnitario();
			totalCotizacion += totalRepuesto;
			
			cotizacionRepuesto.setCostoTotal(totalRepuesto);
		}
		cotizacion.setTotal(totalCotizacion);
		
		//Establecemos la fecha y hora actual
		cotizacion.setFechaHora(LocalDateTime.now());
		return cotizacionRepository.save(cotizacion);
	}
	
	@Override
	public Cotizacion update(Cotizacion cotizacion) throws Exception {
		//Eliminamos los trabajos antiguos
		List<CotizacionTrabajo> cotizacionTrabajoList = cotizacionTrabajoService.getByCotizacion(cotizacion.getIdCotizacion());
		if(!cotizacionTrabajoList.isEmpty()) {
			cotizacionTrabajoService.deleteByCotizacion(cotizacion.getIdCotizacion());
		}
		//Eliminamos los repuestos antiguos
		List<CotizacionRepuesto> cotizacionRepuestoList = cotizacionRepuestoService.getByCotizacion(cotizacion.getIdCotizacion());
		if(!cotizacionRepuestoList.isEmpty()) {
			cotizacionRepuestoService.deleteByCotizacion(cotizacion.getIdCotizacion());
		}
		//Creamos todo de nuevo
		return this.create(cotizacion);
	}
	
	@Override
	public void delete(Integer idCotizacion) throws Exception {
		//Eliminamos los trabajos de la cotizacion
		List<CotizacionTrabajo> cotizacionTrabajoList = cotizacionTrabajoService.getByCotizacion(idCotizacion);
		if(!cotizacionTrabajoList.isEmpty()) {
			cotizacionTrabajoService.deleteByCotizacion(idCotizacion);
		}
		//Eliminamos los repuestos de la cotizacion
		List<CotizacionRepuesto> cotizacionRepuestoList = cotizacionRepuestoService.getByCotizacion(idCotizacion);
		if(!cotizacionRepuestoList.isEmpty()) {
			cotizacionRepuestoService.deleteByCotizacion(idCotizacion);
		}
		//Eliminamos la cotizacion
		cotizacionRepository.deleteById(idCotizacion);
	}

	@Override
	public List<Cotizacion> getBySegmento(Integer idSegmento) throws Exception {
		return cotizacionRepository.findBySegmento(idSegmento);
	}

	@Override
	public List<Cotizacion> getByUsuario(Integer idUsuario) throws Exception {
		return cotizacionRepository.findByUsuario(idUsuario);
	}

	@Override
	public List<Cotizacion> getByFecha(String fechaDesde, String fechaHasta) {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return cotizacionRepository.findByFecha(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}
	
}