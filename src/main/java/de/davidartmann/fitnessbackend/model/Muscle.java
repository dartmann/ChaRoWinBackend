package de.davidartmann.fitnessbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The model class for a muscel, the simplest element in the sports sector of this application.
 * It gets referenced by the {@link Exercise} with {@link OneToMany}.
 * @author David Artmann
 */
@Entity
@Table
public class Muscle extends BaseModel {

	private static final long serialVersionUID = -8788959856140179826L;

	@Column
	private String name;

	@ManyToMany(targetEntity=Exercise.class, mappedBy="muscles")
	private List<Exercise> exercises;
	
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
}
