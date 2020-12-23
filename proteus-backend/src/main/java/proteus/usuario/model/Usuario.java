package proteus.usuario.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import proteus.rol.model.Rol;

/**
 * Model for Table "usuarios"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	@NotNull(message = "El nombre de usuario no puede ser nulo")
	@Size(min = 3, message = "El nombre de usuario debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	@NotNull(message = "El apellido del usuario no puede ser nulo")
	@Size(min = 3, message = "El apellido del usuario debe tener por lo menos 3 caracteres")
	@Column(name = "apellido", nullable = false, length = 50)
	private String apellido;
	
	@Column(name = "username", nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(name = "estado", nullable = false)
	private Boolean enable;
	
	@NotNull(message = "El email del usuario no puede ser nulo")
	@Email(message = "El email del usuario no es valido")
	@Column(name = "email", nullable = false)
	private String email;
	
	@NotNull(message = "El telefono del usuario no puede ser nulo")
	@Size(min = 8, max = 8, message = "El telefono del usuario debe tener 8 digitos")
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	@NotNull(message = "El password del usuario no puede ser nulo")
	@Size(min = 8, message = "El password del usuario debe tener al menos 8 caracteres")
	@Column(name = "password", nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"))
	private List<Rol> roles;
	
	/**
	 * @return the idUsuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the roles
	 */
	public List<Rol> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	/**
	 * @return the enable
	 */
	public Boolean getEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
}
