package de.davidartmann.fitnessbackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.davidartmann.fitnessbackend.model.Dietplan;
import de.davidartmann.fitnessbackend.model.Food;
import de.davidartmann.fitnessbackend.model.Meal;

/**
 * {@link Repository} for the {@link Meal} model class.
 * @author David Artmann
 */
@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {

	public List<Meal> findAll();
	
	public List<Meal> findByName(String name);
	
	public List<Meal> findByWeekday(String weekday);
	
	public List<Meal> findByMealtime(Long mealtime);
	
	public List<Meal> findByDietplans(Dietplan dietplan);
	
	public List<Meal> findByFoods(Food food);
}
