package proteus.factura.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import proteus.proveedor.model.Proveedor;

/**
 * Model for Table "facturas_compras"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "facturas_compras")
public class FacturaCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFacturaCompra;

	@NotNull(message = "El proveedor no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_proveedor", nullable = false, foreignKey = @ForeignKey(name = "fkFacturaCompraProveedor"))
	private Proveedor proveedor;
	
	@NotNull(message = "El codigo de la factura de compra no puede ser nulo")
	@Column(name = "codigo", nullable = false)
	private String codigo;
	
	@NotNull(message = "La fecha de la factura de compra no puede ser nulo")
	@PastOrPresent(message = "La fecha de la factura de compra debe estar en tiempo pasado o presente")
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@PositiveOrZero(message = "El total de la factura de compra debe ser positivo o cero")
	@Column(name = "total", nullable = false)
	private Double total;
	
	@OneToMany(mappedBy = "facturaCompra", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<FacturaCompraDetalle> facturaCompraDetalle;
	
	public FacturaCompra() {}

	/**
	 * @param idFacturaCompra
	 * @param proveedor
	 * @param codigo
	 * @param fecha
	 * @param total
	 */
	public FacturaCompra(Integer idFacturaCompra, Proveedor proveedor, String codigo, LocalDate fecha, Double total) {
		this.idFacturaCompra = idFacturaCompra;
		this.proveedor = proveedor;
		this.codigo = codigo;
		this.fecha = fecha;
		this.total = total;
	}

	/**
	 * @return the idFacturaCompra
	 */
	public Integer getIdFacturaCompra() {
		return idFacturaCompra;
	}

	/**
	 * @param idFacturaCompra the idFacturaCompra to set
	 */
	public void setIdFacturaCompra(Integer idFacturaCompra) {
		this.idFacturaCompra = idFacturaCompra;
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
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the total
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * @return the facturaCompraDetalle
	 */
	public List<FacturaCompraDetalle> getFacturaCompraDetalle() {
		return facturaCompraDetalle;
	}

	/**
	 * @param facturaCompraDetalle the facturaCompraDetalle to set
	 */
	public void setFacturaCompraDetalle(List<FacturaCompraDetalle> facturaCompraDetalle) {
		this.facturaCompraDetalle = facturaCompraDetalle;
	}
	
}
