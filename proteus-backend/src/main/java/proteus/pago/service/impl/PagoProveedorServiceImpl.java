package proteus.pago.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.cheque.model.Cheque;
import proteus.cheque.service.IChequeService;
import proteus.credito.model.CreditoProveedorDetalle;
import proteus.credito.service.ICreditoProveedorDetalleService;
import proteus.credito.service.ICreditoProveedorService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.pago.dto.PagoProveedorTransaccionChequeDTO;
import proteus.pago.model.PagoProveedor;
import proteus.pago.model.PagoProveedorDetalle;
import proteus.pago.repository.IPagoProveedorRepository;
import proteus.pago.service.IPagoProveedorService;
import proteus.pago.service.IPagoTipoService;
import proteus.transaccion.model.Transaccion;
import proteus.transaccion.service.ITransaccionService;

/**
 * Services for PagoProveedor Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class PagoProveedorServiceImpl extends CRUDImpl<PagoProveedor, Integer> implements IPagoProveedorService {

	@Autowired
	private IPagoProveedorRepository pagoProveedorRepository;
	
	@Autowired
	private ICreditoProveedorDetalleService creditoProveedorDetalleService;
	
	@Autowired
	private ICreditoProveedorService creditoProveedorService;
	
	@Autowired
	private IPagoTipoService pagoTipoService;
	
	@Autowired
	private ITransaccionService transaccionService;
	
	@Autowired
	private IChequeService chequeService;

	@Override
	protected IGenericRepository<PagoProveedor, Integer> getRepository() {
		return pagoProveedorRepository;
	}
	
	@Override
	public PagoProveedor create(PagoProveedor pagoProveedor) throws Exception {
		for(PagoProveedorDetalle pagoProveedorDetalle : pagoProveedor.getPagoProveedorDetalle()) {
			CreditoProveedorDetalle creditoProveedorDetalle = creditoProveedorDetalleService.getById(pagoProveedorDetalle.getCreditoProveedorDetalle().getIdCreditoProveedorDetalle());
			creditoProveedorService.updateDeudaAcumulativa(creditoProveedorDetalle.getCreditoProveedor().getIdCreditoProveedor(), pagoProveedor.getMonto(), false);
		}
		
		pagoProveedor.setFechaHoraPago(LocalDateTime.now());
		return pagoProveedorRepository.save(pagoProveedor);
	}
	
	@Override
	public void delete(Integer idPagoProveedor) throws Exception {
		//Se busca el antiguo
		PagoProveedor pagoProveedor = this.getById(idPagoProveedor);
		
		if(pagoProveedor.getPagoTipo().getNombre().equalsIgnoreCase("CHEQUE")) {
			Cheque cheque = chequeService.getById(pagoProveedor.getIdItem());
			if(cheque!=null) {
				chequeService.delete(cheque.getIdCheque());
			}
		} else if(pagoProveedor.getPagoTipo().getNombre().equalsIgnoreCase("TRANSACCION")) {
			Transaccion transaccion = transaccionService.getById(pagoProveedor.getIdItem());
			if(transaccion!=null) {
				transaccionService.delete(transaccion.getIdTransaccion());
			}
		}
		
		Integer idCreditoProveedor = 0;
		//SETEANDO A FALSE EL ESTADO PAGADO DE CADA FACTURA EN SU RESPECTIVO DETALLE DE CREDITO Y ACTUALIZANDO LA DEUDA ACUMULATIVA
		for(PagoProveedorDetalle pagoProveedorDetalle : pagoProveedor.getPagoProveedorDetalle()) {
			CreditoProveedorDetalle creditoProveedorDetalle = creditoProveedorDetalleService.getById(pagoProveedorDetalle.getCreditoProveedorDetalle().getIdCreditoProveedorDetalle());
			if(creditoProveedorDetalle.getPagada()) {
				creditoProveedorDetalle.setPagada(false);
				creditoProveedorDetalleService.update(creditoProveedorDetalle);
				idCreditoProveedor = creditoProveedorDetalle.getCreditoProveedor().getIdCreditoProveedor();
			}
		}
		
		//Antes de eliminar, se devuelve el monto restado a la deuda
		creditoProveedorService.updateDeudaAcumulativa(idCreditoProveedor, pagoProveedor.getMonto(), true);
		
		pagoProveedorRepository.deleteById(idPagoProveedor);
	}

	@Override
	public List<PagoProveedor> getByProveedor(Integer idProveedor) throws Exception {
		return pagoProveedorRepository.findByProveedor(idProveedor);
	}

	@Override
	public List<PagoProveedor> getByFechaPago(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return pagoProveedorRepository.findByFechaPago(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<PagoProveedor> getByCreditoProveedor(Integer idCreditoProveedor) throws Exception {
		return pagoProveedorRepository.findByCreditoProveedor(idCreditoProveedor);
	}
	
	@Override
	public void createDTO(PagoProveedorTransaccionChequeDTO pagoProveedorDto) throws Exception {
		PagoProveedor pagoProveedor = pagoProveedorDto.getPagoProveedor();
		pagoProveedor.setPagoTipo(pagoTipoService.getById(pagoProveedor.getPagoTipo().getIdPagoTipo()));
		
		//EXTRAYENDO EL MONTO TOTAL A PAGAR
		Double montoTotal = 0.0;
		for(PagoProveedorDetalle pagoProveedorDetalle : pagoProveedor.getPagoProveedorDetalle()) {
			pagoProveedorDetalle.setPagoProveedor(pagoProveedor);
			CreditoProveedorDetalle creditoProveedorDetalle = creditoProveedorDetalleService.getById(pagoProveedorDetalle.getCreditoProveedorDetalle().getIdCreditoProveedorDetalle());
			if(!creditoProveedorDetalle.getPagada()) {
				montoTotal += creditoProveedorDetalle.getFacturaCompra().getTotal();
			}
		}
		
		//SI EL MONTO TOTAL A PAGAR ES IGUAL A LO QUE SE DEBE PAGAR
		if(Double.compare(montoTotal, pagoProveedor.getMonto())==0) {
			if(pagoProveedor.getPagoTipo().getNombre().equalsIgnoreCase("CHEQUE")) {
				Cheque cheque = pagoProveedorDto.getCheque();
				chequeService.create(cheque);
				pagoProveedor.setIdItem(cheque.getIdCheque());
			} else if(pagoProveedor.getPagoTipo().getNombre().equalsIgnoreCase("TRANSACCION")) {
				Transaccion transaccion = pagoProveedorDto.getTransaccion();
				transaccionService.create(transaccion);
				pagoProveedor.setIdItem(transaccion.getIdTransaccion());
			} else if(pagoProveedor.getPagoTipo().getNombre().equalsIgnoreCase("EFECTIVO")) {
				pagoProveedor.setIdItem(0);
			}
			
			Integer idCreditoProveedor = 0;
			//SETEANDO A TRUE EL ESTADO PAGADO DE CADA FACTURA EN SU RESPECTIVO DETALLE DE CREDITO Y ACTUALIZANDO LA DEUDA ACUMULATIVA
			for(PagoProveedorDetalle pagoProveedorDetalle : pagoProveedor.getPagoProveedorDetalle()) {
				pagoProveedorDetalle.setPagoProveedor(pagoProveedor);
				CreditoProveedorDetalle creditoProveedorDetalle = creditoProveedorDetalleService.getById(pagoProveedorDetalle.getCreditoProveedorDetalle().getIdCreditoProveedorDetalle());
				if(!creditoProveedorDetalle.getPagada()) {
					creditoProveedorDetalle.setPagada(true);
					creditoProveedorDetalleService.update(creditoProveedorDetalle);
					idCreditoProveedor = creditoProveedorDetalle.getCreditoProveedor().getIdCreditoProveedor();
				}
			}
			creditoProveedorService.updateDeudaAcumulativa(idCreditoProveedor, pagoProveedor.getMonto(), false);
			
			pagoProveedor.setFechaHoraPago(LocalDateTime.now());
			pagoProveedorRepository.save(pagoProveedor);
			
		} else {
			throw new Exception("El monto a pagar excede el monto disponible en el documento");
		}
		
	}
	
}