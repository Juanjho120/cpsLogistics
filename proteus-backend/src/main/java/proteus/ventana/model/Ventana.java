package proteus.ventana.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "ventanas"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "ventanas")
public class Ventana {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVentana;
	
	@NotNull(message = "El nombre de la ventana no puede ser nulo")
	@Size(min = 4, message = "El nombre de la ventana debe tener por lo menos 4 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	@NotNull(message = "La descripcion de la ventana no puede ser nulo")
	@Size(min = 4, message = "La descripcion de la ventana debe tener por lo menos 4 caracteres")
	@Column(name = "descripcion", nullable = false, length = 50)
	private String descripcion;
	
	@NotNull(message = "La url de la ventana no puede ser nulo")
	@Size(min = 4, message = "La url de la ventana debe tener por lo menos 4 caracteres")
	@Column(name = "url", nullable = false)
	private String url;
	
	@Column(name = "icono")
	private String icono;
	
	public Ventana() {}
	
	/**
	 * @param idVentana
	 * @param nombre
	 * @param descripcion
	 * @param url
	 * @param icono
	 */
	public Ventana(Integer idVentana, String nombre, String descripcion, String url, String icono) {
		this.idVentana = idVentana;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
		this.icono = icono;
	}

	/**
	 * @return the idVentana
	 */
	public Integer getIdVentana() {
		return idVentana;
	}

	/**
	 * @param idVentana the idVentana to set
	 */
	public void setIdVentana(Integer idVentana) {
		this.idVentana = idVentana;
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the icono
	 */
	public String getIcono() {
		return icono;
	}

	/**
	 * @param icono the icono to set
	 */
	public void setIcono(String icono) {
		this.icono = icono;
	}
	
}
