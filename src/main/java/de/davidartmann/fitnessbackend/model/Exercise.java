package de.davidartmann.fitnessbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The model class for a exercise. 
 * It references the {@link Muscle} and gets referenced by {@link Workout} with {@link OneToMany}.
 * @author David Artmann
 */
@Entity
@Table
public class Exercise extends BaseModel {

	private static final long serialVersionUID = -5038780650901827764L;

	@Column
	private String name;
	
	@ManyToMany(targetEntity=Muscle.class)
	private List<Muscle> muscles;
	
	@ManyToMany(targetEntity=Workout.class, mappedBy="exercises")
	private List<Workout> workouts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Muscle> getMuscles() {
		return muscles;
	}

	public void setMuscles(List<Muscle> muscles) {
		this.muscles = muscles;
	}

	public List<Workout> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}
}
