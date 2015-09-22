package de.davidartmann.fitnessbackend.dto;

import java.util.List;

import de.davidartmann.fitnessbackend.model.Dietplan;

/**
 * Data transfer object class for the {@link Dietplan} model class.
 * @author David Artmann
 */
public class DietplanDto extends BaseDto {

	private String name;
	
	private List<Long> mealIds;
	
	private Long userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getMealIds() {
		return mealIds;
	}

	public void setMealIds(List<Long> mealIds) {
		this.mealIds = mealIds;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
