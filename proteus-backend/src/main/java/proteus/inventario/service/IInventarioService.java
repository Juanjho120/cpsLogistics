package proteus.inventario.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.inventario.dto.InventarioEntradaSalidaDTO;
import proteus.inventario.dto.ProductoEntradaSalidaDTO;
import proteus.inventario.model.Inventario;

/**
 * Services for Inventario Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IInventarioService extends ICRUD<Inventario, Integer> {

	List<Inventario> getByConcepto(Integer idConcepto) throws Exception;
	List<Inventario> getByFechaRango(String fechaDesde, String fechaHasta) throws Exception;
	List<Inventario> getByConceptoAndFechaRango(Integer idConcepto, String fechaDesde, String fechaHasta) throws Exception;
	List<Inventario> getByRepuesto(Integer idRepuesto) throws Exception;
	List<Inventario> getByUsuario(Integer idUsuario) throws Exception;
	List<Inventario> getByConceptoAndRepuesto(Integer idConcepto, Integer idRepuesto) throws Exception;
	List<Inventario> getByConceptoAndUsuario(Integer idConcepto, Integer idUsuario) throws Exception;
	List<Inventario> getByRepuestoAndUsuario(Integer idRepuesto, Integer idUsuario) throws Exception;
	List<Inventario> getByRepuestoAndFechaRango(Integer idRepuesto, String fechaDesde, String fechaHasta) throws Exception;
	List<Inventario> getByUsuarioAndFechaRango(Integer idUsuario, String fechaDesde, String fechaHasta) throws Exception;
	List<Inventario> getByConceptoAndRepuestoAndUsuario(Integer idConcepto, Integer idRepuesto, Integer idUsuario) throws Exception;
	List<Inventario> getByConceptoAndRepuestoAndFecha(Integer idConcepto, Integer idRepuesto, String fechaDesde, String fechaHasta) throws Exception;
	List<Inventario> getByRepuestoAndFechaAndUsuario(Integer idRepuesto, String fechaDesde, String fechaHasta, Integer idUsuario) throws Exception;
	List<Inventario> getByConceptoAndFechaAndUsuario(Integer idConcepto, String fechaDesde, String fechaHasta, Integer idUsuario) throws Exception;
	List<Inventario> getByConceptoAndRepuestoAndFechaAndUsuario(Integer idConcepto, Integer idRepuesto, 
			String fechaDesde, String fechaHasta, Integer idUsuario) throws Exception;
	List<InventarioEntradaSalidaDTO> getInventarioEntradaSalidaByFechaRango(String fechaDesde, String fechaHasta) throws Exception;
	List<ProductoEntradaSalidaDTO> getProductoEntradaSalidaDTOByFecha(String fechaDesde, String fechaHasta, Integer idRepuesto) throws Exception;
	
}
