package proteus.generico.service;

import java.util.List;

import proteus.generico.repository.IGenericRepository;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	protected abstract IGenericRepository<T, ID> getRepository();
	
	@Override
	public T create(T obj) throws Exception {
		return getRepository().save(obj);
	}
	
	@Override
	public T update(T obj) throws Exception {
		return getRepository().save(obj);
	}

	@Override
	public List<T> getAll() throws Exception {
		return getRepository().findAll();
	}

	@Override
	public T getById(ID id) throws Exception {
		return getRepository().findById(id).orElse(null);
	}

	@Override
	public void delete(ID id) throws Exception {
		getRepository().deleteById(id);
	}
}
