package proteus.reportes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proteus.reportes.service.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

	@Autowired
	private ReporteService reporteService;
	
	@GetMapping(value = "/inventarios/inventario-entrada-salida/{fechaDesde}/{fechaHasta}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> crearReporteInventarioEntradaSalida(@PathVariable("fechaDesde") String fechaDesde,
			@PathVariable("fechaHasta") String fechaHasta) {
		byte[] data = null;
		data = reporteService.crearReporteInventarioEntradaSalida(fechaDesde, fechaHasta);
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
}
