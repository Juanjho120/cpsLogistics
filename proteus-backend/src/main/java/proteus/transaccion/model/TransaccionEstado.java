package proteus.transaccion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model for Table "transaccion_estados"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "transaccion_estados")
public class TransaccionEstado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTransaccionEstado;
	
	@NotNull(message = "El nombre del estado de transaccion no puede ser nulo")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	public TransaccionEstado() {}

	/**
	 * @param idTransaccionEstado
	 * @param nombre
	 */
	public TransaccionEstado(Integer idTransaccionEstado, String nombre) {
		this.idTransaccionEstado = idTransaccionEstado;
		this.nombre = nombre;
	}

	/**
	 * @return the idTransaccionEstado
	 */
	public Integer getIdTransaccionEstado() {
		return idTransaccionEstado;
	}

	/**
	 * @param idTransaccionEstado the idTransaccionEstado to set
	 */
	public void setIdTransaccionEstado(Integer idTransaccionEstado) {
		this.idTransaccionEstado = idTransaccionEstado;
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
