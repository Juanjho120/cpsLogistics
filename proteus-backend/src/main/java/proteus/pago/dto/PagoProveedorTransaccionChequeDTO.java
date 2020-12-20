package proteus.pago.dto;

import proteus.pago.model.PagoProveedor;
import proteus.transaccion.model.Transaccion;
import proteus.cheque.model.Cheque;

public class PagoProveedorTransaccionChequeDTO {

	private PagoProveedor pagoProveedor;
	private Transaccion transaccion;
	private Cheque cheque;
	
	public PagoProveedorTransaccionChequeDTO() {}

	/**
	 * @param pagoProveedor
	 * @param transaccion
	 * @param cheque
	 */
	public PagoProveedorTransaccionChequeDTO(PagoProveedor pagoProveedor, Transaccion transaccion, Cheque cheque) {
		this.pagoProveedor = pagoProveedor;
		this.transaccion = transaccion;
		this.cheque = cheque;
	}

	/**
	 * @return the pagoProveedor
	 */
	public PagoProveedor getPagoProveedor() {
		return pagoProveedor;
	}

	/**
	 * @param pagoProveedor the pagoProveedor to set
	 */
	public void setPagoProveedor(PagoProveedor pagoProveedor) {
		this.pagoProveedor = pagoProveedor;
	}

	/**
	 * @return the transaccion
	 */
	public Transaccion getTransaccion() {
		return transaccion;
	}

	/**
	 * @param transaccion the transaccion to set
	 */
	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
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
