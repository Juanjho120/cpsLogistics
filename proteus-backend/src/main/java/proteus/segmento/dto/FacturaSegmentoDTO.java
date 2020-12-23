package proteus.segmento.dto;

public class FacturaSegmentoDTO {

	private Integer idSegmentoCreditoDetalle;
	private String facturaNumero;
	private String fecha;
	private String segmento;
	private String placa;
	private Double total;
	private Boolean pagada;
	private String fechaPago;
	private String tipoPago;
	
	/**
	 * @param idSegmentoCreditoDetalle
	 * @param facturaNumero
	 * @param fecha
	 * @param segmento
	 * @param placa
	 * @param total
	 * @param pagada
	 * @param fechaPago
	 * @param tipoPago
	 */
	public FacturaSegmentoDTO() {
		this.idSegmentoCreditoDetalle = 0;
		this.facturaNumero = "";
		this.fecha = "";
		this.segmento = "";
		this.placa = "";
		this.total = 0.0;
		this.pagada = false;
		this.fechaPago = "";
		this.tipoPago = "";
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
	 * @return the facturaNumero
	 */
	public String getFacturaNumero() {
		return facturaNumero;
	}
	/**
	 * @param facturaNumero the facturaNumero to set
	 */
	public void setFacturaNumero(String facturaNumero) {
		this.facturaNumero = facturaNumero;
	}
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}
	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	/**
	 * @return the pagada
	 */
	public Boolean getPagada() {
		return pagada;
	}
	/**
	 * @param pagada the pagada to set
	 */
	public void setPagada(Boolean pagada) {
		this.pagada = pagada;
	}
	/**
	 * @return the fechaPago
	 */
	public String getFechaPago() {
		return fechaPago;
	}
	/**
	 * @param fechaPago the fechaPago to set
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	/**
	 * @return the tipoPago
	 */
	public String getTipoPago() {
		return tipoPago;
	}
	/**
	 * @param tipoPago the tipoPago to set
	 */
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}
	
}
