package proteus.banco.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "bancos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "bancos")
public class Banco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBanco;
	
	@NotNull(message = "El nombre del banco no puede ser nulo")
	@Size(min = 3, message = "El nombre del banco debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 40)
	private String nombre;
	
	public Banco() {}

	/**
	 * @param idBanco
	 * @param nombre
	 */
	public Banco(Integer idBanco, String nombre) {
		this.idBanco = idBanco;
		this.nombre = nombre;
	}

	/**
	 * @return the idBanco
	 */
	public Integer getIdBanco() {
		return idBanco;
	}

	/**
	 * @param idBanco the idBanco to set
	 */
	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
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
