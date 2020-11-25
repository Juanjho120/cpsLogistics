package proteus.factura.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import proteus.marca.model.MarcaRepuesto;
import proteus.repuesto.model.Repuesto;

/**
 * Model for Table "factura_compra_detalles"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "factura_compra_detalles")
public class FacturaCompraDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFacturaCompraDetalle;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_factura_compra", nullable = false, foreignKey = @ForeignKey(name = "fkFacturaCompraDetalleFacturaCompra"))
	private FacturaCompra facturaCompra;
	
	@NotNull(message = "El repuesto no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_repuesto", nullable = false, foreignKey = @ForeignKey(name = "fkFacturaCompraDetalleRepuesto"))
	private Repuesto repuesto;
	
	@NotNull(message = "La marca del repuesto no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_marca_repuesto", nullable = false, foreignKey = @ForeignKey(name = "fkFacturaCompraDetalleMarcaRepuesto"))
	private MarcaRepuesto marcaRepuesto;
	
	@NotNull(message = "La cantidad del repuesto de la factura de compra no puede ser nulo")
	@PositiveOrZero(message = "La cantidad del repuesto de la factura de compra debe ser positivo o cero")
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	@NotNull(message = "El costo unitario del repuesto de la factura de compra no puede ser nulo")
	@PositiveOrZero(message = "El costo unitario del repuesto de la factura de compra debe ser positivo o cero")
	@Column(name = "costo_unitario")
	private Double costoUnitario;
	
	@PositiveOrZero(message = "El costo total del repuesto de la factura de compra debe ser positivo o cero")
	@Column(name = "costo_total")
	private Double costoTotal;
	
	public FacturaCompraDetalle() {}

	/**
	 * @param idFacturaCompraDetalle
	 * @param facturaCompra
	 * @param repuesto
	 * @param marcaRepuesto
	 * @param cantidad
	 * @param costoUnitario
	 * @param costoTotal
	 */
	public FacturaCompraDetalle(Integer idFacturaCompraDetalle, FacturaCompra facturaCompra, Repuesto repuesto, MarcaRepuesto marcaRepuesto, Integer cantidad, Double costoUnitario, Double costoTotal) {
		this.idFacturaCompraDetalle = idFacturaCompraDetalle;
		this.facturaCompra = facturaCompra;
		this.repuesto = repuesto;
		this.marcaRepuesto = marcaRepuesto;
		this.cantidad = cantidad;
		this.costoUnitario = costoUnitario;
		this.costoTotal = costoTotal;
	}

	/**
	 * @return the idFacturaCompraDetalle
	 */
	public Integer getIdFacturaCompraDetalle() {
		return idFacturaCompraDetalle;
	}

	/**
	 * @param idFacturaCompraDetalle the idFacturaCompraDetalle to set
	 */
	public void setIdFacturaCompraDetalle(Integer idFacturaCompraDetalle) {
		this.idFacturaCompraDetalle = idFacturaCompraDetalle;
	}

	/**
	 * @return the facturaCompra
	 */
	public FacturaCompra getFacturaCompra() {
		return facturaCompra;
	}

	/**
	 * @param facturaCompra the facturaCompra to set
	 */
	public void setFacturaCompra(FacturaCompra facturaCompra) {
		this.facturaCompra = facturaCompra;
	}

	/**
	 * @return the repuesto
	 */
	public Repuesto getRepuesto() {
		return repuesto;
	}

	/**
	 * @param repuesto the repuesto to set
	 */
	public void setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}

	/**
	 * @return the marcaRepuesto
	 */
	public MarcaRepuesto getMarcaRepuesto() {
		return marcaRepuesto;
	}

	/**
	 * @param marcaRepuesto the marcaRepuesto to set
	 */
	public void setMarcaRepuesto(MarcaRepuesto marcaRepuesto) {
		this.marcaRepuesto = marcaRepuesto;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the costoUnitario
	 */
	public Double getCostoUnitario() {
		return costoUnitario;
	}

	/**
	 * @param costoUnitario the costoUnitario to set
	 */
	public void setCostoUnitario(Double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	/**
	 * @return the costoTotal
	 */
	public Double getCostoTotal() {
		return costoTotal;
	}

	/**
	 * @param costoTotal the costoTotal to set
	 */
	public void setCostoTotal(Double costoTotal) {
		this.costoTotal = costoTotal;
	}
	
}
