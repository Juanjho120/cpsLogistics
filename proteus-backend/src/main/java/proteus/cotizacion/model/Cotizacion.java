package proteus.cotizacion.model;

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

import proteus.segmento.model.Segmento;
import proteus.usuario.model.Usuario;

/**
 * Model for Table "cotizaciones"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "cotizaciones")
public class Cotizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCotizacion;
	
	@NotNull(message = "El usuario no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "fkCotizacionUsuario"))
	private Usuario usuario;
	
	@NotNull(message = "El segmento no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_segmento", nullable = false, foreignKey = @ForeignKey(name = "fkCotizacionSegmento"))
	private Segmento segmento;
	
	@PastOrPresent(message = "La fecha y hora de la cotizacion debe estar en tiempo pasado o presente")
	@Column(name = "fecha_hora", nullable = false)
	private LocalDateTime fechaHora;
	
	@PositiveOrZero(message = "El total de la cotizacion debe ser positivo o cero")
	@Column(name = "total", nullable = false)
	private Double total;
	
	@OneToMany(mappedBy = "cotizacion", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<CotizacionTrabajo> cotizacionTrabajo;
	
	@OneToMany(mappedBy = "cotizacion", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<CotizacionRepuesto> cotizacionRepuesto;
	
	public Cotizacion() {}

	/**
	 * @param idCotizacion
	 * @param usuario
	 * @param segmento
	 * @param fechaHora
	 * @param total
	 */
	public Cotizacion(Integer idCotizacion, Usuario usuario, Segmento segmento, LocalDateTime fechaHora, Double total) {
		this.idCotizacion = idCotizacion;
		this.usuario = usuario;
		this.segmento = segmento;
		this.fechaHora = fechaHora;
		this.total = total;
	}

	/**
	 * @return the idCotizacion
	 */
	public Integer getIdCotizacion() {
		return idCotizacion;
	}

	/**
	 * @param idCotizacion the idCotizacion to set
	 */
	public void setIdCotizacion(Integer idCotizacion) {
		this.idCotizacion = idCotizacion;
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
	 * @return the segmento
	 */
	public Segmento getSegmento() {
		return segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento(Segmento segmento) {
		this.segmento = segmento;
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
	 * @return the cotizacionTrabajo
	 */
	public List<CotizacionTrabajo> getCotizacionTrabajo() {
		return cotizacionTrabajo;
	}

	/**
	 * @param cotizacionTrabajo the cotizacionTrabajo to set
	 */
	public void setCotizacionTrabajo(List<CotizacionTrabajo> cotizacionTrabajo) {
		this.cotizacionTrabajo = cotizacionTrabajo;
	}

	/**
	 * @return the cotizacionRepuesto
	 */
	public List<CotizacionRepuesto> getCotizacionRepuesto() {
		return cotizacionRepuesto;
	}

	/**
	 * @param cotizacionRepuesto the cotizacionRepuesto to set
	 */
	public void setCotizacionRepuesto(List<CotizacionRepuesto> cotizacionRepuesto) {
		this.cotizacionRepuesto = cotizacionRepuesto;
	}
	
}
