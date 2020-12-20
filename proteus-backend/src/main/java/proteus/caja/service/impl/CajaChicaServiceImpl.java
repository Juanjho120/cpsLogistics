package proteus.caja.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.caja.model.CajaChica;
import proteus.caja.repository.ICajaChicaRepository;
import proteus.caja.service.ICajaChicaService;
import proteus.concepto.service.IConceptoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.saldo.model.Saldo;
import proteus.saldo.service.ISaldoService;

/**
 * Services for CajaChica Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class CajaChicaServiceImpl extends CRUDImpl<CajaChica, Integer> implements ICajaChicaService {

	@Autowired
	private ICajaChicaRepository cajaChicaRepository;
	
	@Autowired
	private ISaldoService saldoService;
	
	@Autowired
	private IConceptoService conceptoService;

	@Override
	protected IGenericRepository<CajaChica, Integer> getRepository() {
		return cajaChicaRepository;
	}

	@Override
	public List<CajaChica> getByServicio(Integer idServicio) throws Exception {
		return cajaChicaRepository.findByServicio(idServicio);
	}

	@Override
	public CajaChica create(CajaChica cajaChica) throws Exception {
		Saldo saldo = saldoService.getByNombre("Caja Chica");
		
		cajaChica.setConcepto(conceptoService.getById(cajaChica.getConcepto().getIdConcepto()));
		//SE ACTUALIZA EL SALDO DE CAJA CHICA EN LA TABLA
		if(cajaChica.getConcepto().getNombre().equalsIgnoreCase("ENTRADA")) {
			saldo.setMonto(saldo.getMonto()+cajaChica.getMonto());
		} else if(cajaChica.getConcepto().getNombre().equalsIgnoreCase("SALIDA")) {
			if(saldo.getMonto()>cajaChica.getMonto()) {
				saldo.setMonto(saldo.getMonto()-cajaChica.getMonto());
			} else {
				throw new Exception("El saldo para caja chica no es suficiente");
			}
		}
		
		saldoService.update(saldo);
		cajaChica.setFechaIngreso(LocalDate.now());
		return cajaChicaRepository.save(cajaChica);
	}
	
	@Override
	public CajaChica update(CajaChica cajaChica) throws Exception {
		Saldo saldo = saldoService.getByNombre("Caja Chica");
		
		CajaChica cajaChicaOld = this.getById(cajaChica.getIdCajaChica());
		//SE ACTUALIZA EL SALDO DE CAJA CHICA EN LA TABLA
		if(cajaChicaOld.getConcepto().getNombre().equalsIgnoreCase("ENTRADA")) {
			if(saldo.getMonto()>cajaChicaOld.getMonto()) {
				saldo.setMonto(saldo.getMonto()-cajaChicaOld.getMonto());
			} else {
				throw new Exception("No hay suficiente saldo para caja chica para borrar la actual entrada");
			}
		} else if(cajaChicaOld.getConcepto().getNombre().equalsIgnoreCase("SALIDA")) {
			saldo.setMonto(saldo.getMonto()+cajaChicaOld.getMonto());
		}
				
		cajaChica.setConcepto(conceptoService.getById(cajaChica.getConcepto().getIdConcepto()));
		//SE ACTUALIZA EL SALDO DE CAJA CHICA EN LA TABLA
		if(cajaChica.getConcepto().getNombre().equalsIgnoreCase("ENTRADA")) {
			saldo.setMonto(saldo.getMonto()+cajaChica.getMonto());
		} else if(cajaChica.getConcepto().getNombre().equalsIgnoreCase("SALIDA")) {
			if(saldo.getMonto()>cajaChica.getMonto()) {
				saldo.setMonto(saldo.getMonto()-cajaChica.getMonto());
			} else {
				throw new Exception("El saldo para caja chica no es suficiente");
			}
		}
		
		saldoService.update(saldo);
		return cajaChicaRepository.save(cajaChica);
	}
	
	@Override
	public void delete(Integer idCajaChica) throws Exception {
		CajaChica cajaChica = this.getById(idCajaChica);
		Saldo saldo = saldoService.getByNombre("Caja Chica");
		cajaChica.setConcepto(conceptoService.getById(cajaChica.getConcepto().getIdConcepto()));
		//SE ACTUALIZA EL SALDO DE CAJA CHICA EN LA TABLA
		if(cajaChica.getConcepto().getNombre().equalsIgnoreCase("ENTRADA")) {
			if(saldo.getMonto()>cajaChica.getMonto()) {
				saldo.setMonto(saldo.getMonto()-cajaChica.getMonto());
			} else {
				throw new Exception("No hay suficiente saldo para caja chica para borrar la actual entrada");
			}
		} else if(cajaChica.getConcepto().getNombre().equalsIgnoreCase("SALIDA")) {
			saldo.setMonto(saldo.getMonto()+cajaChica.getMonto());
		}
		cajaChicaRepository.deleteById(idCajaChica);
	}
	
	@Override
	public List<CajaChica> getByFechaIngreso(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				
		LocalDate fechaDesdeParse = LocalDate.parse(fechaDesde, formatter);
		LocalDate fechaHastaParse = LocalDate.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return cajaChicaRepository.findByFechaIngreso(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<CajaChica> getByPlaca(Integer idPlaca) throws Exception {
		return cajaChicaRepository.findByPlaca(idPlaca);
	}
}
