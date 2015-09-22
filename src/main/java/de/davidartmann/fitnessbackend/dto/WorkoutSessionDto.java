package de.davidartmann.fitnessbackend.dto;


/**
 * Data transfer object class for the {@link WorkoutSessionDto} model class.
 * @author David Artmann
 */
public class WorkoutSessionDto extends BaseDto {

	private Long beginOfWorkout;
	
	private Long endOfWorkout;
	
	private Long workoutId;

	public Long getBeginOfWorkout() {
		return beginOfWorkout;
	}

	public void setBeginOfWorkout(Long beginOfWorkout) {
		this.beginOfWorkout = beginOfWorkout;
	}

	public Long getEndOfWorkout() {
		return endOfWorkout;
	}

	public void setEndOfWorkout(Long endOfWorkout) {
		this.endOfWorkout = endOfWorkout;
	}

	public Long getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(Long workoutId) {
		this.workoutId = workoutId;
	}
}
