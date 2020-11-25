package proteus.servicio.service;

import java.time.LocalDateTime;
import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.servicio.model.Servicio;

/**
 * Services for Servicio Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IServicioService extends ICRUD<Servicio, Integer> {

	List<Servicio> getByPlaca(Integer idPlaca) throws Exception;
	List<Servicio> getByFinalizado(Boolean finalizado) throws Exception;
	List<Servicio> getByFacturado(Boolean facturado) throws Exception;
	List<Servicio> getBySegmento(Integer idSegmento) throws Exception;
	List<Servicio> getByFechaRango(LocalDateTime fechaDesde, LocalDateTime fechaHasta) throws Exception;
	List<Servicio> getByPlacaAndFinalizado(Integer idPlaca, Boolean finalizado) throws Exception;
	List<Servicio> getByPlacaAndFacturado(Integer idPlaca, Boolean facturado) throws Exception;
	List<Servicio> getByPlacaAndFinalizadoAndFacturado(Integer idPlaca, Boolean finalizado, Boolean facturado) throws Exception;
	List<Servicio> getBySegmentoAndFinalizado(Integer idSegmento, Boolean finalizado) throws Exception;
	List<Servicio> getBySegmentoAndFacturado(Integer idSegmento, Boolean facturado) throws Exception;
	List<Servicio> getBySegmentoAndFinalizadoAndFacturado(Integer idSegmento, Boolean finalizado, Boolean facturado) throws Exception;
	List<Servicio> getBySegmentoAndPlaca(Integer idSegmento, Integer idPlaca) throws Exception;
	List<Servicio> getByServicioTipo(Integer idServicioTipo) throws Exception;
	List<Servicio> getByCotizacion(Integer idCotizacion) throws Exception;
	void updateFinalizado(Integer idServicio, Boolean finalizado) throws Exception;
	void updateFacturado(Integer idServicio, Boolean facturado) throws Exception;
	
}
