package de.davidartmann.charowinbackend.dto;

import java.util.List;

import de.davidartmann.charowinbackend.model.Food;

/**
 * Data transfer object class for the {@link Food} model class.
 * @author David Artmann
 */
public class FoodDto extends BaseDto {

	private String name;
	
	private Double weight;
	
	private Double weightProtein;
	
	private Double weightCarbohydrates;
	
	private Double weightFat;
	
	private List<Long> mealIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getWeightProtein() {
		return weightProtein;
	}

	public void setWeightProtein(Double weightProtein) {
		this.weightProtein = weightProtein;
	}

	public Double getWeightCarbohydrates() {
		return weightCarbohydrates;
	}

	public void setWeightCarbohydrates(Double weightCarbohydrates) {
		this.weightCarbohydrates = weightCarbohydrates;
	}

	public Double getWeightFat() {
		return weightFat;
	}

	public void setWeightFat(Double weightFat) {
		this.weightFat = weightFat;
	}

	public List<Long> getMealIds() {
		return mealIds;
	}

	public void setMealIds(List<Long> mealIds) {
		this.mealIds = mealIds;
	}
}
