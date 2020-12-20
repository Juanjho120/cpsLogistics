package proteus.pago.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import proteus.credito.model.CreditoProveedorDetalle;

/**
 * Model for Table "pago_proveedor_detalles"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "pago_proveedor_detalles")
public class PagoProveedorDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPagoProveedorDetalle;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_pago_proveedor", nullable = false, foreignKey = @ForeignKey(name = "fkPagoProveedorDetallePagoProveedor"))
	private PagoProveedor pagoProveedor;
	
	@NotNull(message = "El credito detalle de proveedor no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_credito_proveedor_detalle", nullable = false, foreignKey = @ForeignKey(name = "fkPagoProveedorDetalleCreditoProveedorDetalle"))
	private CreditoProveedorDetalle creditoProveedorDetalle;
	
	public PagoProveedorDetalle() {}

	/**
	 * @param idPagoProveedorDetalle
	 * @param pagoProveedor
	 * @param creditoProveedorDetalle
	 */
	public PagoProveedorDetalle(Integer idPagoProveedorDetalle, PagoProveedor pagoProveedor, CreditoProveedorDetalle creditoProveedorDetalle) {
		this.idPagoProveedorDetalle = idPagoProveedorDetalle;
		this.pagoProveedor = pagoProveedor;
		this.creditoProveedorDetalle = creditoProveedorDetalle;
	}

	/**
	 * @return the idPagoProveedorDetalle
	 */
	public Integer getIdPagoProveedorDetalle() {
		return idPagoProveedorDetalle;
	}

	/**
	 * @param idPagoProveedorDetalle the idPagoProveedorDetalle to set
	 */
	public void setIdPagoProveedorDetalle(Integer idPagoProveedorDetalle) {
		this.idPagoProveedorDetalle = idPagoProveedorDetalle;
	}

	/**
	 * @return the pagoProveedor
	 */
	public PagoProveedor getPagoProveedor() {
		return pagoProveedor;
	}

	/**
	 * @param pagoProveedor the pagoProveedor to set
	 */
	public void setPagoProveedor(PagoProveedor pagoProveedor) {
		this.pagoProveedor = pagoProveedor;
	}

	/**
	 * @return the creditoProveedorDetalle
	 */
	public CreditoProveedorDetalle getCreditoProveedorDetalle() {
		return creditoProveedorDetalle;
	}

	/**
	 * @param creditoProveedorDetalle the creditoProveedorDetalle to set
	 */
	public void setCreditoProveedorDetalle(CreditoProveedorDetalle creditoProveedorDetalle) {
		this.creditoProveedorDetalle = creditoProveedorDetalle;
	}
	
}
