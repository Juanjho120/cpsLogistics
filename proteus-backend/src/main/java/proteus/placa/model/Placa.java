package proteus.placa.model;

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

import proteus.marca.model.MarcaAuto;

/**
 * Model for Table "placas"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "placas")
public class Placa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPlaca;
	
	@NotNull(message = "La marca de la placa del auto no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_marca_auto", nullable = false, foreignKey = @ForeignKey(name = "fkPlacaMarcaAuto"))
	private MarcaAuto marcaAuto;
	
	@NotNull(message = "El numero de placa no puede ser nulo")
	@Size(min = 7, message = "El numero de placa debe tener por lo menos 7 caracteres")
	@Column(name = "numero", nullable = false, length = 30)
	private String numero;
	
	@NotNull(message = "La fecha del ultimo servicio no puede ser nulo")
	@PastOrPresent(message = "La fecha del ultimo servicio debe estar en tiempo pasado o presente")
	@Column(name = "fecha_ultimo_servicio", nullable = false)
	private LocalDate fechaUltimoServicio;
	
	@NotNull(message = "El ultimo kilometraje del auto no puede ser nulo")
	@PositiveOrZero(message = "El ultimo kilometraje del auto debe ser positivo o cero")
	@Column(name = "ultimo_kilometraje", nullable = false)
	private Integer ultimoKilometraje;
	
	public Placa() {}

	/**
	 * @param idPlaca
	 * @param marcaAuto
	 * @param numero
	 * @param fechaUltimoServicio
	 * @param ultimoKilometraje
	 */
	public Placa(Integer idPlaca, MarcaAuto marcaAuto, String numero, LocalDate fechaUltimoServicio, Integer ultimoKilometraje) {
		this.idPlaca = idPlaca;
		this.marcaAuto = marcaAuto;
		this.numero = numero;
		this.fechaUltimoServicio = fechaUltimoServicio;
		this.ultimoKilometraje = ultimoKilometraje;
	}

	/**
	 * @return the idPlaca
	 */
	public Integer getIdPlaca() {
		return idPlaca;
	}

	/**
	 * @param idPlaca the idPlaca to set
	 */
	public void setIdPlaca(Integer idPlaca) {
		this.idPlaca = idPlaca;
	}

	/**
	 * @return the marcaAuto
	 */
	public MarcaAuto getMarcaAuto() {
		return marcaAuto;
	}

	/**
	 * @param marcaAuto the marcaAuto to set
	 */
	public void setMarcaAuto(MarcaAuto marcaAuto) {
		this.marcaAuto = marcaAuto;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the fechaUltimoServicio
	 */
	public LocalDate getFechaUltimoServicio() {
		return fechaUltimoServicio;
	}

	/**
	 * @param fechaUltimoServicio the fechaUltimoServicio to set
	 */
	public void setFechaUltimoServicio(LocalDate fechaUltimoServicio) {
		this.fechaUltimoServicio = fechaUltimoServicio;
	}

	/**
	 * @return the ultimoKilometraje
	 */
	public Integer getUltimoKilometraje() {
		return ultimoKilometraje;
	}

	/**
	 * @param ultimoKilometraje the ultimoKilometraje to set
	 */
	public void setUltimoKilometraje(Integer ultimoKilometraje) {
		this.ultimoKilometraje = ultimoKilometraje;
	}
	
}
