package proteus.categoria.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proteus.categoria.model.Categoria;
import proteus.categoria.service.ICategoriaService;
import proteus.exception.ModelNotFoundException;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private ICategoriaService categoriaService;
	
	/**
	 * Obtiene todas las categorias en la base de datos
	 * @return Listado de categorias
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() throws Exception {
		List<Categoria> categoriaList = categoriaService.getAll();
		if(categoriaList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran cuentas categorias en la base de datos");
		}
		return new ResponseEntity<List<Categoria>>(categoriaList, HttpStatus.OK);
	}
	
	/**
	 * Busca una categoria por su id
	 * @param id
	 * @return Categoria
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable("id") Integer id) throws Exception {
		Categoria categoria = categoriaService.getById(id);
		if(categoria == null) {
			throw new ModelNotFoundException("Categoria con id " + id + " no encontrado");
		}
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}
	
	/**
	 * Guarda una nueva categoria
	 * No lo guarda cuando encuentra otra categoria con el mismo nombre
	 * @param categoriaNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Categoria categoriaNew) throws Exception {
		Categoria categoria = categoriaService.create(categoriaNew);
		if(categoria==null) {
			throw new Exception("No se ha podido crear la categoria");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores de la categoria buscandolo por su id
	 * @param categoriaUp
	 * @return Categoria actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Categoria> update(@Valid @RequestBody Categoria categoriaUp) throws Exception {
		Categoria categoria = categoriaService.update(categoriaUp);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina una categoria de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Categoria categoria = categoriaService.getById(id);
		if(categoria == null) {
			throw new ModelNotFoundException("Categoria con id " + id + " no encontrado");
		}
		categoriaService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
