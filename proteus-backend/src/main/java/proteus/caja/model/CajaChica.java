package proteus.caja.model;

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
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import proteus.proveedor.model.Proveedor;
import proteus.servicio.model.Servicio;

/**
 * Model for Table "cajas_chicas"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "cajas_chicas")
public class CajaChica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCajaChica;
	
	@NotNull(message = "El servicio no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_servicio", nullable = false, foreignKey = @ForeignKey(name = "fkCajaChicaServicio"))
	private Servicio servicio;
	
	@NotNull(message = "El proveedor no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_proveedor", nullable = false, foreignKey = @ForeignKey(name = "fkCajaChicaProveedor"))
	private Proveedor proveedor;
	
	@NotNull(message = "La descripcion de la caja chica no puede ser nulo")
	@Size(min = 3, message = "La descripcion de la caja chica debe tener por lo menos 3 caracteres")
	@Column(name = "descripcion", nullable = false, length = 60)
	private String descripcion;
	
	@NotNull(message = "El monto de la caja chica no puede ser nulo")
	@PositiveOrZero(message = "El monto de la caja chica debe ser positivo o cero")
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	@NotNull(message = "El codigo de factura de la caja chica no puede ser nulo")
	@Size(min = 6, message = "El codigo de factura de la caja chica debe tener por lo menos 6 caracteres")
	@Column(name = "codigo_factura", nullable = false, length = 15)
	private String codigoFactura;
	
	public CajaChica() {}

	/**
	 * @param idCajaChica
	 * @param servicio
	 * @param proveedor
	 * @param descripcion
	 * @param monto
	 * @param codigoFactura
	 */
	public CajaChica(Integer idCajaChica, Servicio servicio, Proveedor proveedor, String descripcion, Double monto, String codigoFactura) {
		this.idCajaChica = idCajaChica;
		this.servicio = servicio;
		this.proveedor = proveedor;
		this.descripcion = descripcion;
		this.monto = monto;
		this.codigoFactura = codigoFactura;
	}

	/**
	 * @return the idCajaChica
	 */
	public Integer getIdCajaChica() {
		return idCajaChica;
	}

	/**
	 * @param idCajaChica the idCajaChica to set
	 */
	public void setIdCajaChica(Integer idCajaChica) {
		this.idCajaChica = idCajaChica;
	}

	/**
	 * @return the servicio
	 */
	public Servicio getServicio() {
		return servicio;
	}

	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
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
	 * @return the monto
	 */
	public Double getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	/**
	 * @return the codigoFactura
	 */
	public String getCodigoFactura() {
		return codigoFactura;
	}

	/**
	 * @param codigoFactura the codigoFactura to set
	 */
	public void setCodigoFactura(String codigoFactura) {
		this.codigoFactura = codigoFactura;
	}
	
}
