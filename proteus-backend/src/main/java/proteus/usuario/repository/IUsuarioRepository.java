package proteus.usuario.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.usuario.model.Usuario;

/**
 * Repository for Usuario Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IUsuarioRepository extends IGenericRepository<Usuario, Integer> {

	Usuario findByUsername(String username);
	Usuario findByEmail(String email);
	Usuario findByTelefono(String telefono);
	
	@Modifying
	@Query("DELETE FROM Usuario WHERE username = :username")
	void deleteByUsername(String username);
	
	@Modifying
	@Query("DELETE FROM Usuario WHERE email = :email")
	void deleteByEmail(String email);
	
	//select * from usuario where username = ?
	Usuario findOneByUsername(String username);
	
	@Transactional
	@Modifying
	@Query("UPDATE Usuario us SET us.password =:clave WHERE us.username =:nombre")
	void cambiarClave(String clave, String nombre);
}
