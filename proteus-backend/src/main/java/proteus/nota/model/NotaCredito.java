package proteus.nota.model;

import java.time.LocalDate;

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
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import proteus.factura.model.FacturaCompra;

/**
 * Model for Table "notas_credito"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "notas_credito")
public class NotaCredito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idNotaCredito;
	
	@NotNull(message = "El codigo de la nota de credito no puede ser nulo")
	@Column(name = "codigo", nullable = false)
	private String codigo;
	
	@PositiveOrZero(message = "El monto de la nota de credito debe ser positivo o cero")
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	@NotNull(message = "La descripcion de la nota de credito no puede ser nulo")
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@PastOrPresent(message = "La fecha de ingreso de la nota de credito debe estar en tiempo pasado o presente")
	@Column(name = "fecha_ingreso", nullable = false)
	private LocalDate fechaIngreso;
	
	@NotNull(message = "La factura de compra no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_factura_compra", nullable = false, foreignKey = @ForeignKey(name = "fkNotaCreditoDetalleFacturaCompra"))
	private FacturaCompra facturaCompra;
	
	@NotNull(message = "La fecha de aplicacion de la nota de credito no puede ser nulo")
	@PastOrPresent(message = "La fecha de aplicacion de la nota de credito debe estar en tiempo pasado o presente")
	@Column(name = "fecha_aplicacion", nullable = false)
	private LocalDate fechaAplicacion;
	
	public NotaCredito() {}

	/**
	 * @param idNotaCredito
	 * @param codigo
	 * @param monto
	 * @param descripcion
	 * @param fechaIngreso
	 * @param fechaAplicacion
	 * @param facturaCompra
	 */
	public NotaCredito(Integer idNotaCredito, String codigo, Double monto, String descripcion, LocalDate fechaIngreso, LocalDate fechaAplicacion, FacturaCompra facturaCompra) {
		this.idNotaCredito = idNotaCredito;
		this.codigo = codigo;
		this.monto = monto;
		this.descripcion = descripcion;
		this.fechaIngreso = fechaIngreso;
		this.fechaAplicacion = fechaAplicacion;
		this.facturaCompra = facturaCompra;
	}

	/**
	 * @return the idNotaCredito
	 */
	public Integer getIdNotaCredito() {
		return idNotaCredito;
	}

	/**
	 * @param idNotaCredito the idNotaCredito to set
	 */
	public void setIdNotaCredito(Integer idNotaCredito) {
		this.idNotaCredito = idNotaCredito;
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
	 * @return the fechaIngreso
	 */
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
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
	 * @return the fechaAplicacion
	 */
	public LocalDate getFechaAplicacion() {
		return fechaAplicacion;
	}

	/**
	 * @param fechaAplicacion the fechaAplicacion to set
	 */
	public void setFechaAplicacion(LocalDate fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}
	
}
