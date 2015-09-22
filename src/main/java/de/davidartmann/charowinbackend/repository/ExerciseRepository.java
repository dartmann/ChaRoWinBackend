package de.davidartmann.charowinbackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.davidartmann.charowinbackend.model.Exercise;
import de.davidartmann.charowinbackend.model.Muscle;
import de.davidartmann.charowinbackend.model.Workout;

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
