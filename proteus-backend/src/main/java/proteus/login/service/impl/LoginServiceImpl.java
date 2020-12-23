package proteus.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import proteus.login.service.ILoginService;
import proteus.usuario.model.Usuario;
import proteus.usuario.repository.IUsuarioRepository;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private BCryptPasswordEncoder bcrypt;	
	
	@Autowired
	private IUsuarioRepository repo;

	@Override
	public Usuario verificarNombreUsuario(String usuario) {
		return repo.findByUsername(usuario);
	}

	@Override
	public void cambiarClave(String clave, String nombre) {
		repo.cambiarClave(bcrypt.encode(clave), nombre);
	}
	
}
