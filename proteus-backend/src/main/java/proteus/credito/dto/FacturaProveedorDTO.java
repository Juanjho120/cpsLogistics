package proteus.credito.dto;

public class FacturaProveedorDTO {

	private Integer idFacturaCompra;
	private String facturaNumero;
	private String fecha;
	private String proveedor;
	private Double total;
	private Boolean vencida;
	private Boolean pagada;
	private String fechaPago;
	private String tipoPago;
	/**
	 * @param idFacturaCompra
	 * @param facturaNumero
	 * @param fecha
	 * @param proveedor
	 * @param total
	 * @param vencida
	 * @param pagada
	 * @param fechaPago
	 * @param tipoPago
	 */
	public FacturaProveedorDTO() {
		this.idFacturaCompra = 0;
		this.facturaNumero = "";
		this.fecha = "";
		this.proveedor = "";
		this.total = 0.0;
		this.vencida = false;
		this.pagada = false;
		this.fechaPago = "";
		this.tipoPago = "";
	}
	/**
	 * @return the idFacturaCompra
	 */
	public Integer getIdFacturaCompra() {
		return idFacturaCompra;
	}
	/**
	 * @param idFacturaCompra the idFacturaCompra to set
	 */
	public void setIdFacturaCompra(Integer idFacturaCompra) {
		this.idFacturaCompra = idFacturaCompra;
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
	 * @return the proveedor
	 */
	public String getProveedor() {
		return proveedor;
	}
	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
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
	 * @return the vencida
	 */
	public Boolean getVencida() {
		return vencida;
	}
	/**
	 * @param vencida the vencida to set
	 */
	public void setVencida(Boolean vencida) {
		this.vencida = vencida;
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
