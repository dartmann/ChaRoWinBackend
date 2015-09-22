package de.davidartmann.charowinbackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.davidartmann.charowinbackend.model.Dietplan;
import de.davidartmann.charowinbackend.model.Meal;
import de.davidartmann.charowinbackend.model.User;

/**
 * {@link Repository} for the {@link Dietplan} model class.
 * @author David Artmann
 */
@Repository
public interface DietplanRepository extends CrudRepository<Dietplan, Long> {

	public List<Dietplan> findAll();
	
	public List<Dietplan> findByName(String name);
	
	public List<Dietplan> findByUser(User user);
	
	public List<Dietplan> findByMeals(Meal meal);
}
