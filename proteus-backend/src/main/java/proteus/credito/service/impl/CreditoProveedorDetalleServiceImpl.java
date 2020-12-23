package proteus.credito.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.credito.dto.FacturaProveedorDTO;
import proteus.credito.model.CreditoProveedor;
import proteus.credito.model.CreditoProveedorDetalle;
import proteus.credito.repository.ICreditoProveedorDetalleRepository;
import proteus.credito.service.ICreditoProveedorDetalleService;
import proteus.credito.service.ICreditoProveedorService;
import proteus.factura.model.FacturaCompra;
import proteus.factura.service.IFacturaCompraService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.pago.model.PagoProveedor;
import proteus.pago.service.IPagoProveedorService;

/**
 * Services for CreditoProveedorDetalle Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class CreditoProveedorDetalleServiceImpl extends CRUDImpl<CreditoProveedorDetalle, Integer> implements ICreditoProveedorDetalleService {

	@Autowired
	private ICreditoProveedorDetalleRepository creditoProveedorDetalleRepository;

	@Autowired
	private ICreditoProveedorService creditoProveedorService;
	
	@Autowired
	private IFacturaCompraService facturaCompraService;
	
	@Autowired
	private IPagoProveedorService pagoProveedorService;
	
	@Override
	protected IGenericRepository<CreditoProveedorDetalle, Integer> getRepository() {
		return creditoProveedorDetalleRepository;
	}

	@Override
	public CreditoProveedorDetalle getByFacturaCompra(Integer idFacturaCompra) throws Exception {
		return creditoProveedorDetalleRepository.findByFacturaCompra(idFacturaCompra);
	}
	
	@Override
	public CreditoProveedorDetalle create(CreditoProveedorDetalle creditoProveedorDetalle) throws Exception {
		CreditoProveedorDetalle creditoProveedorDetalleAux = this.getByFacturaCompra(creditoProveedorDetalle.getFacturaCompra().getIdFacturaCompra());
		if(creditoProveedorDetalleAux==null) {
			//Se busca al credito para asignarlo al proveedor correspondiente por factura
			FacturaCompra facturaCompra = facturaCompraService.getById(creditoProveedorDetalle.getFacturaCompra().getIdFacturaCompra());
			CreditoProveedor creditoProveedor = creditoProveedorService.getByProveedor(facturaCompra.getProveedor().getIdProveedor());
			
			//Si no se encuentra entonces se crea un nuevo credito
			//Esta seccion puede crearse tanto aqui como en su propio controlador
			if(creditoProveedor==null) {
				creditoProveedor = new CreditoProveedor();
				creditoProveedor.setProveedor(facturaCompra.getProveedor());
				creditoProveedor.setDeudaAcumulativa(0.0);
				creditoProveedorService.create(creditoProveedor);
			}
			
			creditoProveedorDetalle.setCreditoProveedor(creditoProveedor);
			//Se suma el total de la factura a la deuda hacia el proveedor
			creditoProveedorService.updateDeudaAcumulativa(creditoProveedorDetalle.getCreditoProveedor().getIdCreditoProveedor(), 
					facturaCompra.getTotal(), true);
			
			//Como es nuevo el credito entonces no ha vencido la factura
			creditoProveedorDetalle.setVencida(false);
			creditoProveedorDetalle.setPagada(false);
			return creditoProveedorDetalleRepository.save(creditoProveedorDetalle);
		}
		return null;
	}

	@Override
	public List<CreditoProveedorDetalle> getByCreditoProveedor(Integer idCreditoProveedor) throws Exception {
		return creditoProveedorDetalleRepository.findByCreditoProveedor(idCreditoProveedor);
	}
	
	@Override
	public CreditoProveedorDetalle update(CreditoProveedorDetalle creditoProveedorDetalle) throws Exception {
		CreditoProveedorDetalle creditoProveedorDetalleAux = this.getById(creditoProveedorDetalle.getIdCreditoProveedorDetalle());
		creditoProveedorDetalleAux.setObservaciones(creditoProveedorDetalle.getObservaciones());
		creditoProveedorDetalleAux.setPagada(creditoProveedorDetalle.getPagada());
		return creditoProveedorDetalleRepository.save(creditoProveedorDetalleAux);
	}
	
	@Override
	public void delete(Integer idCreditoProveedorDetalle) throws Exception {
		CreditoProveedorDetalle creditoProveedorDetalle = this.getById(idCreditoProveedorDetalle);
		
		FacturaCompra facturaCompra = creditoProveedorDetalle.getFacturaCompra();
		//Se resta el total de la factura a la deuda hacia el proveedor
		creditoProveedorService.updateDeudaAcumulativa(creditoProveedorDetalle.getCreditoProveedor().getIdCreditoProveedor(), 
				facturaCompra.getTotal(), false);
		
		creditoProveedorDetalleRepository.deleteById(idCreditoProveedorDetalle);
	}

	@Override
	public void checkFacturasVencidas() throws Exception {
		LocalDate hoy = LocalDateTime.now().toLocalDate();
		List<CreditoProveedorDetalle> creditoProveedorDetalleList = this.getAll();
		
		for(CreditoProveedorDetalle creditoProveedorDetalle : creditoProveedorDetalleList) {
			FacturaCompra facturaCompra = creditoProveedorDetalle.getFacturaCompra();
			LocalDate facturaFecha = facturaCompra.getFecha();
			
			//Si ya han pasado dos dias desde que se emitio la factura de compra entonces ya esta vencida
			if(facturaFecha.isEqual(hoy)) {
				creditoProveedorDetalle.setVencida(false);
			} else if(facturaFecha.isEqual(hoy.minusDays(1))){
				creditoProveedorDetalle.setVencida(false);
			} else if(creditoProveedorDetalle.getPagada()){
				creditoProveedorDetalle.setVencida(false);
			} else {
				creditoProveedorDetalle.setVencida(true);
			}
			
			creditoProveedorDetalleRepository.save(creditoProveedorDetalle);
		}
	}
	
	private Boolean isVencida(LocalDate facturaFecha) {
		LocalDate hoy = LocalDateTime.now().toLocalDate();
		if(facturaFecha.isEqual(hoy)) {
			return false;
		} else if(facturaFecha.isEqual(hoy.minusDays(1))){
			return false;
		}
		return true;
	}

	@Override
	public List<CreditoProveedorDetalle> getByPagada(Boolean pagada) throws Exception {
		return creditoProveedorDetalleRepository.findByPagada(pagada);
	}

	@Override
	public List<CreditoProveedorDetalle> getByCreditoProveedorAndPagada(Integer idCreditoProveedor, Boolean pagada)
			throws Exception {
		return creditoProveedorDetalleRepository.findByCreditoProveedorAndPagada(idCreditoProveedor, pagada);
	}

	@Override
	public List<FacturaProveedorDTO> getFacturaByProveedor(Integer idProveedor) throws Exception {
		List<CreditoProveedorDetalle> creditoProveedorDetalleList = creditoProveedorDetalleRepository.findByProveedor(idProveedor);
		List<FacturaProveedorDTO> facturaDtoList = new ArrayList<FacturaProveedorDTO>();
		for(CreditoProveedorDetalle creditoProveedorDetalle : creditoProveedorDetalleList) {
			FacturaProveedorDTO facturaDto = this.convertirCreditoProveedorAFactura(creditoProveedorDetalle);
			facturaDtoList.add(facturaDto);
		}
		return facturaDtoList;
	}

	@Override
	public List<FacturaProveedorDTO> getFacturaByFecha(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate fechaDesdeParse = LocalDate.parse(fechaDesde, formatter);
		LocalDate fechaHastaParse = LocalDate.parse(fechaHasta, formatter);
		
		List<CreditoProveedorDetalle> creditoProveedorDetalleList = creditoProveedorDetalleRepository.getByFechaFactura(fechaDesdeParse, fechaHastaParse);
		List<FacturaProveedorDTO> facturaDtoList = new ArrayList<FacturaProveedorDTO>();
		for(CreditoProveedorDetalle creditoProveedorDetalle : creditoProveedorDetalleList) {
			FacturaProveedorDTO facturaDto = this.convertirCreditoProveedorAFactura(creditoProveedorDetalle);
			facturaDtoList.add(facturaDto);
		}
		return facturaDtoList;
	}
	
	public FacturaProveedorDTO convertirCreditoProveedorAFactura(CreditoProveedorDetalle creditoProveedorDetalle) throws Exception {
		FacturaProveedorDTO facturaDto = new FacturaProveedorDTO();
		facturaDto.setIdFacturaCompra(creditoProveedorDetalle.getFacturaCompra().getIdFacturaCompra());
		facturaDto.setFacturaNumero(creditoProveedorDetalle.getFacturaCompra().getCodigo());
		facturaDto.setProveedor(creditoProveedorDetalle.getCreditoProveedor().getProveedor().getNombre());
		facturaDto.setFecha(creditoProveedorDetalle.getFacturaCompra().getFecha().toString());
		facturaDto.setTotal(creditoProveedorDetalle.getFacturaCompra().getTotal());
		List<PagoProveedor> pagoProveedorList = pagoProveedorService.getByCreditoProveedor(creditoProveedorDetalle.getCreditoProveedor().getIdCreditoProveedor());
		if(!pagoProveedorList.isEmpty()) {
			facturaDto.setFechaPago(pagoProveedorList.get(0).getFechaHoraPago().toLocalDate().toString());
			facturaDto.setTipoPago(pagoProveedorList.get(0).getPagoTipo().getNombre());
			facturaDto.setVencida(false);
			facturaDto.setPagada(true);
		} else {
			facturaDto.setFechaPago("");
			facturaDto.setTipoPago("");
			facturaDto.setPagada(false);
			facturaDto.setVencida(this.isVencida(creditoProveedorDetalle.getFacturaCompra().getFecha()));
		}
		return facturaDto;
	}
	
}