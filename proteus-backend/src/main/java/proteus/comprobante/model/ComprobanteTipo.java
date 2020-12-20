package proteus.comprobante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "comprobante_tipos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "comprobante_tipos")
public class ComprobanteTipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idComprobanteTipo;
	
	@NotNull(message = "El nombre del tipo de comprobante no puede ser nulo")
	@Size(min = 3, message = "El nombre del tipo de comprobante debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 40)
	private String nombre;
	
	public ComprobanteTipo() {}

	/**
	 * @param idComprobanteTipo
	 * @param nombre
	 */
	public ComprobanteTipo(Integer idComprobanteTipo, String nombre) {
		this.idComprobanteTipo = idComprobanteTipo;
		this.nombre = nombre;
	}

	/**
	 * @return the idComprobanteTipo
	 */
	public Integer getIdComprobanteTipo() {
		return idComprobanteTipo;
	}

	/**
	 * @param idComprobanteTipo the idComprobanteTipo to set
	 */
	public void setIdComprobanteTipo(Integer idComprobanteTipo) {
		this.idComprobanteTipo = idComprobanteTipo;
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
