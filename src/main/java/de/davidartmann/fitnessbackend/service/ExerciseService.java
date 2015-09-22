package de.davidartmann.fitnessbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.davidartmann.fitnessbackend.dto.ExerciseDto;
import de.davidartmann.fitnessbackend.model.Exercise;
import de.davidartmann.fitnessbackend.repository.ExerciseRepository;

@Service
public class ExerciseService implements IService<ExerciseDto, Exercise> {

	private static final Logger LOG = LoggerFactory.getLogger(ExerciseService.class);
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	@Autowired
	private WorkoutService workoutService;
	@Autowired
	private MuscleService muscleService;
	
	/**
	 * Method to get all {@link Exercise}s
	 * @return List of Exercises or empty list
	 */
	public List<Exercise> getAll() {
		return exerciseRepository.findAll();
	}
	
	/**
	 * Method to get a {@link Exercise} by its id
	 * @param id
	 * @return Exercise or null
	 */
	public Exercise getById(Long id) {
		Exercise exercise = null;
		if (id != null) {
			exercise = exerciseRepository.findOne(id);
		} else {
			LOG.warn("Could not get a Exercise without id");
		}
		return exercise;
	}
	
	/**
	 * Method to return a {@link List} of {@link Exercise}s by a given {@link List} of ids
	 * @param exerciseIds
	 * @return List of Exercises or empty list
	 */
	public List<Exercise> getByIds(List<Long> exerciseIds) {
		List<Exercise> exercises = new ArrayList<Exercise>();
		if (exerciseIds != null && !exerciseIds.isEmpty()) {
			Iterator<Exercise> exerciseIterator = exerciseRepository.findAll(exerciseIds).iterator();
			while(exerciseIterator.hasNext()) {
				exercises.add(exerciseIterator.next());
			}
		} else {
			LOG.warn("Could not return Exercises withouts ids");
		}
		return exercises;
	}
	
	/**
	 * Method to create an {@link Exercise} by a given id
	 * @param dto
	 * @return Exercise or null
	 */
	public Exercise create(ExerciseDto dto) {
		Exercise exercise = null;
		if (dto != null) {
			exercise = new Exercise();
			exercise.setActive(dto.getActive());
			if (dto.getMuscleIds() != null && 
					!dto.getMuscleIds().isEmpty()) {
				exercise.setMuscles(muscleService.getByIds(dto.getMuscleIds()));
			} else {
				LOG.debug("ExerciseDto without muscleIds");
			}
			exercise.setName(dto.getName());
			if (dto.getWorkoutIds() != null &&
					!dto.getWorkoutIds().isEmpty()) {
				exercise.setWorkouts(workoutService.getByIds(dto.getWorkoutIds()));
			} else {
				LOG.debug("ExerciseDto without workoutIds");
			}
			exercise = exerciseRepository.save(exercise);
			LOG.info("Created Exercise with id {}", exercise.getId());
		} else {
			LOG.warn("Could not create Exercise without id");
		}
		return exercise;
	}
	
	/**
	 * Updates {@link Exercise} by given id and {@link ExerciseDto}
	 * @param id
	 * @param dto
	 * @return Exercise or null
	 */
	public Exercise updateById(Long id, ExerciseDto dto) {
		Exercise exercise = null;
		if (id != null && dto != null) {
			exercise = getById(id);
			if (exercise != null) {
				exercise.setActive(dto.getActive());
				if (dto.getMuscleIds() != null && 
						!dto.getMuscleIds().isEmpty()) {
					exercise.setMuscles(
							muscleService.getByIds(dto.getMuscleIds()));
				} else {
					LOG.debug("ExerciseDto without muscleIds");
				}
				exercise.setName(dto.getName());
				if (dto.getWorkoutIds() != null &&
						!dto.getWorkoutIds().isEmpty()) {
					exercise.setWorkouts(
							workoutService.getByIds(
									dto.getWorkoutIds()));
				} else {
					LOG.debug("ExerciseDto without workoutIds");
				}
				exercise = exerciseRepository.save(exercise);
				LOG.info("Updated Exercise with id {}", id);
			} else {
				LOG.warn("Could not update Exercise with invalid id");
			}
		} else {
			LOG.warn("Could not update Exercise without parameters");
		}
		return exercise;
	}
	
	/**
	 * Deletes {@link Exercise} by given id
	 * @param id
	 * @return true if Exercise could be deleted or false otherwise
	 */
	public Boolean deleteById(Long id) {
		if (id != null) {
			exerciseRepository.delete(id);
			LOG.info("Deleted Exercise with id {}", id);
			return true;
		}
		LOG.warn("Could not delete Exercise without id");
		return false;
	}
}
