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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model for Table "cotizacion_trabajos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "cotizacion_trabajos")
public class CotizacionTrabajo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCotizacionTrabajo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cotizacion", nullable = false, foreignKey = @ForeignKey(name = "fkCotizacionTrabajoCotizacion"))
	private Cotizacion cotizacion;
	
	@NotNull(message = "La descripcion del trabajo de la cotizacion no puede ser nulo")
	@Size(min = 3, message = "La descripcion del trabajo de la cotizacion debe tener por lo menos 3 caracteres")
	@Column(name = "descripcion_trabajo", nullable = false, length = 30)
	private String descripcionTrabajo;
	
	@PositiveOrZero(message = "El costo del trabajo de la cotizacion debe ser positivo o cero")
	@Column(name = "costo")
	private Double costo;
	
	public CotizacionTrabajo() {}

	/**
	 * @param idCotizacionTrabajo
	 * @param cotizacion
	 * @param descripcionTrabajo
	 * @param costo
	 */
	public CotizacionTrabajo(Integer idCotizacionTrabajo, Cotizacion cotizacion, String descripcionTrabajo, Double costo) {
		this.idCotizacionTrabajo = idCotizacionTrabajo;
		this.cotizacion = cotizacion;
		this.descripcionTrabajo = descripcionTrabajo;
		this.costo = costo;
	}

	/**
	 * @return the idCotizacionTrabajo
	 */
	public Integer getIdCotizacionTrabajo() {
		return idCotizacionTrabajo;
	}

	/**
	 * @param idCotizacionTrabajo the idCotizacionTrabajo to set
	 */
	public void setIdCotizacionTrabajo(Integer idCotizacionTrabajo) {
		this.idCotizacionTrabajo = idCotizacionTrabajo;
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
	 * @return the descripcionTrabajo
	 */
	public String getDescripcionTrabajo() {
		return descripcionTrabajo;
	}

	/**
	 * @param descripcionTrabajo the descripcionTrabajo to set
	 */
	public void setDescripcionTrabajo(String descripcionTrabajo) {
		this.descripcionTrabajo = descripcionTrabajo;
	}

	/**
	 * @return the costo
	 */
	public Double getCosto() {
		return costo;
	}

	/**
	 * @param costo the costo to set
	 */
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
}
