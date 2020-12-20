package proteus.caja.model;

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
import javax.validation.constraints.Size;

import proteus.comprobante.model.ComprobanteTipo;
import proteus.concepto.model.Concepto;
import proteus.personal.model.Personal;
import proteus.proveedor.model.ProveedorMenor;
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
	
	@PastOrPresent(message = "La fecha de ingreso debe estar en tiempo pasado o presente")
	@Column(name = "fecha_ingreso", nullable = false)
	private LocalDate fechaIngreso;
	
	@NotNull(message = "El concepto no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_concepto", nullable = false, foreignKey = @ForeignKey(name = "fkCajaChicaConcepto"))
	private Concepto concepto;
	
	@NotNull(message = "El monto de la caja chica no puede ser nulo")
	@PositiveOrZero(message = "El monto de la caja chica debe ser positivo o cero")
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	@NotNull(message = "El que autoriza no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_autoriza", nullable = false, foreignKey = @ForeignKey(name = "fkCajaChicaPersonalAutoriza"))
	private Personal autoriza;
	
	@NotNull(message = "El que recibe no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_recibe", nullable = false, foreignKey = @ForeignKey(name = "fkCajaChicaPersonalRecibe"))
	private Personal recibe;
	
	@NotNull(message = "La descripcion de la caja chica no puede ser nulo")
	@Size(min = 3, message = "La descripcion de la caja chica debe tener por lo menos 3 caracteres")
	@Column(name = "descripcion", nullable = false, length = 60)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_servicio", foreignKey = @ForeignKey(name = "fkCajaChicaServicio"))
	private Servicio servicio;
	
	@NotNull(message = "El tipo de comprobante no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_comprobante_tipo", nullable = false, foreignKey = @ForeignKey(name = "fkCajaChicaComprobanteTipo"))
	private ComprobanteTipo comprobanteTipo;
	
	@Size(min = 3, message = "El numero de comprobante de la caja chica debe tener por lo menos 3 caracteres")
	@Column(name = "numero_comprobante", length = 60)
	private String numeroComprobante;
	
	@ManyToOne
	@JoinColumn(name = "id_proveedor_menor", foreignKey = @ForeignKey(name = "fkCajaChicaProveedorMenor"))
	private ProveedorMenor proveedorMenor;
	
	
	public CajaChica() {}

	/**
	 * @param idCajaChica
	 * @param fechaIngreso
	 * @param concepto
	 * @param monto
	 * @param autoriza
	 * @param recibe
	 * @param descripcion
	 * @param servicio
	 * @param comprobanteTipo
	 * @param numeroComprobante
	 * @param proveedorMenor
	 */
	public CajaChica(Integer idCajaChica, LocalDate fechaIngreso, Concepto concepto, Double monto, Personal autoriza, Personal recibe, String descripcion,
			Servicio servicio, ComprobanteTipo comprobanteTipo, String numeroComprobante, ProveedorMenor proveedorMenor) {
		this.idCajaChica = idCajaChica;
		this.fechaIngreso = fechaIngreso;
		this.concepto = concepto;
		this.monto = monto;
		this.autoriza = autoriza;
		this.recibe = recibe;
		this.descripcion = descripcion;
		this.servicio = servicio;
		this.comprobanteTipo = comprobanteTipo;
		this.numeroComprobante = numeroComprobante;
		this.proveedorMenor = proveedorMenor;
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
	 * @return the autoriza
	 */
	public Personal getAutoriza() {
		return autoriza;
	}

	/**
	 * @param autoriza the autoriza to set
	 */
	public void setAutoriza(Personal autoriza) {
		this.autoriza = autoriza;
	}

	/**
	 * @return the recibe
	 */
	public Personal getRecibe() {
		return recibe;
	}

	/**
	 * @param recibe the recibe to set
	 */
	public void setRecibe(Personal recibe) {
		this.recibe = recibe;
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
	 * @return the comprobanteTipo
	 */
	public ComprobanteTipo getComprobanteTipo() {
		return comprobanteTipo;
	}

	/**
	 * @param comprobanteTipo the comprobanteTipo to set
	 */
	public void setComprobanteTipo(ComprobanteTipo comprobanteTipo) {
		this.comprobanteTipo = comprobanteTipo;
	}

	/**
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * @param numeroComprobante the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * @return the proveedorMenor
	 */
	public ProveedorMenor getProveedorMenor() {
		return proveedorMenor;
	}

	/**
	 * @param proveedorMenor the proveedorMenor to set
	 */
	public void setProveedorMenor(ProveedorMenor proveedorMenor) {
		this.proveedorMenor = proveedorMenor;
	}

}
