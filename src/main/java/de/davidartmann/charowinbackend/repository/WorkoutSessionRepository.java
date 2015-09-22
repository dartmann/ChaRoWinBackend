package de.davidartmann.charowinbackend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.davidartmann.charowinbackend.model.Workout;
import de.davidartmann.charowinbackend.model.WorkoutSession;

/**
 * {@link Repository} for the {@link WorkoutSession} model class.
 * @author David Artmann
 */
@Repository
public interface WorkoutSessionRepository extends CrudRepository<WorkoutSession, Long> {

	public List<WorkoutSession> findAll();
	
//	@Query("select w from WorkoutSession w where w.beginOfWorkout >= :date")
	public List<WorkoutSession> findByBeginOfWorkoutGreaterThanEqual(/*@Param("date") */Long beginDate);
	
	public List<WorkoutSession> findByEndOfWorkoutLessThanEqual(Long endDate);
	
	public List<WorkoutSession> findByWorkout(Workout workout);
}
