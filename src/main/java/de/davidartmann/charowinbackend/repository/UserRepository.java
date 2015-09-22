package de.davidartmann.charowinbackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.davidartmann.charowinbackend.model.User;

/**
 * {@link Repository} for the {@link User} model class.
 * @author David Artmann
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findAll();
	
	public List<User> findByName(String name);
}
