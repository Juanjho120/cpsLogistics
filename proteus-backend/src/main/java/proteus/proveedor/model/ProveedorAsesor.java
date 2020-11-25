package proteus.proveedor.model;

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
 * Model for Table "proveedor_asesores"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "proveedor_asesores")
public class ProveedorAsesor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProveedorAsesor;
	
	@NotNull(message = "El nombre del asesor del proveedor no puede ser nulo")
	@Size(min = 3, message = "El nombre del asesor del proveedor debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 40)
	private String nombre;
	
	@Column(name = "telefono")
	private String telefono;
	
	@NotNull(message = "El proveedor no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_proveedor", nullable = false, foreignKey = @ForeignKey(name = "fkProveedorAsesorProveedor"))
	private Proveedor proveedor;
	
	public ProveedorAsesor() {}

	/**
	 * @param idProveedorAsesor
	 * @param nombre
	 * @param telefono
	 * @param proveedor
	 */
	public ProveedorAsesor(Integer idProveedorAsesor, String nombre, String telefono, Proveedor proveedor) {
		this.idProveedorAsesor = idProveedorAsesor;
		this.nombre = nombre;
		this.telefono = telefono;
		this.proveedor = proveedor;
	}

	/**
	 * @return the idProveedorAsesor
	 */
	public Integer getIdProveedorAsesor() {
		return idProveedorAsesor;
	}

	/**
	 * @param idProveedorAsesor the idProveedorAsesor to set
	 */
	public void setIdProveedorAsesor(Integer idProveedorAsesor) {
		this.idProveedorAsesor = idProveedorAsesor;
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

	/**
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}
