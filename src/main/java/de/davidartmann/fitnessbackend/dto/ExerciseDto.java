package de.davidartmann.fitnessbackend.dto;

import java.util.List;

import de.davidartmann.fitnessbackend.model.Exercise;

/**
 * Data transfer object class for the {@link Exercise} model class.
 * @author David Artmann
 */
public class ExerciseDto extends BaseDto {

	private String name;
	
	private List<Long> muscleIds;
	
	private List<Long> workoutIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getWorkoutIds() {
		return workoutIds;
	}

	public void setWorkoutIds(List<Long> workoutIds) {
		this.workoutIds = workoutIds;
	}

	public List<Long> getMuscleIds() {
		return muscleIds;
	}

	public void setMuscleIds(List<Long> muscleIds) {
		this.muscleIds = muscleIds;
	}
}
