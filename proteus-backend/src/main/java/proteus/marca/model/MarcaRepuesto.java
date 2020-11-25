package proteus.marca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "marcas_repuesto"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "marcas_repuesto")
public class MarcaRepuesto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMarcaRepuesto;
	
	@NotNull(message = "El nombre de la marca del repuesto no puede ser nulo")
	@Size(min = 3, message = "El nombre de la marca del repuesto debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	public MarcaRepuesto() {}
	
	/**
	 * @param idMarcaRepuesto
	 * @param nombre
	 */
	public MarcaRepuesto(Integer idMarcaRepuesto, String nombre) {
		this.idMarcaRepuesto = idMarcaRepuesto;
		this.nombre = nombre;
	}

	/**
	 * @return the idMarcaRepuesto
	 */
	public Integer getIdMarcaRepuesto() {
		return idMarcaRepuesto;
	}

	/**
	 * @param idMarcaRepuesto the idMarcaRepuesto to set
	 */
	public void setIdMarcaRepuesto(Integer idMarcaRepuesto) {
		this.idMarcaRepuesto = idMarcaRepuesto;
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
