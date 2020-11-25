package proteus.repuesto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

/**
 * Model for Table "repuestos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "repuestos")
public class Repuesto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRepuesto;
	
	@Column(name = "codigo", nullable = false)
	private String codigo;
	
	@Column(name = "codigo_barra")
	private String codigoBarra;
	
	@NotNull(message = "La descripcion del repuesto no puede ser nulo")
	@Size(min = 3, message = "La descripcion del repuesto debe tener por lo menos 3 caracteres")
	@Column(name = "descripcion", nullable = false)
	private String descripcion;
	
	@NotNull(message = "La existencia del repuesto no puede ser nulo")
	@PositiveOrZero(message = "La existencia del repuesto debe ser positivo o cero")
	@Column(name = "existencia", nullable = false)
	private Integer existencia;
	
	public Repuesto() {}

	/**
	 * @param idRepuesto
	 * @param codigo
	 * @param codigoBarra
	 * @param descripcion
	 * @param existencia
	 */
	public Repuesto(Integer idRepuesto, String codigo, String codigoBarra, String descripcion, Integer existencia) {
		this.idRepuesto = idRepuesto;
		this.codigo = codigo;
		this.codigoBarra = codigoBarra;
		this.descripcion = descripcion;
		this.existencia = existencia;
	}

	/**
	 * @return the idRepuesto
	 */
	public Integer getIdRepuesto() {
		return idRepuesto;
	}

	/**
	 * @param idRepuesto the idRepuesto to set
	 */
	public void setIdRepuesto(Integer idRepuesto) {
		this.idRepuesto = idRepuesto;
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
	 * @return the codigoBarra
	 */
	public String getCodigoBarra() {
		return codigoBarra;
	}

	/**
	 * @param codigoBarra the codigoBarra to set
	 */
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
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
	 * @return the existencia
	 */
	public Integer getExistencia() {
		return existencia;
	}

	/**
	 * @param existencia the existencia to set
	 */
	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}
	
}
