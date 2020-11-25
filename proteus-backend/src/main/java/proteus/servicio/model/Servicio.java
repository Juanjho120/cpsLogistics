package proteus.servicio.model;

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

import proteus.cotizacion.model.Cotizacion;
import proteus.placa.model.Placa;
import proteus.segmento.model.Segmento;

/**
 * Model for Table "servicios"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "servicios")
public class Servicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServicio;
	
	@NotNull(message = "El segmento del servicio no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_segmento", nullable = false, foreignKey = @ForeignKey(name = "fkServicioSegmento"))
	private Segmento segmento;
	
	@NotNull(message = "La placa del servicio no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_placa", nullable = false, foreignKey = @ForeignKey(name = "fkServicioPlaca"))
	private Placa placa;
	
	@NotNull(message = "El tipo del servicio no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_servicio_tipo", nullable = false, foreignKey = @ForeignKey(name = "fkServicioServicioTipo"))
	private ServicioTipo servicioTipo;
	
	@NotNull(message = "El kilometraje recorrido del servicio no puede ser nulo")
	@PositiveOrZero(message = "El kilometraje recorrido del servicio debe ser positivo o cero")
	@Column(name = "kilometraje_recorrido", nullable = false)
	private Integer kilometrajeRecorrido;
	
	@NotNull(message = "El kilometraje del proximo servicio no puede ser nulo")
	@PositiveOrZero(message = "El kilometraje del proximo servicio debe ser positivo o cero")
	@Column(name = "kilometraje_proximo_servicio", nullable = false)
	private Integer kilometrajeProximoServicio;
	
	@ManyToOne
	@JoinColumn(name = "id_cotizacion", foreignKey = @ForeignKey(name = "fkServicioCotizacion"))
	private Cotizacion cotizacion;
	
	@OneToMany(mappedBy = "servicio", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<ServicioTrabajo> servicioTrabajo;
	
	@OneToMany(mappedBy = "servicio", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<ServicioRepuesto> servicioRepuesto;
	
	@PositiveOrZero(message = "El costo total del servicio debe ser positivo o cero")
	@Column(name = "costo_total")
	private Double costoTotal;
	
	@PastOrPresent(message = "La fecha y hora del servicio debe estar en tiempo pasado o presente")
	@Column(name = "fecha_hora", nullable = false)
	private LocalDateTime fechaHora;
	
	@Column(name = "finalizado", nullable = false)
	private Boolean finalizado;
	
	@Column(name = "facturado", nullable = false)
	private Boolean facturado;
	
	public Servicio() {}

	/**
	 * @param idServicio
	 * @param segmento
	 * @param placa
	 * @param servicioTipo
	 * @param kilometrajeRecorrido
	 * @param kilometrajeProximoServicio
	 * @param costoTotal
	 * @param fechaHora
	 * @param finalizado
	 * @param facturado
	 */
	public Servicio(Integer idServicio, Segmento segmento, Placa placa, ServicioTipo servicioTipo, Integer kilometrajeRecorrido, 
			Integer kilometrajeProximoServicio, Double costoTotal, LocalDateTime fechaHora, Boolean finalizado, Boolean facturado) {
		this.idServicio = idServicio;
		this.segmento = segmento;
		this.placa = placa;
		this.servicioTipo = servicioTipo;
		this.kilometrajeRecorrido = kilometrajeRecorrido;
		this.kilometrajeProximoServicio = kilometrajeProximoServicio;
		this.costoTotal = costoTotal;
		this.fechaHora = fechaHora;
		this.finalizado = finalizado;
		this.facturado = facturado;
	}

	/**
	 * @return the idServicio
	 */
	public Integer getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
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
	 * @return the placa
	 */
	public Placa getPlaca() {
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(Placa placa) {
		this.placa = placa;
	}

	/**
	 * @return the servicioTipo
	 */
	public ServicioTipo getServicioTipo() {
		return servicioTipo;
	}

	/**
	 * @param servicioTipo the servicioTipo to set
	 */
	public void setServicioTipo(ServicioTipo servicioTipo) {
		this.servicioTipo = servicioTipo;
	}

	/**
	 * @return the kilometrajeRecorrido
	 */
	public Integer getKilometrajeRecorrido() {
		return kilometrajeRecorrido;
	}

	/**
	 * @param kilometrajeRecorrido the kilometrajeRecorrido to set
	 */
	public void setKilometrajeRecorrido(Integer kilometrajeRecorrido) {
		this.kilometrajeRecorrido = kilometrajeRecorrido;
	}

	/**
	 * @return the kilometrajeProximoServicio
	 */
	public Integer getKilometrajeProximoServicio() {
		return kilometrajeProximoServicio;
	}

	/**
	 * @param kilometrajeProximoServicio the kilometrajeProximoServicio to set
	 */
	public void setKilometrajeProximoServicio(Integer kilometrajeProximoServicio) {
		this.kilometrajeProximoServicio = kilometrajeProximoServicio;
	}

	/**
	 * @return the cotizacion
	 */
	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	/**
	 * @param cotizacion the cotizacion to set
	 */
	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * @return the servicioTrabajo
	 */
	public List<ServicioTrabajo> getServicioTrabajo() {
		return servicioTrabajo;
	}

	/**
	 * @param servicioTrabajo the servicioTrabajo to set
	 */
	public void setServicioTrabajo(List<ServicioTrabajo> servicioTrabajo) {
		this.servicioTrabajo = servicioTrabajo;
	}

	/**
	 * @return the servicioRepuesto
	 */
	public List<ServicioRepuesto> getServicioRepuesto() {
		return servicioRepuesto;
	}

	/**
	 * @param servicioRepuesto the servicioRepuesto to set
	 */
	public void setServicioRepuesto(List<ServicioRepuesto> servicioRepuesto) {
		this.servicioRepuesto = servicioRepuesto;
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
	 * @return the finalizado
	 */
	public Boolean getFinalizado() {
		return finalizado;
	}

	/**
	 * @param finalizado the finalizado to set
	 */
	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

	/**
	 * @return the facturado
	 */
	public Boolean getFacturado() {
		return facturado;
	}

	/**
	 * @param facturado the facturado to set
	 */
	public void setFacturado(Boolean facturado) {
		this.facturado = facturado;
	}
	
}
