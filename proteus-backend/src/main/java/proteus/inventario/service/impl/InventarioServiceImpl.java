package proteus.inventario.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.concepto.model.Concepto;
import proteus.concepto.service.IConceptoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.inventario.dto.InventarioEntradaSalidaDTO;
import proteus.inventario.model.Inventario;
import proteus.inventario.model.InventarioDetalle;
import proteus.inventario.repository.IInventarioRepository;
import proteus.inventario.service.IInventarioDetalleService;
import proteus.inventario.service.IInventarioService;
import proteus.repuesto.service.IRepuestoService;

/**
 * Services for Inventario Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class InventarioServiceImpl extends CRUDImpl<Inventario, Integer> implements IInventarioService {

	@Autowired
	private IInventarioRepository inventarioRepository;
	
	@Autowired
	private IInventarioDetalleService inventarioDetalleService;
	
	@Autowired
	private IRepuestoService repuestoService;
	
	@Autowired
	private IConceptoService conceptoService;

	@Override
	protected IGenericRepository<Inventario, Integer> getRepository() {
		return inventarioRepository;
	}
	
	@Override
	public Inventario create(Inventario inventario) throws Exception {
		Integer cantidadRepuestos = 0;
		Boolean sumar = false;
		
		//Obtengo el concepto
		Concepto concepto = conceptoService.getById(inventario.getConcepto().getIdConcepto());
		
		//Veo si el inventario es salida o entrada de productos
		if(concepto.getNombre().equalsIgnoreCase("ENTRADA")) {
			sumar = true;
		} else if(concepto.getNombre().equalsIgnoreCase("SALIDA")) {
			sumar = false;
		}
		
		//Calculo el total de producto y le asigno el id de inventario a cada detalle
		for(InventarioDetalle inventarioDetalle : inventario.getInventarioDetalle()) {
			repuestoService.actualizarCantidad(inventarioDetalle.getRepuesto().getIdRepuesto(), inventarioDetalle.getCantidad(), sumar);
			cantidadRepuestos += inventarioDetalle.getCantidad();
			inventarioDetalle.setInventario(inventario);
		}
		
		//Pongo la fecha y hora actual del ingreso del inventario
		inventario.setFechaHora(LocalDateTime.now());
		inventario.setCantidad(cantidadRepuestos);
		return inventarioRepository.save(inventario);
	}
	
	@Override
	public Inventario update(Inventario inventario) throws Exception {
		Integer cantidadRepuestos = 0;
		Boolean sumar = false;
		
		//Obtengo el concepto
		Concepto concepto = conceptoService.getById(inventario.getConcepto().getIdConcepto());
		
		//Veo si el inventario es salida o entrada de productos
		if(concepto.getNombre().equalsIgnoreCase("ENTRADA")) {
			sumar = true;
		} else if(concepto.getNombre().equalsIgnoreCase("SALIDA")) {
			sumar = false;
		}
		
		//Busco los detalles de inventario actuales
		List<InventarioDetalle> inventarioDetalleList = inventarioDetalleService.getByInventario(inventario.getIdInventario());
		
		//Sumo o resto los productos que estan saliendo o entrando con el inverso del concepto
		for(InventarioDetalle inventarioDetalle : inventarioDetalleList) {
			repuestoService.actualizarCantidad(inventarioDetalle.getRepuesto().getIdRepuesto(), inventarioDetalle.getCantidad(), !sumar);
		}
		
		//Borro los detalles del inventario
		inventarioDetalleService.deleteByInventario(inventario.getIdInventario());
		
		//Vuelvo a calcular la cantidad total de productos y sumo o resto en la tabla de repuestos
		for(InventarioDetalle inventarioDetalle : inventario.getInventarioDetalle()) {
			repuestoService.actualizarCantidad(inventarioDetalle.getRepuesto().getIdRepuesto(), inventarioDetalle.getCantidad(), sumar);
			cantidadRepuestos += inventarioDetalle.getCantidad();
			inventarioDetalle.setInventario(inventario);
		}
		
		//Seteo la cantidad de producto y luego guardo
		//La fecha de ingreso nunca se toca
		inventario.setCantidad(cantidadRepuestos);
		return inventarioRepository.save(inventario);
	}
	
	@Override
	public void delete(Integer idInventario) throws Exception {
		Boolean sumar = false;
		Inventario inventario = this.getById(idInventario);
		
		//Obtengo el concepto
		Concepto concepto = conceptoService.getById(inventario.getConcepto().getIdConcepto());
		
		//Veo si el inventario es salida o entrada de productos
		if(concepto.getNombre().equalsIgnoreCase("ENTRADA")) {
			sumar = true;
		} else if(concepto.getNombre().equalsIgnoreCase("SALIDA")) {
			sumar = false;
		}
		
		//Busco los detalles de inventario actuales
		List<InventarioDetalle> inventarioDetalleList = inventarioDetalleService.getByInventario(idInventario);
		
		//Sumo o resto los productos que estan saliendo o entrando con el inverso del concepto
		for(InventarioDetalle inventarioDetalle : inventarioDetalleList) {
			repuestoService.actualizarCantidad(inventarioDetalle.getRepuesto().getIdRepuesto(), inventarioDetalle.getCantidad(), !sumar);
		}
		
		//Borro los detalles del inventario
		inventarioDetalleService.deleteByInventario(idInventario);
		
		inventarioRepository.deleteById(idInventario);
	}

	@Override
	public List<Inventario> getByConcepto(Integer idConcepto) throws Exception {
		return inventarioRepository.findByConcepto(idConcepto);
	}

	@Override
	public List<Inventario> getByFechaRango(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return inventarioRepository.findByFechaRango(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<Inventario> getByConceptoAndFechaRango(Integer idConcepto, String fechaDesde, String fechaHasta)
			throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return inventarioRepository.findByConceptoAndFechaRango(idConcepto, fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<Inventario> getByRepuesto(Integer idRepuesto) throws Exception {
		return inventarioRepository.findByRepuesto(idRepuesto);
	}

	@Override
	public List<Inventario> getByUsuario(Integer idUsuario) throws Exception {
		return inventarioRepository.findByUsuario(idUsuario);
	}

	@Override
	public List<Inventario> getByConceptoAndRepuesto(Integer idConcepto, Integer idRepuesto) throws Exception {
		return inventarioRepository.findByConceptoAndRepuesto(idConcepto, idRepuesto);
	}

	@Override
	public List<Inventario> getByConceptoAndUsuario(Integer idConcepto, Integer idUsuario) throws Exception {
		return inventarioRepository.findByConceptoAndUsuario(idConcepto, idUsuario);
	}

	@Override
	public List<Inventario> getByRepuestoAndUsuario(Integer idRepuesto, Integer idUsuario) throws Exception {
		return inventarioRepository.findByRepuestoAndUsuario(idRepuesto, idUsuario);
	}

	@Override
	public List<Inventario> getByRepuestoAndFechaRango(Integer idRepuesto, String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return inventarioRepository.findByRepuestoAndFechaRango(idRepuesto, fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<Inventario> getByUsuarioAndFechaRango(Integer idUsuario, String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return inventarioRepository.findByUsuarioAndFechaRango(idUsuario, fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<Inventario> getByConceptoAndRepuestoAndUsuario(Integer idConcepto, Integer idRepuesto,
			Integer idUsuario) throws Exception {
		return inventarioRepository.findByConceptoAndRepuestoAndUsuario(idConcepto, idRepuesto, idUsuario);
	}

	@Override
	public List<Inventario> getByConceptoAndRepuestoAndFecha(Integer idConcepto, Integer idRepuesto, String fechaDesde,
			String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return inventarioRepository.findByConceptoAndRepuestoAndFecha(idConcepto, idRepuesto, fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<Inventario> getByRepuestoAndFechaAndUsuario(Integer idRepuesto, String fechaDesde, String fechaHasta,
			Integer idUsuario) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return inventarioRepository.findByRepuestoAndFechaAndUsuario(idRepuesto, fechaDesdeParse, fechaHastaParse.plusDays(1), idUsuario);
	}

	@Override
	public List<Inventario> getByConceptoAndFechaAndUsuario(Integer idConcepto, String fechaDesde, String fechaHasta,
			Integer idUsuario) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return inventarioRepository.findByConceptoAndFechaAndUsuario(idConcepto, fechaDesdeParse, fechaHastaParse.plusDays(1), idUsuario);
	}

	@Override
	public List<Inventario> getByConceptoAndRepuestoAndFechaAndUsuario(Integer idConcepto, Integer idRepuesto,
			String fechaDesde, String fechaHasta, Integer idUsuario) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		return inventarioRepository.findByConceptoAndRepuestoAndFechaAndUsuario(idConcepto, idRepuesto, fechaDesdeParse, fechaHastaParse.plusDays(1), idUsuario);
	}

	@Override
	public List<InventarioEntradaSalidaDTO> getInventarioEntradaSalidaByFechaRango(String fechaDesde, String fechaHasta)
			throws Exception {
		List<InventarioEntradaSalidaDTO> inventarioEntradaSalidaList = new ArrayList<InventarioEntradaSalidaDTO>();
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		//Se suma un dia a la fecha para tomar en cuenta el rango mayor
		List<Inventario> inventarioList = inventarioRepository.findByFechaRango(fechaDesdeParse, fechaHastaParse.plusDays(1));
		for(Inventario inventario : inventarioList) {
			List<InventarioDetalle> inventarioDetalleList = inventarioDetalleService.getByInventario(inventario.getIdInventario());
			InventarioEntradaSalidaDTO inventarioEntradaSalidaDTO;
			Boolean creado;
			for(InventarioDetalle inventarioDetalle : inventarioDetalleList) {
				creado = false;
				//Recorriendo la lista para no agregar repetidos y agruparlos por repuesto
				for(InventarioEntradaSalidaDTO inventarioEntradaSalidaDTOAux : inventarioEntradaSalidaList) {
					//Si ya existe el detalle creado en la lista entonces solo se agrega en ENTRADA o SALIDA
					if(inventarioDetalle.getRepuesto().getIdRepuesto() == inventarioEntradaSalidaDTOAux.getIdRepuesto()) {
						if(inventario.getConcepto().getNombre().equalsIgnoreCase("ENTRADA")) { //Si es ENTRADA
							inventarioEntradaSalidaDTOAux.setEntrada(inventarioEntradaSalidaDTOAux.getEntrada()+inventarioDetalle.getCantidad());
						} else if(inventario.getConcepto().getNombre().equalsIgnoreCase("SALIDA")) { //Si es SALIDA
							inventarioEntradaSalidaDTOAux.setSalida(inventarioEntradaSalidaDTOAux.getSalida()+inventarioDetalle.getCantidad());
						}
						creado = true;
					}
				}
				//Si no existe entonces la variable creado permanecerá en false
				if(!creado) {
					inventarioEntradaSalidaDTO = new InventarioEntradaSalidaDTO();
					inventarioEntradaSalidaDTO.setCodigo(inventarioDetalle.getRepuesto().getCodigo());
					inventarioEntradaSalidaDTO.setDescripcion(inventarioDetalle.getRepuesto().getDescripcion());
					inventarioEntradaSalidaDTO.setIdRepuesto(inventarioDetalle.getRepuesto().getIdRepuesto());
					inventarioEntradaSalidaDTO.setExistencia(inventarioDetalle.getRepuesto().getExistencia());
					if(inventario.getConcepto().getNombre().equalsIgnoreCase("ENTRADA")) { //Si es ENTRADA
						inventarioEntradaSalidaDTO.setEntrada(inventarioDetalle.getCantidad());
						inventarioEntradaSalidaDTO.setSalida(0);
					} else if(inventario.getConcepto().getNombre().equalsIgnoreCase("SALIDA")) { //Si es SALIDA
						inventarioEntradaSalidaDTO.setSalida(inventarioDetalle.getCantidad());
						inventarioEntradaSalidaDTO.setEntrada(0);
					}
					//El nuevo se agrega a la lista
					inventarioEntradaSalidaList.add(inventarioEntradaSalidaDTO);
				}
			}
			
		}
		return inventarioEntradaSalidaList;
	}
	
}