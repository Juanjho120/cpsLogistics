package proteus.inventario.dto;

/**
 * DTO para agrupar repuestos por entradas y salidas del inventario
 * 
 * @author Juan Tzun
 */
public class InventarioEntradaSalidaDTO {

	private Integer idRepuesto;
	private String codigo;
	private String descripcion;
	private Integer entrada;
	private Integer salida;
	private Integer existencia;
	
	public InventarioEntradaSalidaDTO() {}

	/**
	 * @param idRepuesto
	 * @param codigo
	 * @param descripcion
	 * @param entrada
	 * @param salida
	 * @param existencia
	 */
	public InventarioEntradaSalidaDTO(Integer idRepuesto, String codigo, String descripcion, Integer entrada, Integer salida,
			Integer existencia) {
		this.idRepuesto = idRepuesto;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.entrada = entrada;
		this.salida = salida;
		this.existencia = existencia;
	}

	/**
	 * @return the idRepuesto
	 */
	public Integer getIdRepuesto() {
		return idRepuesto;
	}

	/**
	 * @param idRepuesto the idRepuesto to set
	 */
	public void setIdRepuesto(Integer idRepuesto) {
		this.idRepuesto = idRepuesto;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the entrada
	 */
	public Integer getEntrada() {
		return entrada;
	}

	/**
	 * @param entrada the entrada to set
	 */
	public void setEntrada(Integer entrada) {
		this.entrada = entrada;
	}

	/**
	 * @return the salida
	 */
	public Integer getSalida() {
		return salida;
	}

	/**
	 * @param salida the salida to set
	 */
	public void setSalida(Integer salida) {
		this.salida = salida;
	}

	/**
	 * @return the existencia
	 */
	public Integer getExistencia() {
		return existencia;
	}

	/**
	 * @param existencia the existencia to set
	 */
	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}

}
