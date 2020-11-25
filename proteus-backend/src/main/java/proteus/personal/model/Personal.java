package proteus.personal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "personal"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "personal")
public class Personal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPersonal;
	
	@NotNull(message = "El puesto del personal no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_personal_puesto", nullable = false, foreignKey = @ForeignKey(name = "fkPersonalPersonalPuesto"))
	private PersonalPuesto personalPuesto;
	
	@NotNull(message = "El nombre  del personal no puede ser nulo")
	@Size(min = 3, message = "El nombre del personal debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	
	@NotNull(message = "El telefono del personal no puede ser nulo")
	@Size(min = 8, max = 8, message = "El telefono del personal debe tener 8 digitos")
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	public Personal() {}

	/**
	 * @param idPersonal
	 * @param personalPuesto
	 * @param nombre
	 * @param telefono
	 */
	public Personal(Integer idPersonal, PersonalPuesto personalPuesto, String nombre, String telefono) {
		this.idPersonal = idPersonal;
		this.personalPuesto = personalPuesto;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	/**
	 * @return the idPersonal
	 */
	public Integer getIdPersonal() {
		return idPersonal;
	}

	/**
	 * @param idPersonal the idPersonal to set
	 */
	public void setIdPersonal(Integer idPersonal) {
		this.idPersonal = idPersonal;
	}

	/**
	 * @return the personalPuesto
	 */
	public PersonalPuesto getPersonalPuesto() {
		return personalPuesto;
	}

	/**
	 * @param personalPuesto the personalPuesto to set
	 */
	public void setPersonalPuesto(PersonalPuesto personalPuesto) {
		this.personalPuesto = personalPuesto;
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
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
