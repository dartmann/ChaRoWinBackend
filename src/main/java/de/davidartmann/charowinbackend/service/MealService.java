package de.davidartmann.charowinbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.davidartmann.charowinbackend.dto.MealDto;
import de.davidartmann.charowinbackend.model.Meal;
import de.davidartmann.charowinbackend.repository.MealRepository;

@Service
public class MealService implements IService<MealDto, Meal> {

	private static final Logger LOG = LoggerFactory.getLogger(MealService.class);
	
	@Autowired
	private MealRepository mealRepository;
	@Autowired
	private DietplanService dietplanService;
	@Autowired
	private FoodService foodService;
	
	/**
	 * Method to return a {@link Meal} by a given id
	 * @param id
	 * @return Meal or null
	 */
	public Meal getById(Long id) {
		Meal meal = null;
		if (id != null) {
			meal = mealRepository.findOne(id);
		} else {
			LOG.warn("Could not return Meal without id");
		}
		return meal;
	}
	
	/**
	 * Method to return a {@link List} of {@link Meal}s by a given {@link List} of ids
	 * @param ids
	 * @return List of Meals or empty list
	 */
	public List<Meal> getByIds(List<Long> ids) {
		List<Meal> meals = new ArrayList<Meal>();
		if (ids != null && !ids.isEmpty()) {
			Iterator<Meal> mealIterator = mealRepository.findAll(ids).iterator();
			while (mealIterator.hasNext()) {
				meals.add(mealIterator.next());
			}
		} else {
			LOG.warn("Could not return Meals without ids");
		}
		return meals;
	}
	
	/**
	 * Creates {@link Meal} by given {@link MealDto}
	 * @param mealDto
	 * @return Meal or null
	 */
	public Meal create(MealDto mealDto) {
		Meal meal = null;
		if (mealDto != null) {
			meal = new Meal();
			meal.setActive(mealDto.getActive());
			if (mealDto.getDietplanIds() != null 
					&& !mealDto.getDietplanIds().isEmpty()) {
				meal.setDietplans(
						dietplanService.getByIds(mealDto.getDietplanIds()));
			} else {
				LOG.debug("MealDto without dietplanIds");
			}
			if (mealDto.getFoodIds() != null && !mealDto.getFoodIds().isEmpty()) {
				meal.setFoods(foodService.getByIds(mealDto.getFoodIds()));
			} else {
				LOG.debug("MealDto without foodIds");
			}
			meal.setMealtime(mealDto.getMealtime());
			meal.setName(mealDto.getName());
			meal.setWeekday(mealDto.getWeekday());
			meal = mealRepository.save(meal);
			LOG.info("Created Meal with id {}", meal.getId());
		} else {
			LOG.warn("Could not create Meal without MealDto");
		}
		return meal;
	}
	
	/**
	 * Updates {@link Meal} by given id and {@link MealDto}
	 * @param id
	 * @param mealDto
	 * @return Meal or null
	 */
	public Meal updateById(Long id, MealDto mealDto) {
		Meal meal = null;
		if (id != null && mealDto != null) {
			meal = getById(id);
			if (meal != null) {
				meal.setActive(mealDto.getActive());
				if (mealDto.getDietplanIds() != null 
						&& !mealDto.getDietplanIds().isEmpty()) {
					meal.setDietplans(
							dietplanService.getByIds(mealDto.getDietplanIds()));
				} else {
					LOG.debug("MealDto without dietplanIds");
				}
				if (mealDto.getFoodIds() != null && !mealDto.getFoodIds().isEmpty()) {
					meal.setFoods(foodService.getByIds(mealDto.getFoodIds()));
				} else {
					LOG.debug("MealDto without foodIds");
				}
				meal.setMealtime(mealDto.getMealtime());
				meal.setName(mealDto.getName());
				meal.setWeekday(mealDto.getWeekday());
				meal = mealRepository.save(meal);
				LOG.info("Updated Meal with id {}", id);
			} else {
				LOG.warn("Could not update Meal with invalid id {}", id);
			}
		} else {
			LOG.warn("Could not update Meal without parameters");
		}
		return meal;
	}
	
	/**
	 * Deletes {@link Meal} by given id
	 * @param id
	 * @return true if Meal could be deleted or false otherwise
	 */
	public Boolean deleteById(Long id) {
		if (id != null) {
			mealRepository.delete(id);
			LOG.info("Deleted Meal with id {}", id);
			return true;
		}
		LOG.warn("Could not delete Meal without id");
		return false;
	}
	
	/**
	 * Method to return all {@link Meal}s
	 * @return List of Meals or empty list
	 */
	public List<Meal> getAll() {
		return mealRepository.findAll();
	}
}
