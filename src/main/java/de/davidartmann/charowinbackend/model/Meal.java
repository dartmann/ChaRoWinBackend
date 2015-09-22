package de.davidartmann.charowinbackend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;

/**
 * The model class for a meal.
 * It references the {@link Food} and gets references by the {@link Dietplan} with {@link OneToMany}.
 * @author David Artmann
 */
@Entity
@Table
public class Meal extends BaseModel {

	private static final long serialVersionUID = -7259485145497717782L;

	@Column
	private String name;
	
	@Column
	private String weekday;
	
	/**
	 * Long value represents the number of millis from epoch.
	 * We simply use the {@link DateTime#getHourOfDay()} and {@link DateTime#getMinuteOfDay()}
	 * to visualize it in sth. like "15:00" or "09:00".
	 */
	@Column(name="mealtime")
	private Long mealtime;
	
	@ManyToMany(targetEntity=Dietplan.class, mappedBy="meals")
	private List<Dietplan> dietplans;
	
	@ManyToMany(targetEntity=Food.class)
	private List<Food> foods;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public Long getMealtime() {
		return mealtime;
	}

	public void setMealtime(Long mealtime) {
		this.mealtime = mealtime;
	}

	public List<Dietplan> getDietplans() {
		return dietplans;
	}

	public void setDietplans(List<Dietplan> dietplans) {
		this.dietplans = dietplans;
	}
}
