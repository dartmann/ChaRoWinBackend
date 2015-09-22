package de.davidartmann.fitnessbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The model class for a workout session.
 * It gets referenced by the {@link Workout} with {@link OneToMany}.
 * @author David Artmann
 */
@Entity
@Table
public class WorkoutSession extends BaseModel {

	private static final long serialVersionUID = 2607823810249291273L;

	@Column(name="begin_of_workout")
	private Long beginOfWorkout;
	
	@Column(name="end_of_workout")
	private Long endOfWorkout;
	
	@ManyToOne
	@JoinColumn(name="workout_id")
	private Workout workout;

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

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
}
