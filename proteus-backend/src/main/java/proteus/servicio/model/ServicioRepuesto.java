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

import com.fasterxml.jackson.annotation.JsonIgnore;

import proteus.repuesto.model.Repuesto;

/**
 * Model for Table "servicio_repuestos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "servicio_repuestos")
public class ServicioRepuesto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServicioRepuesto;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_servicio", nullable = false, foreignKey = @ForeignKey(name = "fkServicioRepuestoServicio"))
	private Servicio servicio;
	
	@NotNull(message = "El repuesto no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_repuesto", nullable = false, foreignKey = @ForeignKey(name = "fkServicioRepuestoRepuesto"))
	private Repuesto repuesto;
	
	@NotNull(message = "La cantidad del repuesto del servicio no puede ser nulo")
	@PositiveOrZero(message = "La cantidad del repuesto del servicio debe ser positivo o cero")
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	@PositiveOrZero(message = "El costo unitario del repuesto del servicio debe ser positivo o cero")
	@Column(name = "costo_unitario")
	private Double costoUnitario;
	
	@PositiveOrZero(message = "El costo total del repuesto del servicio debe ser positivo o cero")
	@Column(name = "costo_total")
	private Double costoTotal;
	
	public ServicioRepuesto() {}

	/**
	 * @param idServicioRepuesto
	 * @param servicio
	 * @param repuesto
	 * @param cantidad
	 * @param costoUnitario
	 * @param costoTotal
	 */
	public ServicioRepuesto(Integer idServicioRepuesto, Servicio servicio, Repuesto repuesto, Integer cantidad, Double costoUnitario, Double costoTotal) {
		this.idServicioRepuesto = idServicioRepuesto;
		this.servicio = servicio;
		this.repuesto = repuesto;
		this.cantidad = cantidad;
		this.costoUnitario = costoUnitario;
		this.costoTotal = costoTotal;
	}

	/**
	 * @return the idServicioRepuesto
	 */
	public Integer getIdServicioRepuesto() {
		return idServicioRepuesto;
	}

	/**
	 * @param idServicioRepuesto the idServicioRepuesto to set
	 */
	public void setIdServicioRepuesto(Integer idServicioRepuesto) {
		this.idServicioRepuesto = idServicioRepuesto;
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
