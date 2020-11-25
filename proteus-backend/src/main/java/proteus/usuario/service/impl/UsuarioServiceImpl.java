package proteus.usuario.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.usuario.model.Usuario;
import proteus.usuario.repository.IUsuarioRepository;
import proteus.usuario.service.IUsuarioService;

/**
 * Services for Usuario Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class UsuarioServiceImpl extends CRUDImpl<Usuario, Integer> implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	protected IGenericRepository<Usuario, Integer> getRepository() {
		return usuarioRepository;
	}

	@Override
	public String createUsername(String nombre, String apellido) throws Exception {
		String username = "";
		Integer cont = 0;
		Boolean repetido = false;
		Usuario usuario = null;
		
		do {
			if(cont == 0) {
				username = nombre.toLowerCase() + apellido.substring(0, 3).toLowerCase();
			} else {
				username = nombre.toLowerCase() + apellido.substring(0, 3).toLowerCase() + cont;
			}
			
			usuario = this.getByUsername(username);
			if(usuario==null) {
				repetido = false;
			} else {
				cont++;
				repetido = true;
			}
			
		} while(repetido);
		
		return username;
	}

	@Override
	public Usuario getByUsername(String username) throws Exception {
		return usuarioRepository.findByUsername(username);
	}
	
	@Override
	public Usuario create(Usuario usuario) throws Exception {
		Usuario usuarioAux = this.getByEmail(usuario.getEmail());
		if(usuarioAux==null) {
			usuarioAux = this.getByTelefono(usuario.getTelefono());
			if(usuarioAux==null) {
				String username = this.createUsername(usuario.getNombre(), usuario.getApellido());
				usuario.setUsername(username);
				return usuarioRepository.save(usuario);
			}
		}
		return null;
	}

	@Override
	public Usuario getByEmail(String email) throws Exception {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario getByTelefono(String telefono) throws Exception {
		return usuarioRepository.findByTelefono(telefono);
	}

	@Transactional
	@Override
	public void deleteByUsername(String username) throws Exception {
		usuarioRepository.deleteByUsername(username);
	}

	@Transactional
	@Override
	public void deleteByEmail(String email) throws Exception {
		usuarioRepository.deleteByEmail(email);
	}
	
}