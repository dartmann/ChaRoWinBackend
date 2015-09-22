package de.davidartmann.fitnessbackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.davidartmann.fitnessbackend.model.Exercise;
import de.davidartmann.fitnessbackend.model.Muscle;
import de.davidartmann.fitnessbackend.model.Workout;

/**
 * {@link Repository} for the {@link Exercise} model class.
 * @author David Artmann
 */
@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

	public List<Exercise> findAll();
	
	public List<Exercise> findByMuscles(Muscle muscle);
	
	public List<Exercise> findByWorkouts(Workout workout);
}
