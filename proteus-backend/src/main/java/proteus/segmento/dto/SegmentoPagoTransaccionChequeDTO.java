package proteus.segmento.dto;

import proteus.cheque.model.Cheque;
import proteus.segmento.model.SegmentoPago;
import proteus.transaccion.model.Transaccion;

/**
 * DTO para guardar un pago junto con su documento
 * 
 * @author Juan Tzun
 */
public class SegmentoPagoTransaccionChequeDTO {

	private SegmentoPago segmentoPago;
	private Transaccion transaccion;
	private Cheque cheque;
	
	public SegmentoPagoTransaccionChequeDTO() {}

	/**
	 * @param segmentoPago
	 * @param transaccion
	 * @param cheque
	 */
	public SegmentoPagoTransaccionChequeDTO(SegmentoPago segmentoPago, Transaccion transaccion, Cheque cheque) {
		this.segmentoPago = segmentoPago;
		this.transaccion = transaccion;
		this.cheque = cheque;
	}

	/**
	 * @return the segmentoPago
	 */
	public SegmentoPago getSegmentoPago() {
		return segmentoPago;
	}

	/**
	 * @param segmentoPago the segmentoPago to set
	 */
	public void setSegmentoPago(SegmentoPago segmentoPago) {
		this.segmentoPago = segmentoPago;
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
