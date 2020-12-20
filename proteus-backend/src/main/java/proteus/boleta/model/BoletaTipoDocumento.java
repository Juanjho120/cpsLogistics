package proteus.boleta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model for Table "boleta_tipos_documentos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "boleta_tipos_documentos")
public class BoletaTipoDocumento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBoletaTipoDocumento;

	@NotNull(message = "El nombre del tipo de documento en la boleta no puede ser nulo")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	public BoletaTipoDocumento() {}

	/**
	 * @param idBoletaTipoDocumento
	 * @param nombre
	 */
	public BoletaTipoDocumento(Integer idBoletaTipoDocumento, String nombre) {
		this.idBoletaTipoDocumento = idBoletaTipoDocumento;
		this.nombre = nombre;
	}

	/**
	 * @return the idBoletaTipoDocumento
	 */
	public Integer getIdBoletaTipoDocumento() {
		return idBoletaTipoDocumento;
	}

	/**
	 * @param idBoletaTipoDocumento the idBoletaTipoDocumento to set
	 */
	public void setIdBoletaTipoDocumento(Integer idBoletaTipoDocumento) {
		this.idBoletaTipoDocumento = idBoletaTipoDocumento;
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
