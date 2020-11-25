package proteus.pago.model;

import java.time.LocalDateTime;

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
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import proteus.credito.model.CreditoProveedor;

/**
 * Model for Table "pagos_proveedores"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "pagos_proveedores")
public class PagoProveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPagoProveedor;
	
	@NotNull(message = "El credito proveedor no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_credito_proveedor", nullable = false, foreignKey = @ForeignKey(name = "fkPagoProveedorCreditoProveedor"))
	private CreditoProveedor creditoProveedor;
	
	@NotNull(message = "El numero de documento del pago no puede ser nulo")
	@Column(name = "no_documento", nullable = false)
	private String noDocumento;
	
	@NotNull(message = "El monto del pago no puede ser nulo")
	@PositiveOrZero(message = "El monto del pago debe ser positivo o cero")
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	@PastOrPresent(message = "La fecha y hora de pago debe estar en tiempo pasado o presente")
	@Column(name = "fecha_pago", nullable = false)
	private LocalDateTime fechaHoraPago;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	public PagoProveedor() {}

	/**
	 * @param idPagoProveedor
	 * @param creditoProveedor
	 * @param noDocumento
	 * @param monto
	 * @param fechaHoraPago
	 * @param observaciones
	 */
	public PagoProveedor(Integer idPagoProveedor, CreditoProveedor creditoProveedor, String noDocumento, Double monto, LocalDateTime fechaHoraPago, String observaciones) {
		this.idPagoProveedor = idPagoProveedor;
		this.creditoProveedor = creditoProveedor;
		this.noDocumento = noDocumento;
		this.monto = monto;
		this.fechaHoraPago = fechaHoraPago;
		this.observaciones = observaciones;
	}

	/**
	 * @return the idPagoProveedor
	 */
	public Integer getIdPagoProveedor() {
		return idPagoProveedor;
	}

	/**
	 * @param idPagoProveedor the idPagoProveedor to set
	 */
	public void setIdPagoProveedor(Integer idPagoProveedor) {
		this.idPagoProveedor = idPagoProveedor;
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
	 * @return the noDocumento
	 */
	public String getNoDocumento() {
		return noDocumento;
	}

	/**
	 * @param noDocumento the noDocumento to set
	 */
	public void setNoDocumento(String noDocumento) {
		this.noDocumento = noDocumento;
	}

	/**
	 * @return the monto
	 */
	public Double getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	/**
	 * @return the fechaHoraPago
	 */
	public LocalDateTime getFechaHoraPago() {
		return fechaHoraPago;
	}

	/**
	 * @param fechaHoraPago the fechaHoraPago to set
	 */
	public void setFechaHoraPago(LocalDateTime fechaHoraPago) {
		this.fechaHoraPago = fechaHoraPago;
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
