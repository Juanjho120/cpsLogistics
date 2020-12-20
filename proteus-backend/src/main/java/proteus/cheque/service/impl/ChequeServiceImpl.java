package proteus.cheque.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.boleta.model.Boleta;
import proteus.boleta.service.IBoletaService;
import proteus.cheque.model.Cheque;
import proteus.cheque.repository.IChequeRepository;
import proteus.cheque.service.IChequeService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for Cheque Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ChequeServiceImpl extends CRUDImpl<Cheque, Integer> implements IChequeService {

	@Autowired
	private IChequeRepository chequeRepository;
	
	@Autowired
	private IBoletaService boletaService;

	@Override
	protected IGenericRepository<Cheque, Integer> getRepository() {
		return chequeRepository;
	}

	@Override
	public Cheque getByNumeroAndCuentaBancaria(String numero, Integer idCuentaBancaria) throws Exception {
		return chequeRepository.findByNumeroAndCuentaBancaria(numero, idCuentaBancaria);
	}

	@Override
	public List<Cheque> getByCuentaBancaria(Integer idCuentaBancaria) throws Exception {
		return chequeRepository.findByCuentaBancaria(idCuentaBancaria);
	}

	@Override
	public List<Cheque> getByBanco(Integer idBanco) throws Exception {
		return chequeRepository.findByBanco(idBanco);
	}
	
	@Override
	public Cheque create(Cheque cheque) throws Exception {
		Cheque chequeAux = this.getByNumeroAndCuentaBancaria(cheque.getNumero(), cheque.getCuentaBancaria().getIdCuentaBancaria());
		if(chequeAux==null) {
			return chequeRepository.save(cheque);
		}
		return null;
	}

	@Override
	public void delete(Integer idCheque) throws Exception {
		Boolean isInPagoProveedor = this.isChequeInPagoProveedor(idCheque);
		Boolean isInSegmentoPago = this.isChequeInSegmentoPago(idCheque);
		if(isInPagoProveedor || isInSegmentoPago) {
			throw new Exception("El cheque se encuentra asociado a un pago, no es posible eliminar");
		} else {
			List<Boleta> boletaList = boletaService.getByCheque(idCheque);
			for(Boleta boleta : boletaList) {
				boleta.setCheque(null);
				boletaService.update(boleta);
			}
			chequeRepository.deleteById(idCheque);
		}
	}
	
	@Override
	public List<Cheque> getByFacturaNumeroInSegmentoPago(String facturaNumero) throws Exception {
		return chequeRepository.findByNumeroFacturaInSegmentoPago(facturaNumero);
	}

	@Override
	public List<Cheque> getByFacturaCodigoInPagoProveedor(String codigo) throws Exception {
		return chequeRepository.findByCodigoFacturaInPagoProveedor(codigo);
	}

	@Override
	public Boolean isChequeInPagoProveedor(Integer idCheque) throws Exception {
		Cheque cheque = chequeRepository.findChequeInPagoProveedor(idCheque);
		if(cheque==null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean isChequeInSegmentoPago(Integer idCheque) throws Exception {
		Cheque cheque = chequeRepository.findChequeInSegmentoPago(idCheque);
		if(cheque==null) {
			return false;
		}
		return true;
	}

	@Override
	public List<Cheque> getNotInBoletas() throws Exception {
		return chequeRepository.findNotInBoletas();
	}

	@Override
	public List<Cheque> getInBoletas() throws Exception {
		return chequeRepository.findInBoletas();
	}

	@Override
	public List<Cheque> getByNombre(String nombre) throws Exception {
		return chequeRepository.findByNombre("%"+nombre+"%");
	}

	@Override
	public List<Cheque> getByNumero(String numero) throws Exception {
		return chequeRepository.findByNumero("%"+numero+"%");
	}

	@Override
	public List<Cheque> getByFechaEmision(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate fechaDesdeParse = LocalDate.parse(fechaDesde, formatter);
		LocalDate fechaHastaParse = LocalDate.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return chequeRepository.findByFechaEmision(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<Cheque> getByFechaDeposito(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate fechaDesdeParse = LocalDate.parse(fechaDesde, formatter);
		LocalDate fechaHastaParse = LocalDate.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return chequeRepository.findByFechaDeposito(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<Cheque> getNotInBoletasExceptCheque(Integer idCheque) throws Exception {
		return chequeRepository.findNotInBoletasExceptCheque(idCheque);
	}
	
}
