package proteus.saldo.model;

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
 * Model for Table "saldos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "saldos")
public class Saldo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSaldo;
	
	@NotNull(message = "El nombre del saldo no puede ser nulo")
	@Size(min = 3, message = "El nombre del saldo debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 60)
	private String nombre;
	
	@NotNull(message = "El monto del saldo no puede ser nulo")
	@PositiveOrZero(message = "El monto del saldo debe ser positivo o cero")
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	public Saldo() {}

	/**
	 * @param idSaldo
	 * @param nombre
	 * @param monto
	 */
	public Saldo(Integer idSaldo, String nombre, Double monto) {
		this.idSaldo = idSaldo;
		this.nombre = nombre;
		this.monto = monto;
	}

	/**
	 * @return the idSaldo
	 */
	public Integer getIdSaldo() {
		return idSaldo;
	}

	/**
	 * @param idSaldo the idSaldo to set
	 */
	public void setIdSaldo(Integer idSaldo) {
		this.idSaldo = idSaldo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
}
