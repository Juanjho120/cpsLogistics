package proteus.transaccion.model;

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
 * Model for Table "transacciones"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "transacciones")
public class Transaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTransaccion;
	
	@NotNull(message = "La cuenta bancaria no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_cuenta_bancaria_destino", nullable = false, foreignKey = @ForeignKey(name = "fkTransaccionCuentaBancaria"))
	private CuentaBancaria cuentaBancariaDestino;
	
	@NotNull(message = "La referencia de la transaccion no puede ser nulo")
	@Column(name = "referencia", nullable = false)
	private String referencia;
	
	@NotNull(message = "La fecha procesamiento de la transaccion no puede ser nulo")
	@PastOrPresent(message = "La fecha procesamiento de la transaccion debe estar en tiempo pasado o presente")
	@Column(name = "fecha_procesamiento", nullable = false)
	private LocalDate fechaProcesamiento;
	
	@NotNull(message = "La fecha de aprobacion de la transaccion no puede ser nulo")
	@PastOrPresent(message = "La fecha de aprobacion de la transaccion debe estar en tiempo pasado o presente")
	@Column(name = "fecha_aprobacion", nullable = false)
	private LocalDate fechaAprobacion;
	
	@PositiveOrZero(message = "El monto de la transaccion debe ser positivo o cero")
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	@NotNull(message = "El estado de la transaccion no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_transaccion_estado", nullable = false, foreignKey = @ForeignKey(name = "fkTransaccionTransaccionEstado"))
	private TransaccionEstado transaccionEstado;
	
	public Transaccion() {}

	/**
	 * @param idTransaccion
	 * @param cuentaBancariaDestino
	 * @param referencia
	 * @param fechaProcesamiento
	 * @param fechaAprobacion
	 * @param monto
	 * @param transaccionEstado
	 */
	public Transaccion(Integer idTransaccion, CuentaBancaria cuentaBancariaDestino, String referencia, LocalDate fechaProcesamiento, LocalDate fechaAprobacion, 
			Double monto, TransaccionEstado transaccionEstado) {
		this.idTransaccion = idTransaccion;
		this.cuentaBancariaDestino = cuentaBancariaDestino;
		this.referencia = referencia;
		this.fechaProcesamiento = fechaProcesamiento;
		this.fechaAprobacion = fechaAprobacion;
		this.monto = monto;
		this.transaccionEstado = transaccionEstado;
	}

	/**
	 * @return the idTransaccion
	 */
	public Integer getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(Integer idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * @return the cuentaBancariaDestino
	 */
	public CuentaBancaria getCuentaBancariaDestino() {
		return cuentaBancariaDestino;
	}

	/**
	 * @param cuentaBancariaDestino the cuentaBancariaDestino to set
	 */
	public void setCuentaBancariaDestino(CuentaBancaria cuentaBancariaDestino) {
		this.cuentaBancariaDestino = cuentaBancariaDestino;
	}

	/**
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * @param referencia the referencia to set
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the fechaProcesamiento
	 */
	public LocalDate getFechaProcesamiento() {
		return fechaProcesamiento;
	}

	/**
	 * @param fechaProcesamiento the fechaProcesamiento to set
	 */
	public void setFechaProcesamiento(LocalDate fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}

	/**
	 * @return the fechaAprobacion
	 */
	public LocalDate getFechaAprobacion() {
		return fechaAprobacion;
	}

	/**
	 * @param fechaAprobacion the fechaAprobacion to set
	 */
	public void setFechaAprobacion(LocalDate fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
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
	 * @return the transaccionEstado
	 */
	public TransaccionEstado getTransaccionEstado() {
		return transaccionEstado;
	}

	/**
	 * @param transaccionEstado the transaccionEstado to set
	 */
	public void setTransaccionEstado(TransaccionEstado transaccionEstado) {
		this.transaccionEstado = transaccionEstado;
	}
	
}
