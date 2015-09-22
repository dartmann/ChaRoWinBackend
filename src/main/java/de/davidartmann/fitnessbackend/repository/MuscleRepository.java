package de.davidartmann.fitnessbackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.davidartmann.fitnessbackend.model.Exercise;
import de.davidartmann.fitnessbackend.model.Muscle;

/**
 * {@link Repository} for the {@link Muscle} model class.
 * @author David Artmann
 */
@Repository
public interface MuscleRepository extends CrudRepository<Muscle, Long> {

	public List<Muscle> findAll();
	
	public List<Muscle> findByName(String name);
	
	public List<Muscle> findByExercises(Exercise exercise);
}
