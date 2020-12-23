package proteus.token.service;

import proteus.token.model.ResetToken;

public interface IResetTokenService {

	ResetToken getByToken(String token);
	void guardar(ResetToken token);
	void eliminar(ResetToken token);
	
}
