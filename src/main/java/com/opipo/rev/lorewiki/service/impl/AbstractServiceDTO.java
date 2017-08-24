package com.opipo.rev.lorewiki.service.impl;

import com.opipo.rev.lorewiki.service.ServiceDTOInterface;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.Collection;

public abstract class AbstractServiceDTO<T, I extends Serializable> implements ServiceDTOInterface<T, I> {

	public static String DOESNT_EXISTS = "ERR:RESOURCE:01";
	public static String EXISTS = "ERR:RESOURCE:02";
	public static String NEEDS_ID = "ERR:RESOURCE:03";

	protected abstract MongoRepository<T, I> getRepository();

	protected abstract T buildElement(I id);

	public Boolean exists(I id) {
		return this.find(id) != null;
	}

	public void checkExists(I id) throws IllegalArgumentException {
		if (!exists(id)) {
			throw new IllegalArgumentException(DOESNT_EXISTS);
		}
	}

	public void checkDoesntExists(I id) throws IllegalArgumentException {
		if (exists(id)) {
			throw new IllegalArgumentException(EXISTS);
		}
	}

	@Override
	public T find(I id) {
		return getRepository().findOne(id);
	}

	@Override
	public Collection<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public T save(T element) {
		return getRepository().save(element);
	}

	@Override
	public void delete(T element) {
		getRepository().delete(element);
	}

	@Override
	public void delete(I id) {
		getRepository().delete(id);
	}

	@Override
	public T create(I id) {
		return this.save(buildElement(id));
	}
}
