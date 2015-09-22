package de.davidartmann.charowinbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.davidartmann.charowinbackend.dto.FoodDto;
import de.davidartmann.charowinbackend.model.Food;
import de.davidartmann.charowinbackend.repository.FoodRepository;

@Service
public class FoodService implements IService<FoodDto, Food> {

	private static final Logger LOG = LoggerFactory.getLogger(FoodService.class);
	
	@Autowired
	private FoodRepository foodRepository;
	@Autowired
	private MealService mealService;
	
	/**
	 * Returns {@link Food} by given id
	 * @param id
	 * @return Food or null
	 */
	public Food getById(Long id) {
		Food food = null;
		if (id != null) {
			food = foodRepository.findOne(id);
		} else {
			LOG.warn("Could not return Food without id");
		}
		return food;
	}
	
	/**
	 * Returns {@link List} of {@link Food}s by {@link List} of ids
	 * @param foodIds
	 * @return List of Food or empty list
	 */
	public List<Food> getByIds(List<Long> foodIds) {
		List<Food> foods = new ArrayList<Food>();
		if (foodIds != null && !foodIds.isEmpty()) {
			Iterator<Food> foodIterator = foodRepository.findAll(foodIds).iterator();
			while (foodIterator.hasNext()) {
				foods.add(foodIterator.next());
			}
		} else {
			LOG.warn("Could not return Foods without ids");
		}
		return foods;
	}
	
	/**
	 * Creates {@link Food} by given {@link FoodDto}
	 * @param foodDto
	 * @return Food or null
	 */
	public Food create(FoodDto foodDto) {
		Food food = null;
		if (foodDto != null) {
			food = new Food();
			food.setActive(foodDto.getActive());
			if (foodDto.getMealIds() != null && !foodDto.getMealIds().isEmpty()) {
				food.setMeals(mealService.getByIds(foodDto.getMealIds()));
			} else {
				LOG.debug("FoodDto without mealIds");
			}		
			food.setName(foodDto.getName());
			food.setWeight(foodDto.getWeight());
			food.setWeightCarbohydrates(foodDto.getWeightCarbohydrates());
			food.setWeightFat(foodDto.getWeightFat());
			food.setWeightProtein(foodDto.getWeightProtein());
			food = foodRepository.save(food);
			LOG.info("Created Food with id {}", food.getId());
		} else {
			LOG.warn("Could not create Food without FoodDto");
		}
		return food;
	}
	
	/**
	 * Updates {@link Food} by given id and {@link FoodDto}
	 * @param id
	 * @param foodDto
	 * @return Food or null
	 */
	public Food updateById(Long id, FoodDto foodDto) {
		Food food = null;
		if (id != null && foodDto != null) {
			food = getById(id);
			if (food != null) {
				food.setActive(foodDto.getActive());
				if (foodDto.getMealIds() != null && !foodDto.getMealIds().isEmpty()) {
					food.setMeals(mealService.getByIds(foodDto.getMealIds()));
				} else {
					LOG.debug("FoodDto without mealIds");
				}
				food.setName(foodDto.getName());
				food.setWeight(foodDto.getWeight());
				food.setWeightCarbohydrates(foodDto.getWeightCarbohydrates());
				food.setWeightFat(foodDto.getWeightFat());
				food.setWeightProtein(foodDto.getWeightProtein());
				food = foodRepository.save(food);
				LOG.info("Updated Food with id {}", food.getId());
			} else {
				LOG.warn("Could not update Food with invalid id {}", id);
			}
		} else {
			LOG.warn("Could not update Food without parameters");
		}
		return food;
	}
	
	/**
	 * Deletes {@link Food} by given id
	 * @param id
	 * @return true if Food could be deleted or false otherwise
	 */
	public Boolean deleteById(Long id) {
		if (id != null) {
			foodRepository.delete(id);
			LOG.info("Deleted Food with id {}", id);
			return true;
		}
		LOG.warn("Could not delete Food without id");
		return false;
	}
	
	/**
	 * Method to get all {@link Food}s
	 * @return List of Foods or empty list
	 */
	public List<Food> getAll() {
		return foodRepository.findAll();
	}
}
