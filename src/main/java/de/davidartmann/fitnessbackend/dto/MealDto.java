package de.davidartmann.fitnessbackend.dto;

import java.util.List;

import de.davidartmann.fitnessbackend.model.Meal;

/**
 * Data transfer object class for the {@link Meal} model class.
 * @author David Artmann
 */
public class MealDto extends BaseDto {

	private String name;
	
	private String weekday;
	
	private Long mealtime;

	private List<Long> dietplanIds;
	
	private List<Long> foodIds;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMealtime() {
		return mealtime;
	}

	public void setMealtime(Long mealtime) {
		this.mealtime = mealtime;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public List<Long> getDietplanIds() {
		return dietplanIds;
	}

	public void setDietplanIds(List<Long> dietplanIds) {
		this.dietplanIds = dietplanIds;
	}

	public List<Long> getFoodIds() {
		return foodIds;
	}

	public void setFoodIds(List<Long> foodIds) {
		this.foodIds = foodIds;
	}
}
