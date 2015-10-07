package de.davidartmann.charowinbackend.dto;

import java.util.List;


/**
 * The data transfer object class for the {@link de.davidartmann.charowinbackend.model.User} model class.
 * @author David Artmann
 */
public class UserDto extends BaseDto {

	private String name;
	
	private Double bodyWeight;
	
	private Double bodyHeight;
	
	private Integer age;
	
	private Double activityIndex;
	
	private List<Long> dietplanIds;
	
	private List<Long> workoutPlanIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBodyWeight() {
		return bodyWeight;
	}

	public void setBodyWeight(Double bodyWeight) {
		this.bodyWeight = bodyWeight;
	}

	public Double getBodyHeight() {
		return bodyHeight;
	}

	public void setBodyHeight(Double bodyHeight) {
		this.bodyHeight = bodyHeight;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getActivityIndex() {
		return activityIndex;
	}

	public void setActivityIndex(Double activityIndex) {
		this.activityIndex = activityIndex;
	}

	public List<Long> getDietplanIds() {
		return dietplanIds;
	}

	public void setDietplanIds(List<Long> dietplanIds) {
		this.dietplanIds = dietplanIds;
	}

	public List<Long> getWorkoutPlanIds() {
		return workoutPlanIds;
	}

	public void setWorkoutPlanIds(List<Long> workoutPlanIds) {
		this.workoutPlanIds = workoutPlanIds;
	}
}
