package de.davidartmann.charowinbackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.davidartmann.charowinbackend.model.Food;
import de.davidartmann.charowinbackend.model.Meal;

/**
 * {@link Repository} for the {@link Food} model class.
 * @author David Artmann
 */
@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {

	public List<Food> findAll();
	
	public List<Food> findByName(String name);
	
	public List<Food> findByMeals(Meal meal);
	
	public List<Food> findByWeight(Double weight);
}
