package proteus.rol.model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import proteus.permiso.model.Permiso;
import proteus.ventana.model.Ventana;

/**
 * Model for Table "roles_permisos_ventanas"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "roles_permisos_ventanas")
public class RolPermisoVentana {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRolPermisoVentana;
	
	@NotNull(message = "El rol no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "fkRolPermisoVentanaRol"))
	private Rol rol;
	
	@NotNull(message = "El permiso no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_permiso", nullable = false, foreignKey = @ForeignKey(name = "fkRolPermisoVentanaPermiso"))
	private Permiso permiso;
	
	@NotNull(message = "La ventana no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_ventana", nullable = false, foreignKey = @ForeignKey(name = "fkRolPermisoVentanaVentana"))
	private Ventana ventana;
	
	public RolPermisoVentana() {}

	/**
	 * @param idRolPermisoVentana
	 * @param rol
	 * @param permiso
	 * @param ventana
	 */
	public RolPermisoVentana(Integer idRolPermisoVentana, Rol rol, Permiso permiso, Ventana ventana) {
		this.idRolPermisoVentana = idRolPermisoVentana;
		this.rol = rol;
		this.permiso = permiso;
		this.ventana = ventana;
	}

	/**
	 * @return the idRolPermisoVentana
	 */
	public Integer getIdRolPermisoVentana() {
		return idRolPermisoVentana;
	}

	/**
	 * @param idRolPermisoVentana the idRolPermisoVentana to set
	 */
	public void setIdRolPermisoVentana(Integer idRolPermisoVentana) {
		this.idRolPermisoVentana = idRolPermisoVentana;
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

	/**
	 * @return the permiso
	 */
	public Permiso getPermiso() {
		return permiso;
	}

	/**
	 * @param permiso the permiso to set
	 */
	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
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

}
