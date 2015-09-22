package de.davidartmann.charowinbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.davidartmann.charowinbackend.dto.WorkoutDto;
import de.davidartmann.charowinbackend.model.User;
import de.davidartmann.charowinbackend.model.Workout;
import de.davidartmann.charowinbackend.repository.WorkoutRepository;

@Service
public class WorkoutService implements IService<WorkoutDto, Workout> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WorkoutService.class);

	@Autowired
	private WorkoutRepository workoutRepository;
	@Autowired
	private ExerciseService exerciseService;
	@Autowired
	private UserService userService;
	@Autowired
	private WorkoutSessionService workoutSessionService;
	
	
	/**
	 * Method to get a {@link Workout} by its id
	 * @param id
	 * @return workout or null
	 */
	public Workout getById(Long id) {
		Workout workout = null;
		if (id != null) {
			workout = workoutRepository.findOne(id);
		} else {
			LOG.warn("Could not return Workout without id");
		}
		return workout;
	}
	
	/**
	 * Method to return a {@link List} of {@link Workout} by given ids
	 * @param ids
	 * @return list of workouts or empty list
	 */
	public List<Workout> getByIds(List<Long> ids) {
		List<Workout> workouts = new ArrayList<Workout>();
		if (ids != null && !ids.isEmpty()) {
			Iterator<Workout> workoutIterator = 
					workoutRepository.findAll(ids).iterator();
			while(workoutIterator.hasNext()) {
				workouts.add(workoutIterator.next());
			}
		} else {
			LOG.warn("Could not return Workouts without ids");
		}
		return workouts;
	}
	
	/**
	 * Creates {@link Workout} by given {@link WorkoutDto}
	 * @param dto
	 * @return Workout or null
	 */
	public Workout create(WorkoutDto dto) {
		Workout workout = null;
		if (dto != null) {
			workout = new Workout();
			workout.setActive(dto.getActive());
			if (dto.getExerciseIds() != null 
					&& !dto.getExerciseIds().isEmpty()) {
				workout.setExercises(
						exerciseService.getByIds(dto.getExerciseIds()));
			} else {
				LOG.debug("WorkoutDto without exerciseIds");
			}			
			workout.setName(dto.getName());
			workout.setNumberOfDay(dto.getNumberOfDay());
			if (dto.getUserId() != null) {
				User user = userService.getById(dto.getUserId());
				if (user != null) {
					workout.setUser(user);
				} else {
					LOG.debug("WorkoutDto with invalid userId");
				}
			} else {
				LOG.debug("WorkoutDto without userId");
			}			
			workout.setWeekday(dto.getWeekday());
			if (dto.getWorkoutSessionIds() != null 
					&& !dto.getWorkoutSessionIds().isEmpty()) {
				workout.setWorkoutSessions(
						workoutSessionService.getByIds(
								dto.getWorkoutSessionIds()));
			} else {
				LOG.debug("WorkoutDto without workoutSessionIds");
			}
			workout = workoutRepository.save(workout);
			LOG.info("Created Workout with id {}", workout.getId());
		} else {
			LOG.warn("Could not create Workout without WorkoutDto");
		}
		return workout;
	}
	
	/**
	 * Updates {@link Workout} by given id and {@link WorkoutDto}
	 * @param id
	 * @param workoutDto
	 * @return Workout or null
	 */
	public Workout updateById(Long id, WorkoutDto workoutDto) {
		Workout workout = null;
		if (workoutDto != null) {
			workout = getById(id);
			if (workout != null) {
				workout.setActive(workoutDto.getActive());
				if (workoutDto.getExerciseIds() != null 
						&& !workoutDto.getExerciseIds().isEmpty()) {
					workout.setExercises(
							exerciseService.getByIds(
									workoutDto.getExerciseIds()));
				} else {
					LOG.debug("WorkoutDto without exerciseIds");
				}			
				workout.setName(workoutDto.getName());
				workout.setNumberOfDay(workoutDto.getNumberOfDay());
				if (workoutDto.getUserId() != null) {
					workout.setUser(userService.getById(workoutDto.getUserId()));
				} else {
					LOG.debug("WorkoutDto without userId");
				}			
				workout.setWeekday(workoutDto.getWeekday());
				if (workoutDto.getWorkoutSessionIds() != null 
						&& !workoutDto.getWorkoutSessionIds().isEmpty()) {
					workout.setWorkoutSessions(
							workoutSessionService.getByIds(
									workoutDto.getWorkoutSessionIds()));
				} else {
					LOG.debug("WorkoutDto without workoutSessionIds");
				}
				workout = workoutRepository.save(workout);
				LOG.info("Updated Workout with id {}", workout.getId());
			} else {
				LOG.warn("Could not update Workout with invalid id {}", id);
			}
		} else {
			LOG.warn("Could not update Workout without parameters");
		}
		return workout;
	}
	
	/**
	 * Method to delete a {@link Workout} by its id
	 * @param id
	 * @return <code>true</code> if {@link Workout} was deleted or <code>false</code> if not
	 */
	public Boolean deleteById(Long id) {
		if (id != null) {
			workoutRepository.delete(id);
			LOG.info("Deleted Workout with id {}", id);
			return true;
		}
		LOG.warn("Could not delete Workout without id");
		return false;
	}

	/**
	 * Method to get all {@link Workout}s
	 * @return List of Workouts or empty list
	 */
	public List<Workout> getAll() {
		return workoutRepository.findAll();
	}
}
