package de.davidartmann.charowinbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Interface for all {@link Service} annotated classes.
 * @author David Artmann
 *
 * @param <D> the data transfer object class
 * @param <M> the model class
 */
public interface IService <D, M> {

	public List<M> getAll();
	
	public M getById(Long id);
	
	public List<M> getByIds(List<Long> ids);
	
	public M create(D dto);
	
	public M updateById(Long id, D dto);
	
	public Boolean deleteById(Long id);
}
