package proteus.proveedor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "proveedores"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "proveedores")
public class Proveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProveedor;
	
	@NotNull(message = "El nombre del proveedor no puede ser nulo")
	@Size(min = 3, message = "El nombre del proveedor debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	@Size(min = 7, message = "El nit del proveedor debe tener por lo menos 7 numeros")
	@Column(name = "nit", nullable = false)
	private String nit;
	
	@NotNull(message = "La direccion fiscal del proveedor no puede ser nulo")
	@Column(name = "direccion_fiscal", nullable = false)
	private String direccionFiscal;
	
	@Column(name = "telefono")
	private String telefono;
	
	public Proveedor() {}

	/**
	 * @param idProveedor
	 * @param nombre
	 * @param nit
	 * @param direccionFiscal
	 * @param telefono
	 */
	public Proveedor(Integer idProveedor, String nombre, String nit, String direccionFiscal, String telefono) {
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.nit = nit;
		this.direccionFiscal = direccionFiscal;
		this.telefono = telefono;
	}

	/**
	 * @return the idProveedor
	 */
	public Integer getIdProveedor() {
		return idProveedor;
	}

	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
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

	/**
	 * @return the telefonoProveedor
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
