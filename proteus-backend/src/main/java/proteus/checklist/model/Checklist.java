package proteus.checklist.model;

import java.time.LocalDate;
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

import proteus.personal.model.Personal;
import proteus.servicio.model.Servicio;
import proteus.usuario.model.Usuario;

/**
 * Model for Table "checklists"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "checklists")
public class Checklist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idChecklist;

	@NotNull(message = "El tipo de servicio del checklist no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_checklist_servicio_tipo", nullable = false, foreignKey = @ForeignKey(name = "fkChecklistChecklistServicioTipo"))
	private ChecklistServicioTipo checklistServicioTipo;
	
	@NotNull(message = "El servicio no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_servicio", nullable = false, foreignKey = @ForeignKey(name = "fkChecklistServicio"))
	private Servicio servicio;
	
	@NotNull(message = "El mecanico no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_personal_mecanico", nullable = false, foreignKey = @ForeignKey(name = "fkChecklistPersonalMecanico"))
	private Personal mecanico;
	
	@NotNull(message = "El supervisor no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_personal_supervisor", nullable = false, foreignKey = @ForeignKey(name = "fkChecklistPersonalSupervisor"))
	private Personal supervisor;
	
	@NotNull(message = "El usuario no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_usuario_ingreso", nullable = false, foreignKey = @ForeignKey(name = "fkChecklistPersonalUsuario"))
	private Usuario usuarioIngreso;
	
	@PastOrPresent(message = "La fecha de ingreso del checklist debe estar en tiempo pasado o presente")
	@Column(name = "fecha_hora_ingreso", nullable = false)
	private LocalDateTime fechaHoraIngreso;
	
	@NotNull(message = "La fecha de revision del checklist no puede ser nulo")
	@PastOrPresent(message = "La fecha de revision del checklist debe estar en tiempo pasado o presente")
	@Column(name = "fecha_revision", nullable = false)
	private LocalDate fechaRevision;
	
	@Column(name = "no_orden_trabajo")
	private String noOrdenTrabajo;
	
	@Column(name = "observaciones")
	private String observaciones;
	
	@OneToMany(mappedBy = "checklist", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<ChecklistDetalle> checklistDetalle;
	
	public Checklist() {}

	/**
	 * @param idChecklist
	 * @param servicio
	 * @param mecanico
	 * @param supervisor
	 * @param usuarioIngreso
	 * @param fechaHoraIngreso
	 * @param fechaRevision
	 * @param noOrdenTrabajo
	 * @param observaciones
	 */
	public Checklist(Integer idChecklist, ChecklistServicioTipo checklistServicioTipo, Servicio servicio, Personal mecanico, Personal supervisor, Usuario usuarioIngreso,
			LocalDateTime fechaHoraIngreso, LocalDate fechaRevision, String noOrdenTrabajo, String observaciones) {
		this.idChecklist = idChecklist;
		this.checklistServicioTipo = checklistServicioTipo;
		this.servicio = servicio;
		this.mecanico = mecanico;
		this.supervisor = supervisor;
		this.usuarioIngreso = usuarioIngreso;
		this.fechaHoraIngreso = fechaHoraIngreso;
		this.fechaRevision = fechaRevision;
		this.noOrdenTrabajo = noOrdenTrabajo;
		this.observaciones = observaciones;
	}

	/**
	 * @return the idChecklist
	 */
	public Integer getIdChecklist() {
		return idChecklist;
	}

	/**
	 * @param idChecklist the idChecklist to set
	 */
	public void setIdChecklist(Integer idChecklist) {
		this.idChecklist = idChecklist;
	}

	/**
	 * @return the checklistServicioTipo
	 */
	public ChecklistServicioTipo getChecklistServicioTipo() {
		return checklistServicioTipo;
	}

	/**
	 * @param checklistServicioTipo the checklistServicioTipo to set
	 */
	public void setChecklistServicioTipo(ChecklistServicioTipo checklistServicioTipo) {
		this.checklistServicioTipo = checklistServicioTipo;
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
	 * @return the mecanico
	 */
	public Personal getMecanico() {
		return mecanico;
	}

	/**
	 * @param mecanico the mecanico to set
	 */
	public void setMecanico(Personal mecanico) {
		this.mecanico = mecanico;
	}

	/**
	 * @return the supervisor
	 */
	public Personal getSupervisor() {
		return supervisor;
	}

	/**
	 * @param supervisor the supervisor to set
	 */
	public void setSupervisor(Personal supervisor) {
		this.supervisor = supervisor;
	}

	/**
	 * @return the usuarioIngreso
	 */
	public Usuario getUsuarioIngreso() {
		return usuarioIngreso;
	}

	/**
	 * @param usuarioIngreso the usuarioIngreso to set
	 */
	public void setUsuarioIngreso(Usuario usuarioIngreso) {
		this.usuarioIngreso = usuarioIngreso;
	}

	/**
	 * @return the fechaHoraIngreso
	 */
	public LocalDateTime getFechaHoraIngreso() {
		return fechaHoraIngreso;
	}

	/**
	 * @param fechaHoraIngreso the fechaHoraIngreso to set
	 */
	public void setFechaHoraIngreso(LocalDateTime fechaHoraIngreso) {
		this.fechaHoraIngreso = fechaHoraIngreso;
	}

	/**
	 * @return the fechaRevision
	 */
	public LocalDate getFechaRevision() {
		return fechaRevision;
	}

	/**
	 * @param fechaRevision the fechaRevision to set
	 */
	public void setFechaRevision(LocalDate fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	/**
	 * @return the noOrdenTrabajo
	 */
	public String getNoOrdenTrabajo() {
		return noOrdenTrabajo;
	}

	/**
	 * @param noOrdenTrabajo the noOrdenTrabajo to set
	 */
	public void setNoOrdenTrabajo(String noOrdenTrabajo) {
		this.noOrdenTrabajo = noOrdenTrabajo;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the checklistDetalle
	 */
	public List<ChecklistDetalle> getChecklistDetalle() {
		return checklistDetalle;
	}

	/**
	 * @param checklistDetalle the checklistDetalle to set
	 */
	public void setChecklistDetalle(List<ChecklistDetalle> checklistDetalle) {
		this.checklistDetalle = checklistDetalle;
	}

}
