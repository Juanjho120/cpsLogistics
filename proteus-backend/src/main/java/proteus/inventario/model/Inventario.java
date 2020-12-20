package proteus.inventario.model;

import java.time.LocalDateTime;
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

import proteus.concepto.model.Concepto;
import proteus.usuario.model.Usuario;

/**
 * Model for Table "inventarios"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "inventarios")
public class Inventario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idInventario;
	
	@NotNull(message = "El usuario no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "fkInventarioUsuario"))
	private Usuario usuario;
	
	@NotNull(message = "El usuario no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_concepto", nullable = false, foreignKey = @ForeignKey(name = "fkInventarioConcepto"))
	private Concepto concepto;
	
	@NotNull(message = "La razon del inventario no puede ser nulo")
	@Column(name = "razon", nullable = false)
	private String razon;
	
	@PositiveOrZero(message = "La cantidad del inventario debe ser positivo o cero")
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;
	
	@PastOrPresent(message = "La fecha y hora del invetario debe estar en tiempo pasado o presente")
	@Column(name = "fecha_hora", nullable = false)
	private LocalDateTime fechaHora;
	
	@OneToMany(mappedBy = "inventario", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<InventarioDetalle> inventarioDetalle;
	
	public Inventario() {}

	/**
	 * @param idInventario
	 * @param usuario
	 * @param concepto
	 * @param razon
	 * @param cantidad
	 * @param fechaHora
	 */
	public Inventario(Integer idInventario, Usuario usuario, String razon, Concepto concepto, Integer cantidad, LocalDateTime fechaHora) {
		this.idInventario = idInventario;
		this.usuario = usuario;
		this.concepto = concepto;
		this.razon = razon;
		this.cantidad = cantidad;
		this.fechaHora = fechaHora;
	}

	/**
	 * @return the idInventario
	 */
	public Integer getIdInventario() {
		return idInventario;
	}

	/**
	 * @param idInventario the idInventario to set
	 */
	public void setIdInventario(Integer idInventario) {
		this.idInventario = idInventario;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the concepto
	 */
	public Concepto getConcepto() {
		return concepto;
	}

	/**
	 * @param concepto the concepto to set
	 */
	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	/**
	 * @return the razon
	 */
	public String getRazon() {
		return razon;
	}

	/**
	 * @param razon the razon to set
	 */
	public void setRazon(String razon) {
		this.razon = razon;
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
	 * @return the fechaHora
	 */
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	/**
	 * @param fechaHora the fechaHora to set
	 */
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * @return the inventarioDetalle
	 */
	public List<InventarioDetalle> getInventarioDetalle() {
		return inventarioDetalle;
	}

	/**
	 * @param inventarioDetalle the inventarioDetalle to set
	 */
	public void setInventarioDetalle(List<InventarioDetalle> inventarioDetalle) {
		this.inventarioDetalle = inventarioDetalle;
	}
	
}
