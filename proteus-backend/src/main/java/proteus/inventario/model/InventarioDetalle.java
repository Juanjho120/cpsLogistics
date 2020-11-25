package proteus.inventario.model;

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

import proteus.repuesto.model.Repuesto;

/**
 * Model for Table "inventario_detalles"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "inventario_detalles")
public class InventarioDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInventarioDetalle;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_inventario", nullable = false, foreignKey = @ForeignKey(name = "fkInventarioDetalleInventario"))
	private Inventario inventario;
	
	@NotNull(message = "El repuesto no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_repuesto", nullable = false, foreignKey = @ForeignKey(name = "fkInventarioDetalleRepuesto"))
	private Repuesto repuesto;
	
	@NotNull(message = "La cantidad del inventario detalle no puede ser nulo")
	@PositiveOrZero(message = "La cantidad del inventario detalle debe ser positivo o cero")
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	public InventarioDetalle() {}

	/**
	 * @param idInventarioDetalle
	 * @param inventario
	 * @param repuesto
	 * @param cantidad
	 */
	public InventarioDetalle(Integer idInventarioDetalle, Inventario inventario, Repuesto repuesto, Integer cantidad) {
		this.idInventarioDetalle = idInventarioDetalle;
		this.inventario = inventario;
		this.repuesto = repuesto;
		this.cantidad = cantidad;
	}

	/**
	 * @return the idInventarioDetalle
	 */
	public Integer getIdInventarioDetalle() {
		return idInventarioDetalle;
	}

	/**
	 * @param idInventarioDetalle the idInventarioDetalle to set
	 */
	public void setIdInventarioDetalle(Integer idInventarioDetalle) {
		this.idInventarioDetalle = idInventarioDetalle;
	}

	/**
	 * @return the inventario
	 */
	public Inventario getInventario() {
		return inventario;
	}

	/**
	 * @param inventario the inventario to set
	 */
	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
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
	
}
