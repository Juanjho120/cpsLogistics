package proteus.servicio.dto;

import java.time.LocalDateTime;

/**
 * DTO para la busqueda de servicios
 * 
 * @author Juan Tzun
 */
public class ServicioBusquedaDTO {

	private Integer idSegmento;
	private Integer idPlaca;
	private Integer idServicioTipo;
	private Boolean finalizado;
	private Boolean facturado;
	private LocalDateTime fechaDesde;
	private LocalDateTime fechaHasta;
	
	public ServicioBusquedaDTO() {}

	/**
	 * @return the idSegmento
	 */
	public Integer getIdSegmento() {
		return idSegmento;
	}

	/**
	 * @param idSegmento the idSegmento to set
	 */
	public void setIdSegmento(Integer idSegmento) {
		this.idSegmento = idSegmento;
	}

	/**
	 * @return the idPlaca
	 */
	public Integer getIdPlaca() {
		return idPlaca;
	}

	/**
	 * @param idPlaca the idPlaca to set
	 */
	public void setIdPlaca(Integer idPlaca) {
		this.idPlaca = idPlaca;
	}

	/**
	 * @return the idServicioTipo
	 */
	public Integer getIdServicioTipo() {
		return idServicioTipo;
	}

	/**
	 * @param idServicioTipo the idServicioTipo to set
	 */
	public void setIdServicioTipo(Integer idServicioTipo) {
		this.idServicioTipo = idServicioTipo;
	}

	/**
	 * @return the finalizado
	 */
	public Boolean getFinalizado() {
		return finalizado;
	}

	/**
	 * @param finalizado the finalizado to set
	 */
	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	/**
	 * @return the facturado
	 */
	public Boolean getFacturado() {
		return facturado;
	}

	/**
	 * @param facturado the facturado to set
	 */
	public void setFacturado(Boolean facturado) {
		this.facturado = facturado;
	}

	/**
	 * @return the fechaDesde
	 */
	public LocalDateTime getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * @param fechaDesde the fechaDesde to set
	 */
	public void setFechaDesde(LocalDateTime fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return the fechaHasta
	 */
	public LocalDateTime getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta the fechaHasta to set
	 */
	public void setFechaHasta(LocalDateTime fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
}
