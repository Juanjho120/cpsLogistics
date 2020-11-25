package proteus.credito.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import proteus.factura.model.FacturaCompra;

/**
 * Model for Table "credito_proveedor_detalles"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "credito_proveedor_detalles")
public class CreditoProveedorDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCreditoProveedorDetalle;
	
	@ManyToOne
	@JoinColumn(name = "id_credito_proveedor", nullable = false, foreignKey = @ForeignKey(name = "fkCreditoProveedorDetalleCreditoProveedor"))
	private CreditoProveedor creditoProveedor;
	
	@NotNull(message = "La factura de compra no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_factura_compra", nullable = false, foreignKey = @ForeignKey(name = "fkCreditoProveedorDetalleFacturaCompra"))
	private FacturaCompra facturaCompra;
	
	@NotNull(message = "La descripcion del detalle del credito proveedor no puede ser nulo")
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@Column(name = "vencida")
	private Boolean vencida;
	
	public CreditoProveedorDetalle() {}

	/**
	 * @param idCreditoProveedorDetalle
	 * @param creditoProveedor
	 * @param facturaCompra
	 * @param descripcion
	 * @param vencida
	 */
	public CreditoProveedorDetalle(Integer idCreditoProveedorDetalle, CreditoProveedor creditoProveedor, FacturaCompra facturaCompra, String descripcion,
			String observaciones, Boolean vencida) {
		this.idCreditoProveedorDetalle = idCreditoProveedorDetalle;
		this.creditoProveedor = creditoProveedor;
		this.facturaCompra = facturaCompra;
		this.descripcion = descripcion;
		this.observaciones = observaciones;
		this.vencida = vencida;
	}

	/**
	 * @return the idCreditoProveedorDetalle
	 */
	public Integer getIdCreditoProveedorDetalle() {
		return idCreditoProveedorDetalle;
	}

	/**
	 * @param idCreditoProveedorDetalle the idCreditoProveedorDetalle to set
	 */
	public void setIdCreditoProveedorDetalle(Integer idCreditoProveedorDetalle) {
		this.idCreditoProveedorDetalle = idCreditoProveedorDetalle;
	}

	/**
	 * @return the creditoProveedor
	 */
	public CreditoProveedor getCreditoProveedor() {
		return creditoProveedor;
	}

	/**
	 * @param creditoProveedor the creditoProveedor to set
	 */
	public void setCreditoProveedor(CreditoProveedor creditoProveedor) {
		this.creditoProveedor = creditoProveedor;
	}

	/**
	 * @return the facturaCompra
	 */
	public FacturaCompra getFacturaCompra() {
		return facturaCompra;
	}

	/**
	 * @param facturaCompra the facturaCompra to set
	 */
	public void setFacturaCompra(FacturaCompra facturaCompra) {
		this.facturaCompra = facturaCompra;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
}
