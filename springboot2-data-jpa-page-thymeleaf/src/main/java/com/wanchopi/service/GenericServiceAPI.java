package com.wanchopi.service;

import java.io.Serializable;
import java.util.List;

/**
 * GenericServiceAPI, interface where the basic methods are declared.
 * 
 * @author Wanchopi
 *
 * @param <T>
 * @param <ID>
 */
public interface GenericServiceAPI<T, ID extends Serializable> {
	
	T save(T entity);
	void delete(ID id);
	T get(ID id);
	List<T> getAll();

}
