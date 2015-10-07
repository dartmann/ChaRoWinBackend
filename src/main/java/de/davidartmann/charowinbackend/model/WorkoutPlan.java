package de.davidartmann.charowinbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The model class for a workout.
 * It references the {@link Workout} and gets referenced by {@link User} with {@link OneToMany}.
 * @author David Artmann
 */
@Entity
@Table
public class WorkoutPlan extends BaseModel {

	private static final long serialVersionUID = 3129852393631676804L;
	
	@Column
	private String name;
	
	@Column
	private Boolean current;
	
	@Column
	private String description;
	
	@Column
	private Integer amountDays;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy="workoutPlan", fetch=FetchType.EAGER)
	private List<Workout> workouts;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Workout> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}
}
