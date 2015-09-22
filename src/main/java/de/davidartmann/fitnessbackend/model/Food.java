package de.davidartmann.fitnessbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The model class for a partial meal, the simplest element in the nutrition sector of this application.
 * It gets referenced by {@link Meal} with {@link OneToMany}.
 * @author David Artmann
 */
@Entity
@Table
public class Food extends BaseModel {

	private static final long serialVersionUID = -4274298052039693162L;

	@Column
	private String name;
	
	@Column(name="weight")
	private Double weight;
	
	@Column(name="weight_protein")
	private Double weightProtein;
	
	@Column(name="weight_carbohydrates")
	private Double weightCarbohydrates;
	
	@Column(name="weight_fat")
	private Double weightFat;
	
	@ManyToMany(targetEntity=Meal.class, mappedBy="foods")
	private List<Meal> meals;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeightProtein() {
		return weightProtein;
	}

	public void setWeightProtein(Double weightProtein) {
		this.weightProtein = weightProtein;
	}

	public Double getWeightCarbohydrates() {
		return weightCarbohydrates;
	}

	public void setWeightCarbohydrates(Double weightCarbohydrates) {
		this.weightCarbohydrates = weightCarbohydrates;
	}

	public Double getWeightFat() {
		return weightFat;
	}

	public void setWeightFat(Double weightFat) {
		this.weightFat = weightFat;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
