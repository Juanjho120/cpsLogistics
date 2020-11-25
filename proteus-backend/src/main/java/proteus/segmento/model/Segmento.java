package proteus.segmento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "segmentos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "segmentos")
public class Segmento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSegmento;
	
	@NotNull(message = "El nombre del segmento no puede ser nulo")
	@Size(min = 3, message = "El nombre del segmento debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Size(min = 7, message = "El nit del segmento debe tener por lo menos 7 numeros")
	@Column(name = "nit", nullable = false)
	private String nit;
	
	@NotNull(message = "La direccion fiscal del segmento no puede ser nulo")
	@Column(name = "direccion_fiscal", nullable = false)
	private String direccionFiscal;

	public Segmento() {}
	
	/**
	 * @param idSegmento
	 * @param nombre
	 * @param nit
	 * @param direccionFiscal
	 */
	public Segmento(Integer idSegmento, String nombre, String nit, String direccionFiscal) {
		this.idSegmento = idSegmento;
		this.nombre = nombre;
		this.nit = nit;
		this.direccionFiscal = direccionFiscal;
	}

	/**
	 * @return the idSegmento
	 */
	public Integer getIdSegmento() {
		return idSegmento;
	}

	/**
	 * @param idSegmento the idSegmento to set
	 */
	public void setIdSegmento(Integer idSegmento) {
		this.idSegmento = idSegmento;
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
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	/**
	 * @return the direccionFiscal
	 */
	public String getDireccionFiscal() {
		return direccionFiscal;
	}

	/**
	 * @param direccionFiscal the direccionFiscal to set
	 */
	public void setDireccionFiscal(String direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}
	
}
