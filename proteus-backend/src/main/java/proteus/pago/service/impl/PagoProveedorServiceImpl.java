package proteus.pago.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.credito.service.ICreditoProveedorService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.pago.model.PagoProveedor;
import proteus.pago.repository.IPagoProveedorRepository;
import proteus.pago.service.IPagoProveedorService;

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
	private ICreditoProveedorService creditoProveedorService;

	@Override
	protected IGenericRepository<PagoProveedor, Integer> getRepository() {
		return pagoProveedorRepository;
	}
	
	@Override
	public PagoProveedor create(PagoProveedor pagoProveedor) throws Exception {
		PagoProveedor pagoProveedorAux = this.getByNoDocumento(pagoProveedor.getNoDocumento());
		if(pagoProveedorAux==null) {
			creditoProveedorService.updateDeudaAcumulativa(pagoProveedor.getCreditoProveedor().getIdCreditoProveedor(), pagoProveedor.getMonto(), false);
			pagoProveedor.setFechaHoraPago(LocalDateTime.now());
			return pagoProveedorRepository.save(pagoProveedor);
		}
		return null;
	}

	@Override
	public PagoProveedor getByNoDocumento(String noDocumento) throws Exception {
		return pagoProveedorRepository.findByNoDocumento(noDocumento);
	}
	
	@Override
	public PagoProveedor update(PagoProveedor pagoProveedor) throws Exception {
		//Se busca el antiguo
		PagoProveedor pagoProveedorAux = this.getById(pagoProveedor.getIdPagoProveedor());
		
		//Para actualizar el credito total es necesario sumar primero el antiguo y luego restar el nuevo
		creditoProveedorService.updateDeudaAcumulativa(pagoProveedorAux.getCreditoProveedor().getIdCreditoProveedor(), pagoProveedorAux.getMonto(), true);
		creditoProveedorService.updateDeudaAcumulativa(pagoProveedor.getCreditoProveedor().getIdCreditoProveedor(), pagoProveedor.getMonto(), false);
		
		//Se mantiene la misma fecha y hora de pago original
		pagoProveedor.setFechaHoraPago(pagoProveedorAux.getFechaHoraPago());
		return pagoProveedorRepository.save(pagoProveedor);
	}
	
	@Override
	public void delete(Integer idPagoProveedor) throws Exception {
		//Se busca el antiguo
		PagoProveedor pagoProveedor = this.getById(idPagoProveedor);
		
		//Antes de eliminar, se devuelve el monto restado a la deuda
		creditoProveedorService.updateDeudaAcumulativa(pagoProveedor.getCreditoProveedor().getIdCreditoProveedor(), pagoProveedor.getMonto(), true);
		
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
	
}