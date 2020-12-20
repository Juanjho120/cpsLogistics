package proteus.nota.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.nota.model.NotaCredito;
import proteus.nota.repository.INotaCreditoRepository;
import proteus.nota.service.INotaCreditoService;

/**
 * Services for NotaCredito Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class NotaCreditoServiceImpl extends CRUDImpl<NotaCredito, Integer> implements INotaCreditoService {

	@Autowired
	private INotaCreditoRepository notaCreditoRepository;

	@Override
	protected IGenericRepository<NotaCredito, Integer> getRepository() {
		return notaCreditoRepository;
	}
	
	@Override
	public NotaCredito create(NotaCredito notaCredito) {
		NotaCredito notaCreditoAux = notaCreditoRepository.findByCodigo(notaCredito.getCodigo());
		if(notaCreditoAux == null) {
			notaCredito.setFechaIngreso(LocalDate.now());
			return notaCreditoRepository.save(notaCredito);
		}
		return null;
	}

	@Override
	public List<NotaCredito> getByFacturaCompra(Integer idFacturaCompra) throws Exception {
		return notaCreditoRepository.findByFacturaCompra(idFacturaCompra);
	}

	@Override
	public List<NotaCredito> getByFechaIngreso(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate fechaDesdeParse = LocalDate.parse(fechaDesde, formatter);
		LocalDate fechaHastaParse = LocalDate.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return notaCreditoRepository.findByFechaIngreso(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}
}
