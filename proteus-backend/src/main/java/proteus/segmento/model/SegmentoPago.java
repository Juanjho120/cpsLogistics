package proteus.segmento.model;

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
	
	@NotNull(message = "El segmento credito detalle no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_segmento_credito_detalle", nullable = false, foreignKey = @ForeignKey(name = "fkSegmentoPagoSegmentoCreditoDetalle"))
	private SegmentoCreditoDetalle segmentoCreditoDetalle;
	
	@PastOrPresent(message = "La fecha y hora de pago debe estar en tiempo pasado o presente")
	@Column(name = "fecha_pago", nullable = false)
	private LocalDateTime fechaHoraPago;
	
	@NotNull(message = "El monto del pago no puede ser nulo")
	@PositiveOrZero(message = "El monto del pago debe ser positivo o cero")
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	public SegmentoPago() {}

	/**
	 * @param idSegmentoPago
	 * @param segmentoCreditoDetalle
	 * @param fechaHoraPago
	 * @param monto
	 */
	public SegmentoPago(Integer idSegmentoPago, SegmentoCreditoDetalle segmentoCreditoDetalle, LocalDateTime fechaHoraPago, Double monto) {
		this.idSegmentoPago = idSegmentoPago;
		this.segmentoCreditoDetalle = segmentoCreditoDetalle;
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
	 * @return the segmentoCreditoDetalle
	 */
	public SegmentoCreditoDetalle getSegmentoCreditoDetalle() {
		return segmentoCreditoDetalle;
	}

	/**
	 * @param segmentoCreditoDetalle the segmentoCreditoDetalle to set
	 */
	public void setSegmentoCreditoDetalle(SegmentoCreditoDetalle segmentoCreditoDetalle) {
		this.segmentoCreditoDetalle = segmentoCreditoDetalle;
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
	
}
