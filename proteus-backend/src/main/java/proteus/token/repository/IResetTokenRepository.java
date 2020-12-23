package proteus.token.repository;

import proteus.generico.repository.IGenericRepository;
import proteus.token.model.ResetToken;

public interface IResetTokenRepository extends IGenericRepository<ResetToken, Integer> {

	ResetToken findByToken(String token);
	
}
