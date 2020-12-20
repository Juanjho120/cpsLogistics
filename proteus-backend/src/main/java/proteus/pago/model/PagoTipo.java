package proteus.pago.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Model for Table "pago_tipos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "pago_tipos")
public class PagoTipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPagoTipo;

	@NotNull(message = "El nombre del tipo de pago no puede ser nulo")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	public PagoTipo() {}

	/**
	 * @param idPagoTipo
	 * @param nombre
	 */
	public PagoTipo(Integer idPagoTipo, String nombre) {
		this.idPagoTipo = idPagoTipo;
		this.nombre = nombre;
	}

	/**
	 * @return the idPagoTipo
	 */
	public Integer getIdPagoTipo() {
		return idPagoTipo;
	}

	/**
	 * @param idPagoTipo the idPagoTipo to set
	 */
	public void setIdPagoTipo(Integer idPagoTipo) {
		this.idPagoTipo = idPagoTipo;
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
