package de.davidartmann.fitnessbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.davidartmann.fitnessbackend.dto.WorkoutSessionDto;
import de.davidartmann.fitnessbackend.model.Workout;
import de.davidartmann.fitnessbackend.model.WorkoutSession;
import de.davidartmann.fitnessbackend.repository.WorkoutSessionRepository;

@Service
public class WorkoutSessionService 
	implements IService<WorkoutSessionDto, WorkoutSession> {

	private static final Logger LOG = 
			LoggerFactory.getLogger(WorkoutSessionService.class);

	@Autowired
	private WorkoutSessionRepository workoutSessionRepository;
	@Autowired
	private WorkoutService workoutService;
	
	/**
	 * Returns {@link WorkoutSession} by given id
	 * @param id
	 * @return WorkoutSession or null
	 */
	public WorkoutSession getById(Long id) {
		WorkoutSession workoutSession = null;
		if (id != null) {
			workoutSession = workoutSessionRepository.findOne(id);
		} else {
			LOG.warn("Could not return WorkoutSession without id");
		}
		return workoutSession;
	}
	
	/**
	 * Returns {@link List} of {@link WorkoutSession}s by given {@link List} of ids
	 * @param ids
	 * @return List of WorkoutSessions or empty list
	 */
	public List<WorkoutSession> getByIds(List<Long> ids) {
		List<WorkoutSession> workoutSessions = new ArrayList<WorkoutSession>();
		if (ids != null) {
			Iterator<WorkoutSession> workoutSessionIterator = 
					workoutSessionRepository.findAll(ids).iterator();
			while(workoutSessionIterator.hasNext()) {
				workoutSessions.add(workoutSessionIterator.next());
			}
		} else {
			LOG.warn("Could not return WorkoutSessions without ids");
		}
		return workoutSessions;
	}
	
	/**
	 * Creates {@link WorkoutSession} by given {@link WorkoutSessionDto}
	 * @param sessionDto
	 * @return WorkoutSession or null
	 */
	public WorkoutSession create(WorkoutSessionDto sessionDto) {
		WorkoutSession session = null;
		if (sessionDto != null) {
			session = new WorkoutSession();
			session.setActive(true);
			session.setBeginOfWorkout(sessionDto.getBeginOfWorkout());
			session.setEndOfWorkout(sessionDto.getEndOfWorkout());
			if (sessionDto.getWorkoutId() != null) {
				Workout workout = 
						workoutService.getById(sessionDto.getWorkoutId());
				if (workout != null) {
					session.setWorkout(workout);
				} else {
					LOG.debug("WorkoutSessionDto with invalid workoutId");
				}
			} else {
				LOG.debug("WorkoutSessionDto without workoutId");
			}
			session = workoutSessionRepository.save(session);
			LOG.info("Created WorkoutSession with id {}", session.getId());
		} else {
			LOG.warn("Could not create WorkoutSession without id");
		}
		return session;
	}
	/**
	 * Updates {@link WorkoutSession} by given id and {@link WorkoutSessionDto}
	 * @param id
	 * @param sessionDto
	 * @return WorkoutSession or null
	 */
	public WorkoutSession updateById(Long id, WorkoutSessionDto sessionDto) {
		WorkoutSession session = null;
		if (sessionDto != null) {
			session = getById(id);
			if (session != null) {
				session.setActive(sessionDto.getActive());
				session.setBeginOfWorkout(sessionDto.getBeginOfWorkout());
				session.setEndOfWorkout(sessionDto.getEndOfWorkout());
				if (sessionDto.getWorkoutId() != null) {
					Workout workout = 
							workoutService.getById(sessionDto.getWorkoutId());
					if (workout != null) {
						session.setWorkout(workout);
					} else {
						LOG.debug("WorkoutSessionDto with invalid workoutId");
					}
				} else {
					LOG.debug("WorkoutSessionDto without workoutId");
				}
				session = workoutSessionRepository.save(session);
				LOG.info("Updated WorkoutSession with id {}", session.getId());
			} else {
				LOG.warn("Could not update WorkoutSession with invalid id {}", id);
			}
		} else {
			LOG.warn("Could not update WorkoutSession without id");
		}
		return session;
	}
	
	/**
	 * Deletes {@link WorkoutSession} by given id
	 * @param id
	 * @return true if WorkoutSession could be deleted or false otherwise
	 */
	public Boolean deleteById(Long id) {
		if (id != null) {
			workoutSessionRepository.delete(id);
			LOG.info("Deleted WorkoutSession with id {}", id);
			return true;
		}
		LOG.warn("Could not delete WorkoutSession without id");
		return false;
	}
	
	/**
	 * Method to return all {@link WorkoutSession}s
	 * @return List of WorkoutSessions or empty list
	 */
	public List<WorkoutSession> getAll() {
		return workoutSessionRepository.findAll();
	}
}
