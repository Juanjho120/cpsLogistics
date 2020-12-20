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

import proteus.servicio.model.Servicio;

/**
 * Model for Table "segmento_credito_detalles"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "segmento_credito_detalles")
public class SegmentoCreditoDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSegmentoCreditoDetalle;
	
	@ManyToOne
	@JoinColumn(name = "id_segmento_credito", nullable = false, foreignKey = @ForeignKey(name = "fkSegmentoCreditoDetalleSegmentoCredito"))
	private SegmentoCredito segmentoCredito;
	
	@NotNull(message = "El numero de factura del credito detalle del segmento no puede ser nulo")
	@Column(name = "factura_numero", nullable = false)
	private String facturaNumero;
	
	@NotNull(message = "El servicio no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_servicio", nullable = false, foreignKey = @ForeignKey(name = "fkSegmentoCreditoDetalleServicio"))
	private Servicio servicio;
	
	@PastOrPresent(message = "La fecha y hora de emision del credito debe estar en tiempo pasado o presente")
	@Column(name = "fecha_emision", nullable = false)
	private LocalDateTime fechaHoraEmision;
	
	@PositiveOrZero(message = "El total facturado debe ser positivo o cero")
	@Column(name = "total_facturado", nullable = false)
	private Double totalFacturado;
	
	@PositiveOrZero(message = "El total restante debe ser positivo o cero")
	@Column(name = "total_restante", nullable = false)
	private Double totalRestante;
	
	@PositiveOrZero(message = "El total pagado debe ser positivo o cero")
	@Column(name = "total_pagado", nullable = false)
	private Double totalPagado;

	public SegmentoCreditoDetalle() {}

	/**
	 * @param idSegmentoCreditoDetalle
	 * @param segmentoCredito
	 * @param facturaNumero
	 * @param servicio
	 * @param fechaHoraEmision
	 * @param totalFacturado
	 * @param totalRestante
	 * @param totalPagado
	 */
	public SegmentoCreditoDetalle(Integer idSegmentoCreditoDetalle, SegmentoCredito segmentoCredito, Servicio servicio, LocalDateTime fechaHoraEmision, Double totalFacturado, 
			Double totalRestante, Double totalPagado, String facturaNumero) {
		this.idSegmentoCreditoDetalle = idSegmentoCreditoDetalle;
		this.segmentoCredito = segmentoCredito;
		this.facturaNumero = facturaNumero;
		this.servicio = servicio;
		this.fechaHoraEmision = fechaHoraEmision;
		this.totalFacturado = totalFacturado;
		this.totalRestante = totalRestante;
		this.totalPagado = totalPagado;
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
	 * @return the segmentoCredito
	 */
	public SegmentoCredito getSegmentoCredito() {
		return segmentoCredito;
	}

	/**
	 * @param segmentoCredito the segmentoCredito to set
	 */
	public void setSegmentoCredito(SegmentoCredito segmentoCredito) {
		this.segmentoCredito = segmentoCredito;
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
	 * @return the servicio
	 */
	public Servicio getServicio() {
		return servicio;
	}

	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return the fechaHoraEmision
	 */
	public LocalDateTime getFechaHoraEmision() {
		return fechaHoraEmision;
	}

	/**
	 * @param fechaHoraEmision the fechaHoraEmision to set
	 */
	public void setFechaHoraEmision(LocalDateTime fechaHoraEmision) {
		this.fechaHoraEmision = fechaHoraEmision;
	}

	/**
	 * @return the totalFacturado
	 */
	public Double getTotalFacturado() {
		return totalFacturado;
	}

	/**
	 * @param totalFacturado the totalFacturado to set
	 */
	public void setTotalFacturado(Double totalFacturado) {
		this.totalFacturado = totalFacturado;
	}

	/**
	 * @return the totalRestante
	 */
	public Double getTotalRestante() {
		return totalRestante;
	}

	/**
	 * @param totalRestante the totalRestante to set
	 */
	public void setTotalRestante(Double totalRestante) {
		this.totalRestante = totalRestante;
	}

	/**
	 * @return the totalPagado
	 */
	public Double getTotalPagado() {
		return totalPagado;
	}

	/**
	 * @param totalPagado the totalPagado to set
	 */
	public void setTotalPagado(Double totalPagado) {
		this.totalPagado = totalPagado;
	}
	
}
