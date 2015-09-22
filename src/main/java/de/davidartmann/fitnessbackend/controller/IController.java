package de.davidartmann.fitnessbackend.controller;

import java.util.List;

public interface IController <D, M> {

	public List<M> getAll();
	
	public M getById(Long id);
	
	public List<M> getByIds(List<Long> ids);
	
	public Boolean deleteById(Long id);
	
	public M create(D dto);
	
	public M updateById(Long id, D dto);
}
