package proteus.factura.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.factura.model.FacturaCompra;
import proteus.factura.model.FacturaCompraDetalle;
import proteus.factura.repository.IFacturaCompraRepository;
import proteus.factura.service.IFacturaCompraDetalleService;
import proteus.factura.service.IFacturaCompraService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for FacturaCompra Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class FacturaCompraServiceImpl extends CRUDImpl<FacturaCompra, Integer> implements IFacturaCompraService {

	@Autowired
	private IFacturaCompraRepository facturaCompraRepository;
	
	@Autowired
	private IFacturaCompraDetalleService facturaCompraDetalleService;

	@Override
	protected IGenericRepository<FacturaCompra, Integer> getRepository() {
		return facturaCompraRepository;
	}

	@Override
	public List<FacturaCompra> getByCodigo(String codigo) throws Exception {
		return facturaCompraRepository.findByCodigo(codigo);
	}

	@Override
	public List<FacturaCompra> getByProveedor(Integer idProveedor) throws Exception {
		return facturaCompraRepository.findByProveedor(idProveedor);
	}

	@Override
	public FacturaCompra getByCodigoAndProveedor(String codigo, Integer idProveedor) throws Exception {
		return facturaCompraRepository.findByCodigoAndProveedor(codigo, idProveedor);
	}
	
	@Override
	public FacturaCompra create(FacturaCompra facturaCompra) throws Exception {
		FacturaCompra facturaCompraAux = this.getByCodigoAndProveedor(facturaCompra.getCodigo(), facturaCompra.getProveedor().getIdProveedor());
		if(facturaCompraAux==null) {
			Double totalFactura = 0.0;
			Double totalFacturaDetalle = 0.0;
			
			for(FacturaCompraDetalle facturaCompraDetalle : facturaCompra.getFacturaCompraDetalle()) {
				//Asigno a cada detalle la misma factura de compra
				facturaCompraDetalle.setFacturaCompra(facturaCompra);
				
				//Calculo el subtotal y total de la factura
				totalFacturaDetalle = facturaCompraDetalle.getCostoUnitario() * facturaCompraDetalle.getCantidad();
				totalFactura += totalFacturaDetalle;
				
				facturaCompraDetalle.setCostoTotal(totalFacturaDetalle);
			}
			facturaCompra.setTotal(totalFactura);
			
			//Guardo la factura de compra junto con su detalle
			return facturaCompraRepository.save(facturaCompra);
		}
		return null;
	}
	
	@Override
	public FacturaCompra update(FacturaCompra facturaCompra) throws Exception {
		//Borrando el detalle anterior
		List<FacturaCompraDetalle> facturaCompraDetalleList = facturaCompraDetalleService.getByFacturaCompra(facturaCompra.getIdFacturaCompra());
		if(!facturaCompraDetalleList.isEmpty()) {
			facturaCompraDetalleService.deleteByFacturaCompra(facturaCompra.getIdFacturaCompra());
		}
		
		Double totalFactura = 0.0;
		Double totalFacturaDetalle = 0.0;
		
		for(FacturaCompraDetalle facturaCompraDetalle : facturaCompra.getFacturaCompraDetalle()) {
			//Asigno a cada detalle la misma factura de compra
			facturaCompraDetalle.setFacturaCompra(facturaCompra);
			
			//Calculo el subtotal y total de la factura
			totalFacturaDetalle = facturaCompraDetalle.getCostoUnitario() * facturaCompraDetalle.getCantidad();
			totalFactura += totalFacturaDetalle;
			
			facturaCompraDetalle.setCostoTotal(totalFacturaDetalle);
		}
		facturaCompra.setTotal(totalFactura);
		
		//Guardo la factura de compra junto con su detalle
		return facturaCompraRepository.save(facturaCompra);
	}
	
	@Override
	public void delete(Integer idFacturaCompra) throws Exception {
		//Borrando el detalle de la factura
		List<FacturaCompraDetalle> facturaCompraDetalleList = facturaCompraDetalleService.getByFacturaCompra(idFacturaCompra);
		if(!facturaCompraDetalleList.isEmpty()) {
			facturaCompraDetalleService.deleteByFacturaCompra(idFacturaCompra);
		}
		//Eliminando la factura
		facturaCompraRepository.deleteById(idFacturaCompra);
	}

	@Override
	public List<FacturaCompra> getByFecha(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate fechaDesdeParse = LocalDate.parse(fechaDesde, formatter);
		LocalDate fechaHastaParse = LocalDate.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return facturaCompraRepository.findByFecha(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<FacturaCompra> getByRepuesto(Integer idRepuesto) throws Exception {
		return facturaCompraRepository.findByRepuesto(idRepuesto);
	}

	@Override
	public List<FacturaCompra> getByVencimiento(Boolean vencida) throws Exception {
		return facturaCompraRepository.findByVencimiento(vencida);
	}

	@Override
	public List<FacturaCompra> getNotInCreditoProveedorDetalle() throws Exception {
		return facturaCompraRepository.findNotInCreditoProveedorDetalle();
	}

	@Override
	public List<FacturaCompra> getByPagada(Boolean pagada) throws Exception {
		return facturaCompraRepository.findByPagada(pagada);
	}
	
}