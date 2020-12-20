package proteus.segmento.model;

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

/**
 * Model for Table "segmento_creditos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "segmento_creditos")
public class SegmentoCredito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSegmentoCredito;
	
	@NotNull(message = "El segmento no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_segmento", nullable = false, foreignKey = @ForeignKey(name = "fkSegmentoCreditoSegmento"))
	private Segmento segmento;
	
	@NotNull(message = "El credito del segmento no puede ser nulo")
	@PositiveOrZero(message = "El credigo del segmento debe ser positivo o cero")
	@Column(name = "credito", nullable = false)
	private Double credito;
	
	@NotNull(message = "La fecha de la ultima transaccion del segmento no puede ser nulo")
	@PastOrPresent(message = "La fecha de la ultima transaccion del segmento debe estar en tiempo pasado o presente")
	@Column(name = "ultima_transaccion", nullable = false)
	private LocalDate ultimaTransaccion;
	
	public SegmentoCredito() {}

	/**
	 * @param idSegmentoCredito
	 * @param segmento
	 * @param credito
	 */
	public SegmentoCredito(Integer idSegmentoCredito, Segmento segmento, Double credito, LocalDate ultimaTransaccion) {
		this.idSegmentoCredito = idSegmentoCredito;
		this.segmento = segmento;
		this.credito = credito;
		this.ultimaTransaccion = ultimaTransaccion;
	}

	/**
	 * @return the idSegmentoCredito
	 */
	public Integer getIdSegmentoCredito() {
		return idSegmentoCredito;
	}

	/**
	 * @param idSegmentoCredito the idSegmentoCredito to set
	 */
	public void setIdSegmentoCredito(Integer idSegmentoCredito) {
		this.idSegmentoCredito = idSegmentoCredito;
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
	 * @return the credito
	 */
	public Double getCredito() {
		return credito;
	}

	/**
	 * @param credito the credito to set
	 */
	public void setCredito(Double credito) {
		this.credito = credito;
	}
	
	/**
	 * @return the ultimaTransaccion
	 */
	public LocalDate getUltimaTransaccion() {
		return ultimaTransaccion;
	}

	/**
	 * @param ultimaTransaccion the ultimaTransaccion to set
	 */
	public void setUltimaTransaccion(LocalDate ultimaTransaccion) {
		this.ultimaTransaccion = ultimaTransaccion;
	}
	
}
