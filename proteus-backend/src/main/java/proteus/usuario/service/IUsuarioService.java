package proteus.usuario.service;

import proteus.generico.service.ICRUD;
import proteus.usuario.model.Usuario;

/**
 * Services for Usuario Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IUsuarioService extends ICRUD<Usuario, Integer> {

	String createUsername(String nombre, String apellido) throws Exception;
	Usuario getByUsername(String username) throws Exception;
	Usuario getByEmail(String email) throws Exception;
	Usuario getByTelefono(String telefono) throws Exception;
	void deleteByUsername(String username) throws Exception;
	void deleteByEmail(String email) throws Exception;
}
