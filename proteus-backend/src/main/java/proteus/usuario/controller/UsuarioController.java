package proteus.usuario.controller;

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

import proteus.exception.ModelNotFoundException;
import proteus.usuario.model.Usuario;
import proteus.usuario.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	/**
	 * Obtiene todos los usuarios de la base de datos
	 * @return Listado de usuarios
	 * @throws Exception
	 */
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() throws Exception {
		List<Usuario> usuarioList = usuarioService.getAll();
		if(usuarioList.isEmpty()) {
			throw new ModelNotFoundException("No se encuentran usuarios en la base de datos");
		}
		return new ResponseEntity<List<Usuario>>(usuarioList, HttpStatus.OK);
	}
	
	/**
	 * Busca un usuario por su id
	 * @param id
	 * @return Usuario
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable("id") Integer id) throws Exception {
		Usuario usuario = usuarioService.getById(id);
		if(usuario == null) {
			throw new ModelNotFoundException("Usuario con id " + id + " no encontrado");
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	/**
	 * Busca un usuario por su username
	 * @param username
	 * @return Usuario
	 * @throws Exception
	 */
	@GetMapping("/username/{username}")
	public ResponseEntity<Usuario> getByUsername(@PathVariable("username") String username) throws Exception {
		Usuario usuario = usuarioService.getByUsername(username);
		if(usuario == null) {
			throw new ModelNotFoundException("Usuario con username " + username + " no encontrado");
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	/**
	 * Busca un usuario por su email
	 * @param email
	 * @return Usuario
	 * @throws Exception
	 */
	@GetMapping("/email/{email}")
	public ResponseEntity<Usuario> getByEmail(@PathVariable("email") String email) throws Exception {
		Usuario usuario = usuarioService.getByEmail(email);
		if(usuario == null) {
			throw new ModelNotFoundException("Usuario con email " + email + " no encontrado");
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo usuario
	 * No lo guarda cuando encuentra otro usuario con el mismo email
	 * @param usuarioNew
	 * @throws Exception
	 */
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody Usuario usuarioNew) throws Exception {
		Usuario usuario = usuarioService.create(usuarioNew);
		if(usuario==null) {
			throw new Exception("No se ha podido crear el usuario");
		} else {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	/**
	 * Actualiza todos los valores del usuario buscandolo por su id
	 * No lo actualiza cuando encuentra otro usuario con el mismo email
	 * @param usuarioUp
	 * @return Usuario actualizado
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario usuarioUp) throws Exception {
		Usuario usuario = usuarioService.getByEmail(usuarioUp.getEmail());
		if(usuario!=null && usuario.getIdUsuario()!=usuarioUp.getIdUsuario()) {
			throw new Exception("No se ha podido actualizar el usuario");
		} else {
			usuario = usuarioService.getByTelefono(usuarioUp.getTelefono());
			if(usuario!=null && usuario.getIdUsuario()!=usuarioUp.getIdUsuario()) {
				throw new Exception("No se ha podido actualizar el usuario");
			}
		}
		usuario = usuarioService.update(usuarioUp);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}
	
	/**
	 * Elimina un usuario de la base de datos por su id
	 * @param id
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
		Usuario usuario = usuarioService.getById(id);
		if(usuario == null) {
			throw new ModelNotFoundException("Usuario con id " + id + " no encontrado");
		}
		usuarioService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Elimina un usuario de la base de datos por su username
	 * @param username
	 * @throws Exception
	 */
	@DeleteMapping("/username/{username}")
	public ResponseEntity<Void> deleteByUsername(@PathVariable("username") String username) throws Exception {
		Usuario usuario = usuarioService.getByUsername(username);
		if(usuario == null) {
			throw new ModelNotFoundException("Usuario con username " + username + " no encontrado");
		}
		usuarioService.deleteByUsername(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Elimina un usuario de la base de datos por su email
	 * @param email
	 * @throws Exception
	 */
	@DeleteMapping("/email/{email}")
	public ResponseEntity<Void> deleteByEmail(@PathVariable("email") String email) throws Exception {
		Usuario usuario = usuarioService.getByEmail(email);
		if(usuario == null) {
			throw new ModelNotFoundException("Usuario con email " + email + " no encontrado");
		}
		usuarioService.deleteByUsername(email);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
