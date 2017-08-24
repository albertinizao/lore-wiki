package com.opipo.rev.lorewiki.service;

import java.io.Serializable;
import java.util.Collection;

public interface ServiceDTOInterface<T, I extends Serializable> {
	T find(I id);

	Collection<T> findAll();

	T create(I id);

	T save(T element);

	void delete(T element);

	void delete(I id);
	
	Boolean exists(I id);
	
	void checkExists(I id) throws IllegalArgumentException;
	
	void checkDoesntExists(I id) throws IllegalArgumentException;
}
