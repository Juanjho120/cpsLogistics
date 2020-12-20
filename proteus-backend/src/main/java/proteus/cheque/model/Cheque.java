package proteus.cheque.model;

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

import proteus.cuenta.model.CuentaBancaria;

/**
 * Model for Table "cheques"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "cheques")
public class Cheque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCheque;
	
	@NotNull(message = "La cuenta bancaria no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_cuenta_bancaria", nullable = false, foreignKey = @ForeignKey(name = "fkChequeCuentaBancaria"))
	private CuentaBancaria cuentaBancaria;
	
	@NotNull(message = "El numero del cheque no puede ser nulo")
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@PositiveOrZero(message = "El monto del cheque debe ser positivo o cero")
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	@NotNull(message = "La fecha de emision del cheque no puede ser nulo")
	@PastOrPresent(message = "La fecha de emision del cheque debe estar en tiempo pasado o presente")
	@Column(name = "fecha_emision", nullable = false)
	private LocalDate fechaEmision;
	
	@PastOrPresent(message = "La fecha de deposito del cheque debe estar en tiempo pasado o presente")
	@Column(name = "fecha_deposito")
	private LocalDate fechaDeposito;
	
	public Cheque() {}

	/**
	 * @param idCheque
	 * @param cuentaBancaria
	 * @param numero
	 * @param monto
	 * @param fechaEmision
	 * @param fechaDeposito
	 */
	public Cheque(Integer idCheque, CuentaBancaria cuentaBancaria, String numero, Double monto, LocalDate fechaEmision, LocalDate fechaDeposito) {
		this.idCheque = idCheque;
		this.cuentaBancaria = cuentaBancaria;
		this.numero = numero;
		this.monto = monto;
		this.fechaEmision = fechaEmision;
		this.fechaDeposito = fechaDeposito;
	}

	/**
	 * @return the idCheque
	 */
	public Integer getIdCheque() {
		return idCheque;
	}

	/**
	 * @param idCheque the idCheque to set
	 */
	public void setIdCheque(Integer idCheque) {
		this.idCheque = idCheque;
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
	 * @return the fechaEmision
	 */
	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @return the fechaDeposito
	 */
	public LocalDate getFechaDeposito() {
		return fechaDeposito;
	}

	/**
	 * @param fechaDeposito the fechaDeposito to set
	 */
	public void setFechaDeposito(LocalDate fechaDeposito) {
		this.fechaDeposito = fechaDeposito;
	}
	
}
