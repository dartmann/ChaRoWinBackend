package de.davidartmann.charowinbackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.davidartmann.charowinbackend.model.User;
import de.davidartmann.charowinbackend.model.Workout;
import de.davidartmann.charowinbackend.model.WorkoutPlan;

/**
 * {@link Repository} for the {@link WorkoutPlan} model class.
 * @author David Artmann
 */
@Repository
public interface WorkoutPlanRepository extends CrudRepository<WorkoutPlan, Long> {

	public List<WorkoutPlan> findAll();
	
	public List<WorkoutPlan> findByName(String name);
	
	public List<WorkoutPlan> findByDescription(String description);
	
	public List<WorkoutPlan> findByAmountDays(Integer amountDays);
	
	public List<WorkoutPlan> findByUser(User user);
	
	public WorkoutPlan findByWorkouts(Workout workout);
}
