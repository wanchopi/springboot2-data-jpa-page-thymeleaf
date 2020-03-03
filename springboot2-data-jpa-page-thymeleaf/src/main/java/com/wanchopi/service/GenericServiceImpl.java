package com.wanchopi.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * GenericServiceImpl, implements the GenericServiceAPI interface
 * 
 * @author Wanchopi
 *
 * @param <T>
 * @param <ID>
 */
@Service
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericServiceAPI<T, ID> {

	@Override
	public T save(T entity) {
		return getRepository().save(entity);
	}

	@Override
	public void delete(ID id) {
		getRepository().deleteById(id);
	}

	@Override
	public T get(ID id) {
		Optional<T> obj = getRepository().findById(id);
		if(obj.isPresent()) return obj.get();
		return null;
	}

	@Override
	public List<T> getAll() {
		List<T> list = new ArrayList<>();
		getRepository().findAll().forEach(obj -> list.add(obj));
		return list;
	}
	
	public abstract JpaRepository<T, ID> getRepository();

}
