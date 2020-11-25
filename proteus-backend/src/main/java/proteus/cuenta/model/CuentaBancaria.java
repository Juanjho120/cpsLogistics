package proteus.cuenta.model;

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
import javax.validation.constraints.Size;

import proteus.banco.model.Banco;
import proteus.categoria.model.Categoria;
import proteus.moneda.model.Moneda;

/**
 * Model for Table "cuentas_bancarias"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCuentaBancaria;
	
	@NotNull(message = "La categoria de la cuenta bancaria no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_categoria", nullable = false, foreignKey = @ForeignKey(name = "fkCuentaBancariaCategoria"))
	private Categoria categoria;
	
	@Column(name = "id_item")
	private Integer idItem;
	
	@NotNull(message = "El banco de la cuenta bancaria no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_banco", nullable = false, foreignKey = @ForeignKey(name = "fkCuentaBancariaBanco"))
	private Banco banco;
	
	@NotNull(message = "El tipo de la cuenta bancaria no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_cuenta_bancaria_tipo", nullable = false, foreignKey = @ForeignKey(name = "fkCuentaBancariaCuentaBancariaTipo"))
	private CuentaBancariaTipo cuentaBancariaTipo;
	
	@NotNull(message = "El tipo de moneda de la cuenta bancaria no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_moneda", nullable = false, foreignKey = @ForeignKey(name = "fkCuentaBancariaMoneda"))
	private Moneda moneda;
	
	@NotNull(message = "El numero de la cuenta bancaria no puede ser nulo")
	@Size(min = 7, message = "El numero de cuenta bancaria debe tener por lo menos 7 digitos")
	@Column(name = "numero", nullable = false, length = 30)
	private String numero;
	
	@NotNull(message = "El nombre la cuenta bancaria no puede ser nulo")
	@Size(min = 3, message = "El nombre de la cuenta bancaria debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	public CuentaBancaria() {}

	/**
	 * @param idCuentaBancaria
	 * @param proveedor
	 * @param idItem
	 * @param banco
	 * @param cuentaBancariaTipo
	 * @param moneda
	 * @param numero
	 * @param nombre
	 */
	public CuentaBancaria(Integer idCuentaBancaria, Categoria categoria, Integer idItem, Banco banco, CuentaBancariaTipo cuentaBancariaTipo, 
			Moneda moneda, String numero, String nombre) {
		this.idCuentaBancaria = idCuentaBancaria;
		this.categoria = categoria;
		this.idItem = idItem;
		this.banco = banco;
		this.cuentaBancariaTipo = cuentaBancariaTipo;
		this.moneda = moneda;
		this.numero = numero;
		this.nombre = nombre;
	}

	/**
	 * @return the idCuentaBancaria
	 */
	public Integer getIdCuentaBancaria() {
		return idCuentaBancaria;
	}

	/**
	 * @param idCuentaBancaria the idCuentaBancaria to set
	 */
	public void setIdCuentaBancaria(Integer idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	/**
	 * @return the banco
	 */
	public Banco getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	/**
	 * @return the cuentaBancariaTipo
	 */
	public CuentaBancariaTipo getCuentaBancariaTipo() {
		return cuentaBancariaTipo;
	}

	/**
	 * @param cuentaBancariaTipo the cuentaBancariaTipo to set
	 */
	public void setCuentaBancariaTipo(CuentaBancariaTipo cuentaBancariaTipo) {
		this.cuentaBancariaTipo = cuentaBancariaTipo;
	}

	/**
	 * @return the moneda
	 */
	public Moneda getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
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
	
}
