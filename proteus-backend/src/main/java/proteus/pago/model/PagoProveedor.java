package proteus.pago.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

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
	
	@NotNull(message = "El tipo de pago no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_pago_tipo", nullable = false, foreignKey = @ForeignKey(name = "fkPagoProveedorPagoTipo"))
	private PagoTipo pagoTipo;
	
	@Column(name = "id_item")
	private Integer idItem;
	
	@PastOrPresent(message = "La fecha y hora de pago debe estar en tiempo pasado o presente")
	@Column(name = "fecha_pago", nullable = false)
	private LocalDateTime fechaHoraPago;
	
	@NotNull(message = "El monto del pago no puede ser nulo")
	@PositiveOrZero(message = "El monto del pago debe ser positivo o cero")
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@OneToMany(mappedBy = "pagoProveedor", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<PagoProveedorDetalle> pagoProveedorDetalle;
	
	public PagoProveedor() {}

	/**
	 * @param idPagoProveedor
	 * @param pagoTipo
	 * @param idItem
	 * @param fechaHoraPago
	 * @param monto
	 * @param observaciones
	 * @param pagoProveedorDetalle
	 */
	public PagoProveedor(Integer idPagoProveedor, PagoTipo pagoTipo, Integer idItem, LocalDateTime fechaHoraPago, Double monto,
			String observaciones, List<PagoProveedorDetalle> pagoProveedorDetalle) {
		this.idPagoProveedor = idPagoProveedor;
		this.pagoTipo = pagoTipo;
		this.idItem = idItem;
		this.fechaHoraPago = fechaHoraPago;
		this.monto = monto;
		this.observaciones = observaciones;
		this.pagoProveedorDetalle = pagoProveedorDetalle;
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
	 * @return the pagoTipo
	 */
	public PagoTipo getPagoTipo() {
		return pagoTipo;
	}

	/**
	 * @param pagoTipo the pagoTipo to set
	 */
	public void setPagoTipo(PagoTipo pagoTipo) {
		this.pagoTipo = pagoTipo;
	}

	/**
	 * @return the idItem
	 */
	public Integer getIdItem() {
		return idItem;
	}

	/**
	 * @param idItem the idItem to set
	 */
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
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

	/**
	 * @return the pagoProveedorDetalle
	 */
	public List<PagoProveedorDetalle> getPagoProveedorDetalle() {
		return pagoProveedorDetalle;
	}

	/**
	 * @param pagoProveedorDetalle the pagoProveedorDetalle to set
	 */
	public void setPagoProveedorDetalle(List<PagoProveedorDetalle> pagoProveedorDetalle) {
		this.pagoProveedorDetalle = pagoProveedorDetalle;
	}

}
