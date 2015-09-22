package de.davidartmann.fitnessbackend.dto;

import java.util.List;

import de.davidartmann.fitnessbackend.model.Workout;

/**
 * Data transfer object class for the {@link Workout} model class.
 * @author David Artmann
 */
public class WorkoutDto extends BaseDto {

	private String name;
	
	private String weekday;
	
	private Integer numberOfDay;
	
	private List<Long> exerciseIds;
	
	private List<Long> workoutSessionIds;
	
	private Long userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public Integer getNumberOfDay() {
		return numberOfDay;
	}

	public void setNumberOfDay(Integer numberOfDay) {
		this.numberOfDay = numberOfDay;
	}

	public List<Long> getExerciseIds() {
		return exerciseIds;
	}

	public void setExerciseIds(List<Long> exerciseIds) {
		this.exerciseIds = exerciseIds;
	}

	public List<Long> getWorkoutSessionIds() {
		return workoutSessionIds;
	}

	public void setWorkoutSessionIds(List<Long> workoutSessionIds) {
		this.workoutSessionIds = workoutSessionIds;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
