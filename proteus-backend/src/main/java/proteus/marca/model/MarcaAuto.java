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
 * Model for Table "marcas_auto"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "marcas_auto")
public class MarcaAuto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMarcaAuto;
	
	@NotNull(message = "El nombre de la marca del auto no puede ser nulo")
	@Size(min = 3, message = "El nombre de la marca del auto debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	public MarcaAuto() {}
	
	/**
	 * @param idMarcaAuto
	 * @param nombre
	 */
	public MarcaAuto(Integer idMarcaAuto, String nombre) {
		this.idMarcaAuto = idMarcaAuto;
		this.nombre = nombre;
	}

	/**
	 * @return the idMarcaAuto
	 */
	public Integer getIdMarcaAuto() {
		return idMarcaAuto;
	}

	/**
	 * @param idMarcaAuto the idMarcaAuto to set
	 */
	public void setIdMarcaAuto(Integer idMarcaAuto) {
		this.idMarcaAuto = idMarcaAuto;
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
