package proteus.token.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.token.model.ResetToken;
import proteus.token.repository.IResetTokenRepository;
import proteus.token.service.IResetTokenService;

@Service
public class ResetTokenServiceImpl implements IResetTokenService {

	@Autowired
	private IResetTokenRepository resetTokenRepository;

	@Override
	public ResetToken getByToken(String token) {
		return resetTokenRepository.findByToken(token);
	}

	@Override
	public void guardar(ResetToken token) {
		resetTokenRepository.save(token);
	}

	@Override
	public void eliminar(ResetToken token) {
		resetTokenRepository.delete(token);
	}
	
	
}
