package de.davidartmann.fitnessbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The model class for a user, the logically highest element in this application's database structure.
 * It references the {@link Workout} with {@link OneToMany}.
 * @author David Artmann
 */
@Entity
@Table
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class User extends BaseModel {

	private static final long serialVersionUID = -6140052682789440497L;

	@Column
	private String name;
	
	@Column(name="body_weight")
	private Double bodyWeight;
	
	@Column(name="body_height")
	private Double bodyHeight;
	
	@Column(length=3)
	private Integer age;
	
	@Column(name="activity_index")
	private Double activityIndex;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Workout> workouts;

	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Dietplan> dietplans;

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

	public List<Workout> getWorkouts() {
		return workouts;
	}
	
	public List<Dietplan> getDietplans() {
		return dietplans;
	}

	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}

	public void setDietplans(List<Dietplan> dietplans) {
		this.dietplans = dietplans;
	}
}
