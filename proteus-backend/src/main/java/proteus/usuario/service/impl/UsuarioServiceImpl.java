package proteus.usuario.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.usuario.model.Usuario;
import proteus.usuario.repository.IUsuarioRepository;

/**
 * Services for Usuario Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class UsuarioServiceImpl extends CRUDImpl<Usuario, Integer> implements UserDetailsService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	protected IGenericRepository<Usuario, Integer> getRepository() {
		return usuarioRepository;
	}

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
				usuario.setPassword(bcrypt.encode(usuario.getPassword()));
				usuario.setEnable(true);
				return usuarioRepository.save(usuario);
			}
		}
		return null;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findOneByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
		usuario.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		});
		
		UserDetails ud = new User(usuario.getUsername(), usuario.getPassword(), roles);
		
		return ud;
	}

	public Usuario getByEmail(String email) throws Exception {
		return usuarioRepository.findByEmail(email);
	}

	public Usuario getByTelefono(String telefono) throws Exception {
		return usuarioRepository.findByTelefono(telefono);
	}

	@Transactional
	public void deleteByUsername(String username) throws Exception {
		usuarioRepository.deleteByUsername(username);
	}

	@Transactional
	public void deleteByEmail(String email) throws Exception {
		usuarioRepository.deleteByEmail(email);
	}
	
}