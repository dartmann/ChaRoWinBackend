package de.davidartmann.charowinbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The model class for the dietplan.
 * It references the {@link Meal} and gets referenced by the {@link User} with {@link OneToMany}.
 * @author David Artmann
 */
@Entity
@Table
public class Dietplan extends BaseModel {

	private static final long serialVersionUID = 6437840072509181822L;

	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToMany(targetEntity=Meal.class)
	private List<Meal> meals;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
}
