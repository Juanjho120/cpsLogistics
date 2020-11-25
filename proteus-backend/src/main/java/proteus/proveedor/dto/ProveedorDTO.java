package proteus.proveedor.dto;

import java.util.List;

import proteus.proveedor.model.Proveedor;
import proteus.proveedor.model.ProveedorAsesor;

/**
 * DTO para agrupar el proveedor con sus asesores
 * @author Juan Tzun
 *
 */
public class ProveedorDTO {
	
	private Proveedor proveedor;
	private List<ProveedorAsesor> proveedorAsesor;
	
	public ProveedorDTO() {}

	/**
	 * @param proveedor
	 * @param proveedorAsesor
	 */
	public ProveedorDTO(Proveedor proveedor, List<ProveedorAsesor> proveedorAsesor) {
		this.proveedor = proveedor;
		this.proveedorAsesor = proveedorAsesor;
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
	 * @return the proveedorAsesor
	 */
	public List<ProveedorAsesor> getProveedorAsesor() {
		return proveedorAsesor;
	}

	/**
	 * @param proveedorAsesor the proveedorAsesor to set
	 */
	public void setProveedorAsesor(List<ProveedorAsesor> proveedorAsesor) {
		this.proveedorAsesor = proveedorAsesor;
	}

}
