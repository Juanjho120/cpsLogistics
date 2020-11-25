package proteus.cuenta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "cuenta_bancaria_tipos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "cuenta_bancaria_tipos")
public class CuentaBancariaTipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCuentaBancariaTipo;
	
	@NotNull(message = "El nombre del tipo de cuenta bancaria no puede ser nulo")
	@Size(min = 3, message = "El nombre del tipo de cuenta bancaria debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	public CuentaBancariaTipo() {}
	
	/**
	 * @param idCuentaBancariaTipo
	 * @param nombre
	 */
	public CuentaBancariaTipo(Integer idCuentaBancariaTipo, String nombre) {
		this.idCuentaBancariaTipo = idCuentaBancariaTipo;
		this.nombre = nombre;
	}

	/**
	 * @return the idCuentaBancariaTipo
	 */
	public Integer getIdCuentaBancariaTipo() {
		return idCuentaBancariaTipo;
	}

	/**
	 * @param idCuentaBancariaTipo the idCuentaBancariaTipo to set
	 */
	public void setIdCuentaBancariaTipo(Integer idCuentaBancariaTipo) {
		this.idCuentaBancariaTipo = idCuentaBancariaTipo;
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
