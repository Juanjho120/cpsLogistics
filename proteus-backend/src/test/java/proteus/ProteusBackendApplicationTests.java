package proteus;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import proteus.usuario.model.Usuario;
import proteus.usuario.repository.IUsuarioRepository;



@SpringBootTest
class ProteusBackendApplicationTests {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Test
	void verificarClave() {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(2);
		usuario.setUsername("carloslop");
		usuario.setPassword(bcrypt.encode("12345678"));				
		usuario.setEnable(true);
		
		Usuario retorno = usuarioRepository.save(usuario);
		
		assertTrue(retorno.getPassword().equalsIgnoreCase(usuario.getPassword()));		
		
		//prueba de la clase equivalente, la universidad politecnica de valencia 
	}

}
