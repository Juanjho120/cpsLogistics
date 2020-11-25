package proteus.concepto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "conceptos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "conceptos")
public class Concepto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConcepto;
	
	@NotNull(message = "El nombre del concepto no puede ser nulo")
	@Size(min = 3, message = "El nombre del concepto debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	public Concepto() {}
	
	/**
	 * @param idConcepto
	 * @param nombre
	 */
	public Concepto(Integer idConcepto, String nombre) {
		this.idConcepto = idConcepto;
		this.nombre = nombre;
	}

	/**
	 * @return the idConcepto
	 */
	public Integer getIdConcepto() {
		return idConcepto;
	}

	/**
	 * @param idConcepto the idConcepto to set
	 */
	public void setIdConcepto(Integer idConcepto) {
		this.idConcepto = idConcepto;
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
