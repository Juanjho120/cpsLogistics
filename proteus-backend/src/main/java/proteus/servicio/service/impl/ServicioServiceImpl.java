package proteus.servicio.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.repuesto.service.IRepuestoService;
import proteus.servicio.model.Servicio;
import proteus.servicio.model.ServicioRepuesto;
import proteus.servicio.model.ServicioTrabajo;
import proteus.servicio.repository.IServicioRepository;
import proteus.servicio.service.IServicioRepuestoService;
import proteus.servicio.service.IServicioService;
import proteus.servicio.service.IServicioTrabajoService;

/**
 * Services for Servicio Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ServicioServiceImpl extends CRUDImpl<Servicio, Integer> implements IServicioService {

	@Autowired
	private IServicioRepository servicioRepository;
	
	@Autowired
	private IServicioTrabajoService servicioTrabajoService;
	
	@Autowired
	private IServicioRepuestoService servicioRepuestoService;
	
	@Autowired
	private IRepuestoService repuestoService;

	@Override
	protected IGenericRepository<Servicio, Integer> getRepository() {
		return servicioRepository;
	}
	
	@Override
	public Servicio create(Servicio servicio) throws Exception {
		Double totalServicio = 0.0;
		Double totalRepuesto = 0.0;
		
		List<Servicio> servicioAuxList = this.getByPlacaAndFinalizado(servicio.getPlaca().getIdPlaca(), false);
		if(servicioAuxList.isEmpty()) {
			//Asignando el id de servicio a cada trabajo
			for(ServicioTrabajo servicioTrabajo : servicio.getServicioTrabajo()) {
				servicioTrabajo.setServicio(servicio);
				//Sumando el costo del trabajo al costo total del servicio
				totalServicio += servicioTrabajo.getCosto();
			}
			
			//Verificando la existencia de repuesto solicitado
			List<String> errorStockList = new ArrayList<>();
			for(ServicioRepuesto servicioRepuesto : servicio.getServicioRepuesto()) {
				String errorStock = repuestoService.verStock(servicioRepuesto.getRepuesto().getIdRepuesto(), 
						servicioRepuesto.getCantidad(), "ASIGNACIÓN DE SERVICIO");
				if(!errorStock.equalsIgnoreCase("")) {
					errorStockList.add(errorStock);
				}
			}
			
			//Si la lista de mensaje viene vacia entonces si hay existencia para todos los repuestos
			if(errorStockList.isEmpty()) {
				//Asignando el servicio a cada repuesto
				for(ServicioRepuesto servicioRepuesto : servicio.getServicioRepuesto()) {
					servicioRepuesto.setServicio(servicio);
					//Calculando el total de repuesto y sumando este total al costo total del servicio
					totalRepuesto = servicioRepuesto.getCantidad() * servicioRepuesto.getCostoUnitario();
					servicioRepuesto.setCostoTotal(totalRepuesto);
					totalServicio += totalRepuesto;
					
					//Restando la existencia del repuesto
					repuestoService.actualizarCantidad(servicioRepuesto.getRepuesto().getIdRepuesto(), servicioRepuesto.getCantidad(), false);
				}
				
				servicio.setCostoTotal(totalServicio);
				servicio.setFacturado(false);		//Los servicios nuevos no estan facturados
				servicio.setFinalizado(false);		//Los servicios nuevos no estan finalizados
				servicio.setFechaHora(LocalDateTime.now());
				return servicioRepository.save(servicio);
			} else {
				throw new Exception(errorStockList.toString());
			}
		}
		return null;
	}
	
	@Override
	public Servicio update(Servicio servicio) throws Exception {
		Double totalServicio = 0.0;
		Double totalRepuesto = 0.0;
		
		//No es posible actualizar un servicio que ya esta finalizado
		Servicio servicioAux = this.getById(servicio.getIdServicio());
		if(!servicioAux.getFinalizado()) {
			//SECCION DE BORRADO
			//Verificando si existen trabajos asignados al servicio
			List<ServicioTrabajo> servicioTrabajoList = servicioTrabajoService.getByServicio(servicio.getIdServicio());
			if(!servicioTrabajoList.isEmpty()) {
				//Borrando todos los trabajos pertenecientes al servicio
				servicioTrabajoService.deleteByServicio(servicio.getIdServicio());
			}
			
			//Verificando si existen repuestos asignados al servicio
			//Si es que existen entonces se devuelven todos los repuestos al cardex
			List<ServicioRepuesto> servicioRepuestoList = servicioRepuestoService.getByServicio(servicio.getIdServicio());
			for(ServicioRepuesto servicioRepuesto : servicioRepuestoList) {
				//Sumando la existencia del repuesto
				repuestoService.actualizarCantidad(servicioRepuesto.getRepuesto().getIdRepuesto(), servicioRepuesto.getCantidad(), true);
			}
			
			if(!servicioRepuestoList.isEmpty()) {
				//Borrando todos los repuestos pertenecientes al servicio
				servicioRepuestoService.deleteByServicio(servicio.getIdServicio());
			}
			
			//SECCION DE INSERCION
			//Asignando el id de servicio a cada trabajo
			for(ServicioTrabajo servicioTrabajo : servicio.getServicioTrabajo()) {
				servicioTrabajo.setServicio(servicio);
				//Sumando el costo del trabajo al costo total del servicio
				totalServicio += servicioTrabajo.getCosto();
			}
			
			//Verificando la existencia de repuesto solicitado
			List<String> errorStockList = new ArrayList<>();
			for(ServicioRepuesto servicioRepuesto : servicio.getServicioRepuesto()) {
				String errorStock = repuestoService.verStock(servicioRepuesto.getRepuesto().getIdRepuesto(), 
						servicioRepuesto.getCantidad(), "ASIGNACIÓN DE SERVICIO");
				if(!errorStock.equalsIgnoreCase("")) {
					errorStockList.add(errorStock);
				}
			}
			
			//Si la lista de mensaje viene vacia entonces si hay existencia para todos los repuestos
			if(errorStockList.isEmpty()) {
				//Asignando el servicio a cada repuesto
				for(ServicioRepuesto servicioRepuesto : servicio.getServicioRepuesto()) {
					servicioRepuesto.setServicio(servicio);
					//Calculando el total de repuesto y sumando este total al costo total del servicio
					totalRepuesto = servicioRepuesto.getCantidad() * servicioRepuesto.getCostoUnitario();
					servicioRepuesto.setCostoTotal(totalRepuesto);
					totalServicio += totalRepuesto;
					
					//Restando la existencia del repuesto
					repuestoService.actualizarCantidad(servicioRepuesto.getRepuesto().getIdRepuesto(), servicioRepuesto.getCantidad(), false);
				}
				servicio.setCostoTotal(totalServicio);
				
				//Se recuperan los valores anteriores porque no pueden ser actualizados por este medio
				servicio.setFacturado(servicioAux.getFacturado());
				servicio.setFinalizado(servicioAux.getFinalizado());
				servicio.setFechaHora(servicioAux.getFechaHora()); //La fecha de creacion nunca cambia
				return servicioRepository.save(servicio);
			}
		}
		
		return null;
	}

	@Override
	public List<Servicio> getByPlaca(Integer idPlaca) throws Exception {
		return servicioRepository.findByPlaca(idPlaca);
	}

	@Override
	public List<Servicio> getByPlacaAndFinalizado(Integer idPlaca, Boolean finalizado) throws Exception {
		return servicioRepository.findByPlacaAndFinalizado(idPlaca, finalizado);
	}

	@Override
	public void updateFinalizado(Integer idServicio, Boolean finalizado) throws Exception {
		Servicio servicio = this.getById(idServicio);
		servicio.setFinalizado(finalizado);
		servicioRepository.save(servicio);
	}
	
	@Override
	public void delete(Integer idServicio) throws Exception {
		Servicio servicio = this.getById(idServicio);
		//No puede eliminarse si ya tiene factura
		if(!servicio.getFacturado()) {
			//Verificando si existen trabajos asignados al servicio
			List<ServicioTrabajo> servicioTrabajoList = servicioTrabajoService.getByServicio(idServicio);
			if(!servicioTrabajoList.isEmpty()) {
				//Borrando todos los trabajos pertenecientes al servicio
				servicioTrabajoService.deleteByServicio(idServicio);
			}
			
			//Verificando si existen repuestos asignados al servicio
			List<ServicioRepuesto> servicioRepuestoList = servicioRepuestoService.getByServicio(idServicio);
			//Si es que existen entonces se devuelven todos los repuestos al cardex
			for(ServicioRepuesto servicioRepuesto : servicioRepuestoList) {
				//Sumando la existencia del repuesto
				repuestoService.actualizarCantidad(servicioRepuesto.getRepuesto().getIdRepuesto(), servicioRepuesto.getCantidad(), true);
			}
			
			if(!servicioRepuestoList.isEmpty()) {
				//Borrando todos los repuestos pertenecientes al servicio
				servicioRepuestoService.deleteByServicio(idServicio);
			}
			
			servicioRepository.deleteById(idServicio);
		} else if(servicio.getFacturado()) {
			throw new Exception("No es posible eliminar un servicio facturado");
		}
	}

	@Override
	public List<Servicio> getByPlacaAndFacturado(Integer idPlaca, Boolean facturado) throws Exception {
		return servicioRepository.findByPlacaAndFacturado(idPlaca, facturado);
	}

	@Override
	public List<Servicio> getByFinalizado(Boolean finalizado) throws Exception {
		return servicioRepository.findByFinalizado(finalizado);
	}

	@Override
	public List<Servicio> getByFacturado(Boolean facturado) throws Exception {
		return servicioRepository.findByFacturado(facturado);
	}

	@Override
	public List<Servicio> getBySegmento(Integer idSegmento) throws Exception {
		return servicioRepository.findBySegmento(idSegmento);
	}

	@Override
	public List<Servicio> getByFechaRango(LocalDateTime fechaDesde, LocalDateTime fechaHasta) throws Exception {
		return servicioRepository.findByFechaRango(fechaDesde, fechaHasta.plusDays(1));
	}

	@Override
	public List<Servicio> getBySegmentoAndFinalizado(Integer idSegmento, Boolean finalizado) throws Exception {
		return servicioRepository.findBySegmentoAndFinalizado(idSegmento, finalizado);
	}

	@Override
	public List<Servicio> getBySegmentoAndFacturado(Integer idSegmento, Boolean facturado) throws Exception {
		return servicioRepository.findBySegmentoAndFacturado(idSegmento, facturado);
	}

	@Override
	public List<Servicio> getBySegmentoAndPlaca(Integer idSegmento, Integer idPlaca) throws Exception {
		return servicioRepository.findBySegmentoAndPlaca(idSegmento, idPlaca);
	}

	@Override
	public List<Servicio> getByPlacaAndFinalizadoAndFacturado(Integer idPlaca, Boolean finalizado, Boolean facturado)
			throws Exception {
		return servicioRepository.findByPlacaAndFinalizadoAndFacturado(idPlaca, finalizado, facturado);
	}

	@Override
	public List<Servicio> getBySegmentoAndFinalizadoAndFacturado(Integer idSegmento, Boolean finalizado,
			Boolean facturado) throws Exception {
		return servicioRepository.findBySegmentoAndFinalizadoAndFacturado(idSegmento, finalizado, facturado);
	}

	@Override
	public List<Servicio> getByServicioTipo(Integer idServicioTipo) throws Exception {
		return servicioRepository.findByServicioTipo(idServicioTipo);
	}

	@Override
	public List<Servicio> getByCotizacion(Integer idCotizacion) throws Exception {
		return servicioRepository.findByCotizacion(idCotizacion);
	}

	@Override
	public void updateFacturado(Integer idServicio, Boolean facturado) throws Exception {
		Servicio servicio = this.getById(idServicio);
		servicio.setFacturado(facturado);
		servicioRepository.save(servicio);
	}
	
}