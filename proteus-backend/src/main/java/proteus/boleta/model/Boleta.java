package proteus.boleta.model;

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

import proteus.cheque.model.Cheque;
import proteus.cuenta.model.CuentaBancaria;

/**
 * Model for Table "boletas"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "boletas")
public class Boleta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idBoleta;
	
	@NotNull(message = "El numero de la boleta no puede ser nulo")
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@NotNull(message = "El tipo de documento no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_boleta_tipo_documento", nullable = false, foreignKey = @ForeignKey(name = "fkBoletaBoletaTipoDocumento"))
	private BoletaTipoDocumento boletaTipoDocumento;
	
	@NotNull(message = "El tipo de documento no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_cuenta_bancaria", nullable = false, foreignKey = @ForeignKey(name = "fkBoletaCuentaBancaria"))
	private CuentaBancaria cuentaBancaria;
	
	@NotNull(message = "La fecha de la boleta no puede ser nulo")
	@PastOrPresent(message = "La fecha de la boleta debe estar en tiempo pasado o presente")
	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;
	
	@PositiveOrZero(message = "El monto de la boleta debe ser positivo o cero")
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	@ManyToOne
	@JoinColumn(name = "id_cheque", foreignKey = @ForeignKey(name = "fkBoletaCheque"))
	private Cheque cheque;
	
	public Boleta() {}

	/**
	 * @param idBoleta
	 * @param numero
	 * @param boletaTipoDocumento
	 * @param cuentaBancaria
	 * @param fecha
	 * @param monto
	 */
	public Boleta(Integer idBoleta, String numero, BoletaTipoDocumento boletaTipoDocumento, CuentaBancaria cuentaBancaria, LocalDate fecha,
			Double monto, Cheque cheque) {
		this.idBoleta = idBoleta;
		this.numero = numero;
		this.boletaTipoDocumento = boletaTipoDocumento;
		this.cuentaBancaria = cuentaBancaria;
		this.fecha = fecha;
		this.monto = monto;
		this.cheque = cheque;
	}

	/**
	 * @return the idBoleta
	 */
	public Integer getIdBoleta() {
		return idBoleta;
	}

	/**
	 * @param idBoleta the idBoleta to set
	 */
	public void setIdBoleta(Integer idBoleta) {
		this.idBoleta = idBoleta;
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
	 * @return the boletaTipoDocumento
	 */
	public BoletaTipoDocumento getBoletaTipoDocumento() {
		return boletaTipoDocumento;
	}

	/**
	 * @param boletaTipoDocumento the boletaTipoDocumento to set
	 */
	public void setBoletaTipoDocumento(BoletaTipoDocumento boletaTipoDocumento) {
		this.boletaTipoDocumento = boletaTipoDocumento;
	}

	/**
	 * @return the cuentaBancaria
	 */
	public CuentaBancaria getCuentaBancaria() {
		return cuentaBancaria;
	}

	/**
	 * @param cuentaBancaria the cuentaBancaria to set
	 */
	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	/**
	 * @return the fecha
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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
	 * @return the cheque
	 */
	public Cheque getCheque() {
		return cheque;
	}

	/**
	 * @param cheque the cheque to set
	 */
	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}
	
}
