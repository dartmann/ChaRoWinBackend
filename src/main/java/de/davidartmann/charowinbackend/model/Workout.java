package de.davidartmann.charowinbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The model class for a workout.
 * It references the {@link Exercise} and gets referenced by {@link WorkoutPlan} with {@link OneToMany}.
 * @author David Artmann
 */
@Entity
@Table
public class Workout extends BaseModel {
	
	private static final long serialVersionUID = 8064368117813586743L;

	@Column
	private String name;
	
	@Column
	private String weekday;
	
	@Column(length=2)
	private Integer numberOfDay;
	
	@ManyToMany(targetEntity=Exercise.class)
	private List<Exercise> exercises;
	
	@OneToMany(mappedBy="workout", fetch=FetchType.EAGER)
	private List<WorkoutSession> workoutSessions;
	
	@ManyToOne
	@JoinColumn(name="workoutPlan_id")
	private WorkoutPlan workoutPlan;

	public WorkoutPlan getWorkoutPlan() {
		return workoutPlan;
	}

	public void setWorkoutPlan(WorkoutPlan workoutPlan) {
		this.workoutPlan = workoutPlan;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public Integer getNumberOfDay() {
		return numberOfDay;
	}

	public void setNumberOfDay(Integer numberOfDay) {
		this.numberOfDay = numberOfDay;
	}

	public List<WorkoutSession> getWorkoutSessions() {
		return workoutSessions;
	}

	public void setWorkoutSessions(List<WorkoutSession> workoutSessions) {
		this.workoutSessions = workoutSessions;
	}
}
