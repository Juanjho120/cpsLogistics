package proteus.segmento.model;

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

/**
 * Model for Table "segmento_pago_detalles"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "segmento_pago_detalles")
public class SegmentoPagoDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSegmentoPagoDetalle;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_segmento_pago", nullable = false, foreignKey = @ForeignKey(name = "fkSegmentoPagoDetalleSegmentoPago"))
	private SegmentoPago segmentoPago;
	
	@NotNull(message = "El segmento credito detalle no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_segmento_credito_detalle", nullable = false, foreignKey = @ForeignKey(name = "fkSegmentoPagoDetalleSegmentoCreditoDetalle"))
	private SegmentoCreditoDetalle segmentoCreditoDetalle;
	
	public SegmentoPagoDetalle() {}

	/**
	 * @param idSegmentoPagoDetalle
	 * @param segmentoPago
	 * @param segmentoCreditoDetalle
	 */
	public SegmentoPagoDetalle(Integer idSegmentoPagoDetalle, SegmentoPago segmentoPago, SegmentoCreditoDetalle segmentoCreditoDetalle) {
		this.idSegmentoPagoDetalle = idSegmentoPagoDetalle;
		this.segmentoPago = segmentoPago;
		this.segmentoCreditoDetalle = segmentoCreditoDetalle;
	}

	/**
	 * @return the idSegmentoPagoDetalle
	 */
	public Integer getIdSegmentoPagoDetalle() {
		return idSegmentoPagoDetalle;
	}

	/**
	 * @param idSegmentoPagoDetalle the idSegmentoPagoDetalle to set
	 */
	public void setIdSegmentoPagoDetalle(Integer idSegmentoPagoDetalle) {
		this.idSegmentoPagoDetalle = idSegmentoPagoDetalle;
	}

	/**
	 * @return the segmentoPago
	 */
	public SegmentoPago getSegmentoPago() {
		return segmentoPago;
	}

	/**
	 * @param segmentoPago the segmentoPago to set
	 */
	public void setSegmentoPago(SegmentoPago segmentoPago) {
		this.segmentoPago = segmentoPago;
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
	
}
