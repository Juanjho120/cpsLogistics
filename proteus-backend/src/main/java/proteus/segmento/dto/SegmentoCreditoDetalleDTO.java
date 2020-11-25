package proteus.segmento.dto;

/**
 * DTO para devolver los totales con formato Q y decimales
 * 
 * @author Juan Tzun
 */
public class SegmentoCreditoDetalleDTO {

	private Integer idSegmentoCreditoDetalle;
	private Integer idServicio;
	private String fechaEmision;
	private String placaNumero;
	private String totalFacturado;
	private String totalPagado;
	private String totalRestante;
	
	public SegmentoCreditoDetalleDTO() {}

	/**
	 * @param idSegmentoCreditoDetalle
	 * @param idServicio
	 * @param placaNumero
	 * @param totalFacturado
	 * @param totalPagado
	 * @param totalRestante
	 */
	public SegmentoCreditoDetalleDTO(Integer idSegmentoCreditoDetalle, Integer idServicio, String fechaEmision, String placaNumero,
			String totalFacturado, String totalPagado, String totalRestante) {
		this.idSegmentoCreditoDetalle = idSegmentoCreditoDetalle;
		this.idServicio = idServicio;
		this.fechaEmision = fechaEmision;
		this.placaNumero = placaNumero;
		this.totalFacturado = totalFacturado;
		this.totalPagado = totalPagado;
		this.totalRestante = totalRestante;
	}

	/**
	 * @return the idSegmentoCreditoDetalle
	 */
	public Integer getIdSegmentoCreditoDetalle() {
		return idSegmentoCreditoDetalle;
	}

	/**
	 * @param idSegmentoCreditoDetalle the idSegmentoCreditoDetalle to set
	 */
	public void setIdSegmentoCreditoDetalle(Integer idSegmentoCreditoDetalle) {
		this.idSegmentoCreditoDetalle = idSegmentoCreditoDetalle;
	}

	/**
	 * @return the idServicio
	 */
	public Integer getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the placaNumero
	 */
	public String getPlacaNumero() {
		return placaNumero;
	}

	/**
	 * @param placaNumero the placaNumero to set
	 */
	public void setPlacaNumero(String placaNumero) {
		this.placaNumero = placaNumero;
	}

	/**
	 * @return the totalFacturado
	 */
	public String getTotalFacturado() {
		return totalFacturado;
	}

	/**
	 * @param totalFacturado the totalFacturado to set
	 */
	public void setTotalFacturado(String totalFacturado) {
		this.totalFacturado = totalFacturado;
	}

	/**
	 * @return the totalPagado
	 */
	public String getTotalPagado() {
		return totalPagado;
	}

	/**
	 * @param totalPagado the totalPagado to set
	 */
	public void setTotalPagado(String totalPagado) {
		this.totalPagado = totalPagado;
	}

	/**
	 * @return the totalRestante
	 */
	public String getTotalRestante() {
		return totalRestante;
	}

	/**
	 * @param totalRestante the totalRestante to set
	 */
	public void setTotalRestante(String totalRestante) {
		this.totalRestante = totalRestante;
	}
	
}
