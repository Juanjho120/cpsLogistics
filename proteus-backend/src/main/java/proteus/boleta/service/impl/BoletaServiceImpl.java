package proteus.boleta.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.boleta.model.Boleta;
import proteus.boleta.repository.IBoletaRepository;
import proteus.boleta.service.IBoletaService;
import proteus.cheque.model.Cheque;
import proteus.cheque.service.IChequeService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for Boleta Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class BoletaServiceImpl extends CRUDImpl<Boleta, Integer> implements IBoletaService {
	
	@Autowired
	private IBoletaRepository boletaRepository;
	
	@Autowired
	private IChequeService chequeService;

	@Override
	protected IGenericRepository<Boleta, Integer> getRepository() {
		return boletaRepository;
	}
	
	@Override
	public Boleta create(Boleta boleta) throws Exception {
		Boleta boletaAux = this.getByNumeroAndCuentaBancaria(boleta.getNumero(), boleta.getCuentaBancaria().getIdCuentaBancaria());
		if(boletaAux==null) {
			if(boleta.getCheque()!=null) {
				List<Boleta> boletaList = this.getByCheque(boleta.getCheque().getIdCheque());
				if(!boletaList.isEmpty()) {
					throw new Exception("El cheque ya esta asociado a otra boleta");
				}
				Cheque cheque = chequeService.getById(boleta.getCheque().getIdCheque());
				boleta.setMonto(cheque.getMonto());
				cheque.setFechaDeposito(boleta.getFecha());
				chequeService.update(cheque);
			}
			return boletaRepository.save(boleta);
		}
		return null;
	}

	@Override
	public Boleta update(Boleta boleta) throws Exception {
		Boleta boletaAux = this.getById(boleta.getIdBoleta());
		if(boletaAux.getCheque()!=null) {
			Cheque cheque = boletaAux.getCheque();
			cheque.setFechaDeposito(null);
			chequeService.update(cheque);
			
			boletaAux.setCheque(null);
			boletaAux.setMonto(0.0);
			boletaRepository.save(boletaAux);
		}
		
		if(boleta.getCheque()!=null) {
			Cheque cheque = chequeService.getById(boleta.getCheque().getIdCheque());
			cheque.setFechaDeposito(boleta.getFecha());
			chequeService.update(cheque);
			boleta.setMonto(cheque.getMonto());
		}
		return boletaRepository.save(boleta);
	}
	
	@Override
	public void delete(Integer idBoleta) throws Exception {
		Boleta boleta = this.getById(idBoleta);
		Cheque cheque = boleta.getCheque();
		if(cheque!=null) {
			cheque.setFechaDeposito(null);
			chequeService.update(cheque);
		}
		boletaRepository.deleteById(idBoleta);
	}
	
	@Override
	public Boleta getByNumeroAndCuentaBancaria(String numero, Integer idCuentaBancaria) throws Exception {
		return boletaRepository.findByNumeroAndCuentaBancaria(numero, idCuentaBancaria);
	}

	@Override
	public List<Boleta> getByCuentaBancaria(Integer idCuentaBancaria) throws Exception {
		return boletaRepository.findByCuentaBancaria(idCuentaBancaria);
	}

	@Override
	public List<Boleta> getByBanco(Integer idBanco) throws Exception {
		return boletaRepository.findByBanco(idBanco);
	}

	@Override
	public List<Boleta> getByFecha(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate fechaDesdeParse = LocalDate.parse(fechaDesde, formatter);
		LocalDate fechaHastaParse = LocalDate.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return boletaRepository.findByFecha(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<Boleta> getByCheque(Integer idCheque) throws Exception {
		return boletaRepository.findByCheque(idCheque);
	}

	@Override
	public List<Boleta> getByNumero(String numero) throws Exception {
		return boletaRepository.findByNumero("%"+numero+"%");
	}

	@Override
	public List<Boleta> getByBoletaTipoDocumento(Integer idBoletaTipoDocumento) throws Exception {
		return boletaRepository.findByBoletaTipoDocumento(idBoletaTipoDocumento);
	}
 
}
