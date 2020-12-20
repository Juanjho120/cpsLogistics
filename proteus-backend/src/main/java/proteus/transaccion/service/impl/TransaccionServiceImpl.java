package proteus.transaccion.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.transaccion.model.Transaccion;
import proteus.transaccion.repository.ITransaccionRepository;
import proteus.transaccion.service.ITransaccionService;

/**
 * Services for Transaccion Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class TransaccionServiceImpl extends CRUDImpl<Transaccion, Integer> implements ITransaccionService {
	
	@Autowired
	private ITransaccionRepository transaccionRepository;

	@Override
	protected IGenericRepository<Transaccion, Integer> getRepository() {
		return transaccionRepository;
	}

	@Override
	public Transaccion getByReferencia(String referencia) throws Exception {
		return transaccionRepository.findByReferencia(referencia);
	}

	@Override
	public List<Transaccion> getByFechaAprobacion(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate fechaDesdeParse = LocalDate.parse(fechaDesde, formatter);
		LocalDate fechaHastaParse = LocalDate.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return transaccionRepository.findByFechaAprobacion(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}
	
	@Override
	public Transaccion create(Transaccion transaccion) throws Exception {
		Transaccion transaccionAux = this.getByReferencia(transaccion.getReferencia());
		if(transaccionAux==null) {
			return transaccionRepository.save(transaccion);
		}
		return null;
	}

	@Override
	public List<Transaccion> getByFacturaNumeroInSegmentoPago(String facturaNumero) throws Exception {
		return transaccionRepository.findByNumeroFacturaInSegmentoPago(facturaNumero);
	}
	
}
