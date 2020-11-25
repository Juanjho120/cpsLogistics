package proteus.usuario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@NotNull(message = "El rol no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_rol", nullable = false, foreignKey = @ForeignKey(name = "fkUsuarioRol"))
	private Rol rol;
	
	public Usuario() {}

	/**
	 * @param idUsuario
	 * @param nombre
	 * @param apellido
	 * @param username
	 * @param email
	 * @param telefono
	 * @param password
	 * @param rol
	 */
	public Usuario(Integer idUsuario, String nombre, String apellido, String username, String email, String telefono, String password, Rol rol) {
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.username = username;
		this.email = email;
		this.telefono = telefono;
		this.password = password;
		this.rol = rol;
	}

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
