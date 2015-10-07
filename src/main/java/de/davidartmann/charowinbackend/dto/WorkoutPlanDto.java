package de.davidartmann.charowinbackend.dto;

import java.util.List;

import de.davidartmann.charowinbackend.model.WorkoutPlan;

/**
 * Data transfer object class for the {@link WorkoutPlan} model class.
 * @author David Artmann
 */
public class WorkoutPlanDto extends BaseDto {

	private String name;
	
	private Boolean current;
	
	private String description;
	
	private Integer amountDays;
	
	private Long userId;
	
	private List<Long> workoutIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCurrent() {
		return current;
	}

	public void setCurrent(Boolean current) {
		this.current = current;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAmountDays() {
		return amountDays;
	}

	public void setAmountDays(Integer amountDays) {
		this.amountDays = amountDays;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getWorkoutIds() {
		return workoutIds;
	}

	public void setWorkoutIds(List<Long> workoutIds) {
		this.workoutIds = workoutIds;
	}
}
