package proteus.modificacion.model;

import java.time.LocalDateTime;

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
import javax.validation.constraints.Size;

import proteus.concepto.model.Concepto;
import proteus.usuario.model.Usuario;

/**
 * Model for Table "modificaciones"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "modificaciones")
public class Modificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idModificacion;
	
	@NotNull(message = "El concepto de la modificacion no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_concepto", nullable = false, foreignKey = @ForeignKey(name = "fkModificacionConcepto"))
	private Concepto concepto;
	
	@NotNull(message = "EL nombre de la tabla de la modificacion no puede ser nulo")
	@Size(min = 5, message = "El nombre de la tabla de la modificacion debe tener por lo menos 5 caracteres")
	@Column(name = "tabla", nullable = false, length = 40)
	private String tabla;
	
	@NotNull(message = "EL nombre de la columna de la modificacion no puede ser nulo")
	@Size(min = 5, message = "El nombre de la columna de la modificacion debe tener por lo menos 5 caracteres")
	@Column(name = "columna", nullable = false, length = 40)
	private String columna;
	
	@NotNull(message = "El usuario responsable de la modificacion no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "fkModificacionUsuario"))
	private Usuario usuario;
	
	@NotNull(message = "La fecha y hora de la modificacion no puede ser nulo")
	@PastOrPresent(message = "La fecha y hora de la modificacion debe estar en tiempo pasado o presente")
	@Column(name = "fecha_hora", nullable = false)
	private LocalDateTime fechaHora;
	
	@Column(name = "valor_anterior", length = 50)
	private String valorAnterior;
	
	@Column(name = "valor_actual", length = 50)
	private String valorActual;
	
	@Column(name = "id_item")
	private Integer idItem;
	
	public Modificacion() {}

	/**
	 * @param idModificacion
	 * @param concepto
	 * @param tabla
	 * @param columna
	 * @param usuario
	 * @param fechaHora
	 * @param valorAnterior
	 * @param valorActual
	 * @param idItem
	 */
	public Modificacion(Integer idModificacion, Concepto concepto, String tabla, Usuario usuario, LocalDateTime fechaHora, String valorAnterior, 
			String valorActual, Integer idItem, String columna) {
		this.idModificacion = idModificacion;
		this.concepto = concepto;
		this.tabla = tabla;
		this.columna = columna;
		this.usuario = usuario;
		this.fechaHora = fechaHora;
		this.valorAnterior = valorAnterior;
		this.valorActual = valorActual;
		this.idItem = idItem;
	}

	/**
	 * @return the idModificacion
	 */
	public Integer getIdModificacion() {
		return idModificacion;
	}

	/**
	 * @param idModificacion the idModificacion to set
	 */
	public void setIdModificacion(Integer idModificacion) {
		this.idModificacion = idModificacion;
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
	 * @return the tabla
	 */
	public String getTabla() {
		return tabla;
	}

	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	/**
	 * @return the columna
	 */
	public String getColumna() {
		return columna;
	}

	/**
	 * @param columna the columna to set
	 */
	public void setColumna(String columna) {
		this.columna = columna;
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
	 * @return the valorAnterior
	 */
	public String getValorAnterior() {
		return valorAnterior;
	}

	/**
	 * @param valorAnterior the valorAnterior to set
	 */
	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	/**
	 * @return the valorActual
	 */
	public String getValorActual() {
		return valorActual;
	}

	/**
	 * @param valorActual the valorActual to set
	 */
	public void setValorActual(String valorActual) {
		this.valorActual = valorActual;
	}

	/**
	 * @return the idItem
	 */
	public Integer getIdItem() {
		return idItem;
	}

	/**
	 * @param idItem the idItem to set
	 */
	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}
	
}
