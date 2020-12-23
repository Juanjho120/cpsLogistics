package proteus.ventana.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import proteus.rol.model.Rol;

/**
 * Model for Table "ventanas_roles"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "ventanas_roles")
public class VentanaRol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVentanaRol;

	@NotNull(message = "La ventana no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_ventana", nullable = false, foreignKey = @ForeignKey(name = "fkVentanaRolVentana"))
	private Ventana ventana;
	
	@NotNull(message = "El rol no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "fkVentanaRolRol"))
	private Rol rol;
	
	public VentanaRol() {}

	/**
	 * @param idVentanaRol
	 * @param ventana
	 * @param rol
	 */
	public VentanaRol(Integer idVentanaRol, Ventana ventana, Rol rol) {
		this.idVentanaRol = idVentanaRol;
		this.ventana = ventana;
		this.rol = rol;
	}

	/**
	 * @return the idVentanaRol
	 */
	public Integer getIdVentanaRol() {
		return idVentanaRol;
	}

	/**
	 * @param idVentanaRol the idVentanaRol to set
	 */
	public void setIdVentanaRol(Integer idVentanaRol) {
		this.idVentanaRol = idVentanaRol;
	}

	/**
	 * @return the ventana
	 */
	public Ventana getVentana() {
		return ventana;
	}

	/**
	 * @param ventana the ventana to set
	 */
	public void setVentana(Ventana ventana) {
		this.ventana = ventana;
	}

	/**
	 * @return the rol
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
}
