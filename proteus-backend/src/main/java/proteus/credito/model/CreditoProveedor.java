package proteus.credito.model;

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
import javax.validation.constraints.PositiveOrZero;

import proteus.proveedor.model.Proveedor;

/**
 * Model for Table "credito_proveedores"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "credito_proveedores")
public class CreditoProveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCreditoProveedor;
	
	@NotNull(message = "El proveedor no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_proveedor", nullable = false, foreignKey = @ForeignKey(name = "fkCreditoProveedorProveedor"))
	private Proveedor proveedor;
	
	@NotNull(message = "La deuda acumulativa no puede ser nulo")
	@PositiveOrZero(message = "La deuda acumulativa debe ser positivo o cero")
	@Column(name = "deuda_acumulativa", nullable = false)
	private Double deudaAcumulativa;
	
	public CreditoProveedor() {}
	
	/**
	 * @param idCreditoProveedor
	 * @param proveedor
	 * @param deudaAcumulativa
	 */
	public CreditoProveedor(Integer idCreditoProveedor, Proveedor proveedor, Double deudaAcumulativa) {
		this.idCreditoProveedor = idCreditoProveedor;
		this.proveedor = proveedor;
		this.deudaAcumulativa = deudaAcumulativa;
	}

	/**
	 * @return the idCreditoProveedor
	 */
	public Integer getIdCreditoProveedor() {
		return idCreditoProveedor;
	}

	/**
	 * @param idCreditoProveedor the idCreditoProveedor to set
	 */
	public void setIdCreditoProveedor(Integer idCreditoProveedor) {
		this.idCreditoProveedor = idCreditoProveedor;
	}

	/**
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}

	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return the deudaAcumulativa
	 */
	public Double getDeudaAcumulativa() {
		return deudaAcumulativa;
	}

	/**
	 * @param deudaAcumulativa the deudaAcumulativa to set
	 */
	public void setDeudaAcumulativa(Double deudaAcumulativa) {
		this.deudaAcumulativa = deudaAcumulativa;
	}
	
}
