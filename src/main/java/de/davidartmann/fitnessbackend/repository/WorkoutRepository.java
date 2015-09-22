package de.davidartmann.fitnessbackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.davidartmann.fitnessbackend.model.Exercise;
import de.davidartmann.fitnessbackend.model.User;
import de.davidartmann.fitnessbackend.model.Workout;
import de.davidartmann.fitnessbackend.model.WorkoutSession;

/**
 * {@link Repository} for the {@link Workout} model class.
 * @author David Artmann
 */
@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long> {

	public List<Workout> findAll();
	
	public List<Workout> findByName(String name);
	
	public List<Workout> findByWeekday(String weekday);
	
	public List<Workout> findByNumberOfDay(Integer numberOfDay);
	
	public List<Workout> findByExercises(Exercise exercise);
	
	public Workout findByWorkoutSessions(WorkoutSession workoutSession);
	
	public List<Workout> findByUser(User user);
}
