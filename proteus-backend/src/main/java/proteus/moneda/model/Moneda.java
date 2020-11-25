package proteus.moneda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "monedas"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "monedas")
public class Moneda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMoneda;
	
	@NotNull(message = "El nombre de moneda no puede ser nulo")
	@Size(min = 3, message = "El nombre de moneda debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 40)
	private String nombre;
	
	@NotNull(message = "El simbolo de moneda no puede ser nulo")
	@Size(min = 1, message = "El simbolo de moneda debe tener por lo menos 1 caracter")
	@Column(name = "simbolo", nullable = false, length = 5)
	private String simbolo;
	
	public Moneda() {}

	/**
	 * @param idMoneda
	 * @param nombre
	 * @param simbolo
	 */
	public Moneda(Integer idMoneda, String nombre, String simbolo) {
		this.idMoneda = idMoneda;
		this.nombre = nombre;
		this.simbolo = simbolo;
	}

	/**
	 * @return the idMoneda
	 */
	public Integer getIdMoneda() {
		return idMoneda;
	}

	/**
	 * @param idMoneda the idMoneda to set
	 */
	public void setIdMoneda(Integer idMoneda) {
		this.idMoneda = idMoneda;
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

	/**
	 * @return the simbolo
	 */
	public String getSimbolo() {
		return simbolo;
	}

	/**
	 * @param simbolo the simbolo to set
	 */
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
}
