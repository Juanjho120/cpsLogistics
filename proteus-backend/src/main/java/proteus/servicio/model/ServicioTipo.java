package proteus.servicio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "servicio_tipos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "servicio_tipos")
public class ServicioTipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServicioTipo;
	
	@NotNull(message = "El nombre del tipo de servicio no puede ser nulo")
	@Size(min = 3, message = "El nombre del tipo de servicio debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	public ServicioTipo() {}
	
	/**
	 * @param idServicioTipo
	 * @param nombre
	 */
	public ServicioTipo(Integer idServicioTipo, String nombre) {
		this.idServicioTipo = idServicioTipo;
		this.nombre = nombre;
	}

	/**
	 * @return the idServicioTipo
	 */
	public Integer getIdServicioTipo() {
		return idServicioTipo;
	}

	/**
	 * @param idServicioTipo the idServicioTipo to set
	 */
	public void setIdServicioTipo(Integer idServicioTipo) {
		this.idServicioTipo = idServicioTipo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
