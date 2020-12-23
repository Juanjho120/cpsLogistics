package proteus.inventario.dto;

public class ProductoEntradaSalidaDTO {

	private String inventarios;
	private Integer entrada;
	private Integer salida;
	private String razon;
	private String fecha;
	
	public ProductoEntradaSalidaDTO() {
		this.inventarios = "";
		this.entrada = 0;
		this.salida = 0;
		this.razon = "";
		this.fecha = "";
	}

	/**
	 * @param inventarios
	 * @param entrada
	 * @param salida
	 * @param razon
	 * @param fecha
	 */
	public ProductoEntradaSalidaDTO(String inventarios, Integer entrada, Integer salida, String razon, String fecha) {
		this.inventarios = inventarios;
		this.entrada = entrada;
		this.salida = salida;
		this.razon = razon;
		this.fecha = fecha;
	}

	/**
	 * @return the inventarios
	 */
	public String getInventarios() {
		return inventarios;
	}

	/**
	 * @param inventarios the inventarios to set
	 */
	public void setInventarios(String inventarios) {
		this.inventarios = inventarios;
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
	 * @return the razon
	 */
	public String getRazon() {
		return razon;
	}

	/**
	 * @param razon the razon to set
	 */
	public void setRazon(String razon) {
		this.razon = razon;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
