package proteus.segmento.model;

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

import proteus.pago.model.PagoTipo;

/**
 * Model for Table "segmento_pagos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "segmento_pagos")
public class SegmentoPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSegmentoPago;
	
	@NotNull(message = "El tipo de pago no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_pago_tipo", nullable = false, foreignKey = @ForeignKey(name = "fkSegmentoPagoPagoTipo"))
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
	
	@OneToMany(mappedBy = "segmentoPago", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<SegmentoPagoDetalle> segmentoPagoDetalle;
	
	public SegmentoPago() {}

	/**
	 * @param idSegmentoPago
	 * @param pagoTipo
	 * @param idItem
	 * @param fechaHoraPago
	 * @param monto
	 */
	public SegmentoPago(Integer idSegmentoPago, PagoTipo pagoTipo,
			Integer idItem, LocalDateTime fechaHoraPago, Double monto) {
		this.idSegmentoPago = idSegmentoPago;
		this.pagoTipo = pagoTipo;
		this.idItem = idItem;
		this.fechaHoraPago = fechaHoraPago;
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
	 * @return the segmentoPagoDetalle
	 */
	public List<SegmentoPagoDetalle> getSegmentoPagoDetalle() {
		return segmentoPagoDetalle;
	}

	/**
	 * @param segmentoPagoDetalle the segmentoPagoDetalle to set
	 */
	public void setSegmentoPagoDetalle(List<SegmentoPagoDetalle> segmentoPagoDetalle) {
		this.segmentoPagoDetalle = segmentoPagoDetalle;
	}
	
}
