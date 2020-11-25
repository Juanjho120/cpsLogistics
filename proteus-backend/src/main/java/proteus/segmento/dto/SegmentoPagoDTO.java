package proteus.segmento.dto;

/**
 * DTO para devolver los pagos de segmentos con formato de Q y fecha
 * 
 * @author Juan Tzun
 */
public class SegmentoPagoDTO {

	private Integer idSegmentoPago;
	private Integer idServicio;
	private String fechaPago;
	private String placaNumero;
	private String monto;
	
	public SegmentoPagoDTO() {}

	/**
	 * @param idSegmentoPago
	 * @param idServicio
	 * @param fechaPago
	 * @param placaNumero
	 * @param monto
	 */
	public SegmentoPagoDTO(Integer idSegmentoPago, Integer idServicio, String fechaPago, String placaNumero,
			String monto) {
		this.idSegmentoPago = idSegmentoPago;
		this.idServicio = idServicio;
		this.fechaPago = fechaPago;
		this.placaNumero = placaNumero;
		this.monto = monto;
	}

	/**
	 * @return the idSegmentoPago
	 */
	public Integer getIdSegmentoPago() {
		return idSegmentoPago;
	}

	/**
	 * @param idSegmentoPago the idSegmentoPago to set
	 */
	public void setIdSegmentoPago(Integer idSegmentoPago) {
		this.idSegmentoPago = idSegmentoPago;
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
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}
	
}
