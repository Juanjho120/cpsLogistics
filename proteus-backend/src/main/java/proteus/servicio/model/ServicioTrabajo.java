package proteus.servicio.model;

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
 * Model for Table "servicio_trabajos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "servicio_trabajos")
public class ServicioTrabajo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServicioTrabajo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_servicio", nullable = false, foreignKey = @ForeignKey(name = "fkServicioTrabajoServicio"))
	private Servicio servicio;
	
	@NotNull(message = "La descripcion del trabajo del servicio no puede ser nulo")
	@Size(min = 3, message = "La descripcion del trabajo del servicio debe tener por lo menos 3 caracteres")
	@Column(name = "descripcion_trabajo", nullable = false)
	private String descripcionTrabajo;
	
	@PositiveOrZero(message = "El costo del trabajo del servicio debe ser positivo o cero")
	@Column(name = "costo")
	private Double costo;
	
	public ServicioTrabajo() {}

	/**
	 * @param idServicioTrabajo
	 * @param descripcionTrabajo
	 * @param costo
	 */
	public ServicioTrabajo(Integer idServicioTrabajo, Servicio servicio, String descripcionTrabajo, Double costo) {
		this.idServicioTrabajo = idServicioTrabajo;
		this.servicio = servicio;
		this.descripcionTrabajo = descripcionTrabajo;
		this.costo = costo;
	}

	/**
	 * @return the idServicioTrabajo
	 */
	public Integer getIdServicioTrabajo() {
		return idServicioTrabajo;
	}

	/**
	 * @param idServicioTrabajo the idServicioTrabajo to set
	 */
	public void setIdServicioTrabajo(Integer idServicioTrabajo) {
		this.idServicioTrabajo = idServicioTrabajo;
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
	
}
