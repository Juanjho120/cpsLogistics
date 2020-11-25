package proteus.cotizacion.model;

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
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

import proteus.repuesto.model.Repuesto;

/**
 * Model for Table "cotizacion_repuestos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "cotizacion_repuestos")
public class CotizacionRepuesto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCotizacionRepuesto;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cotizacion", nullable = false, foreignKey = @ForeignKey(name = "fkCotizacionRepuestoCotizacion"))
	private Cotizacion cotizacion;
	
	@NotNull(message = "El repuesto no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_repuesto", nullable = false, foreignKey = @ForeignKey(name = "fkCotizacionRepuestoRepuesto"))
	private Repuesto repuesto;
	
	@NotNull(message = "La cantidad del repuesto de la cotizacion no puede ser nulo")
	@PositiveOrZero(message = "La cantidad del repuesto de la cotizacion debe ser positivo o cero")
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	@PositiveOrZero(message = "El costo unitario del repuesto de la cotizacion debe ser positivo o cero")
	@Column(name = "costo_unitario")
	private Double costoUnitario;
	
	@PositiveOrZero(message = "El costo total del repuesto de la cotizacion debe ser positivo o cero")
	@Column(name = "costo_total")
	private Double costoTotal;
	
	public CotizacionRepuesto() {}

	/**
	 * @param idCotizacionRepuesto
	 * @param cotizacion
	 * @param repuesto
	 * @param cantidad
	 * @param costoUnitario
	 * @param costoTotal
	 */
	public CotizacionRepuesto(Integer idCotizacionRepuesto, Cotizacion cotizacion, Repuesto repuesto, Integer cantidad, Double costoUnitario, Double costoTotal) {
		this.idCotizacionRepuesto = idCotizacionRepuesto;
		this.cotizacion = cotizacion;
		this.repuesto = repuesto;
		this.cantidad = cantidad;
		this.costoUnitario = costoUnitario;
		this.costoTotal = costoTotal;
	}

	/**
	 * @return the idCotizacionRepuesto
	 */
	public Integer getIdCotizacionRepuesto() {
		return idCotizacionRepuesto;
	}

	/**
	 * @param idCotizacionRepuesto the idCotizacionRepuesto to set
	 */
	public void setIdCotizacionRepuesto(Integer idCotizacionRepuesto) {
		this.idCotizacionRepuesto = idCotizacionRepuesto;
	}

	/**
	 * @return the cotizacion
	 */
	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	/**
	 * @param cotizacion the cotizacion to set
	 */
	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * @return the repuesto
	 */
	public Repuesto getRepuesto() {
		return repuesto;
	}

	/**
	 * @param repuesto the repuesto to set
	 */
	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the costoUnitario
	 */
	public Double getCostoUnitario() {
		return costoUnitario;
	}

	/**
	 * @param costoUnitario the costoUnitario to set
	 */
	public void setCostoUnitario(Double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	/**
	 * @return the costoTotal
	 */
	public Double getCostoTotal() {
		return costoTotal;
	}

	/**
	 * @param costoTotal the costoTotal to set
	 */
	public void setCostoTotal(Double costoTotal) {
		this.costoTotal = costoTotal;
	}
	
}
