package proteus.reportes.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import proteus.inventario.service.IInventarioService;

/**
 * Servicios para la creacion de reportes
 * 
 * @author Juan Tzun
 */
@Service
public class ReporteService {
	
	@Autowired
	private IInventarioService inventarioService;
	
	public byte[] crearReporteInventarioEntradaSalida(String fechaDesde, String fechaHasta) {
		byte[] data = null;
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("fechaDesde", fechaDesde.substring(0,11));
		parametros.put("fechaHasta", fechaHasta.substring(0,11));
		
		try {
			//File file = new ClassPathResource("/reports/inventarioEntradaSalida.jasper").getFile();
			InputStream input = new ClassPathResource("/reports/inventarioEntradaSalida.jrxml").getInputStream();
			JasperDesign jasperDesign = JRXmlLoader.load(input);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			
			JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, new JRBeanCollectionDataSource(inventarioService.getInventarioEntradaSalidaByFechaRango(fechaDesde, fechaHasta)));
			data = JasperExportManager.exportReportToPdf(print);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}

}
