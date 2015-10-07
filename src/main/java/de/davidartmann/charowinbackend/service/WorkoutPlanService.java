package de.davidartmann.charowinbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.davidartmann.charowinbackend.dto.WorkoutPlanDto;
import de.davidartmann.charowinbackend.model.User;
import de.davidartmann.charowinbackend.model.WorkoutPlan;
import de.davidartmann.charowinbackend.repository.WorkoutPlanRepository;

@Service
public class WorkoutPlanService implements IService<WorkoutPlanDto, WorkoutPlan> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WorkoutPlanService.class);
	
	@Autowired
	private WorkoutPlanRepository workoutPlanRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private WorkoutService workoutService;

	/**
	 * Method to get all {@link WorkoutPlan}s
	 * @return list of workouts or empty list
	 */
	public List<WorkoutPlan> getAll() {
		return workoutPlanRepository.findAll();
	}

	/**
	 * Method to get a {@link WorkoutPlan} by its id
	 * @param id
	 * @return workoutplan or null
	 */
	public WorkoutPlan getById(Long id) {
		WorkoutPlan workoutPlan = null;
		if (id != null) {
			workoutPlan = workoutPlanRepository.findOne(id);
		} else {
			LOG.warn("Could not return WorkoutPlan without id");
		}
		return workoutPlan;
	}

	/**
	 * Method to return a {@link List} of {@link WorkoutPlan} by given ids
	 * @param ids
	 * @return List of WorkoutPlans or empty list
	 */
	public List<WorkoutPlan> getByIds(List<Long> ids) {
		List<WorkoutPlan> workoutPlans = new ArrayList<>();
		if (ids != null && !ids.isEmpty()) {
			Iterator<WorkoutPlan> workoutPlanIterator = 
					workoutPlanRepository.findAll(ids).iterator();
			while(workoutPlanIterator.hasNext()) {
				workoutPlans.add(workoutPlanIterator.next());
			}
		} else {
			LOG.warn("Could not return WorkoutPlans without ids");
		}
		return workoutPlans;
	}

	/**
	 * Creates {@link WorkoutPlan} by given {@link WorkoutPlanDto}
	 * @param dto
	 * @return WorkoutPlan or null
	 */
	public WorkoutPlan create(WorkoutPlanDto dto) {
		WorkoutPlan workoutPlan = null;
		if (dto != null) {
			workoutPlan = new WorkoutPlan();
			workoutPlan.setActive(dto.getActive());
			workoutPlan.setAmountDays(dto.getAmountDays());
			workoutPlan.setCurrent(dto.getCurrent());
			workoutPlan.setDescription(dto.getDescription());
			workoutPlan.setName(dto.getName());
			if (dto.getUserId() != null) {
				User user = userService.getById(dto.getUserId());
				if (user != null) {
					workoutPlan.setUser(user);
				} else {
					LOG.debug("WorkoutPlanDto with invalid userId");
				}
			} else {
				LOG.debug("WorkoutPlanDto without userId");
			}
			if (dto.getWorkoutIds() != null 
					&& !dto.getWorkoutIds().isEmpty()) {
				workoutPlan.setWorkouts(workoutService.getByIds(dto.getWorkoutIds()));				
			} else {
				LOG.debug("WorkoutPlanDto without workoutIds");
			}
			workoutPlan = workoutPlanRepository.save(workoutPlan);
			LOG.info("Created WorkoutPlan with id {}", workoutPlan.getId());
		} else {
			LOG.warn("Could not create WorkoutPlan without WorkoutPlanDto");
		}
		return workoutPlan;
	}

	/**
	 * Updates {@link WorkoutPlan} by given id and {@link WorkoutPlanDto}
	 * @param id
	 * @param dto
	 * @return WorkoutPlan or null
	 */
	public WorkoutPlan updateById(Long id, WorkoutPlanDto dto) {
		WorkoutPlan workoutPlan = null;
		if (id != null && dto != null) {
			workoutPlan = getById(id);
			if (workoutPlan != null) {
				workoutPlan.setActive(dto.getActive());
				workoutPlan.setAmountDays(dto.getAmountDays());
				workoutPlan.setCurrent(dto.getCurrent());
				workoutPlan.setDescription(dto.getDescription());
				workoutPlan.setName(dto.getName());
				if (dto.getUserId() != null) {
					User user = userService.getById(dto.getUserId());
					if (user != null) {
						workoutPlan.setUser(user);
					} else {
						LOG.debug("WorkoutPlanDto with invalid userId");
					}
				} else {
					LOG.debug("WorkoutPlanDto without userId");
				}
				if (dto.getWorkoutIds() != null 
						&& !dto.getWorkoutIds().isEmpty()) {
					workoutPlan.setWorkouts(workoutService.getByIds(dto.getWorkoutIds()));				
				} else {
					LOG.debug("WorkoutPlanDto without workoutIds");
				}
				workoutPlan = workoutPlanRepository.save(workoutPlan);
				LOG.info("Updated WorkoutPlan with id {}", workoutPlan.getId());
			} else {
				LOG.warn("Could not update WorkoutPlan with invalid id {}", id);
			}
		} else {
			LOG.warn("Could not update WorkoutPlan without parameters");
		}
		return workoutPlan;
	}

	/**
	 * Method to delete a {@link WorkoutPlan} by its id
	 * @param id
	 * @return <code>true</code> if {@link WorkoutPlan} was deleted or <code>false</code> if not
	 */
	public Boolean deleteById(Long id) {
		if (id != null) {
			workoutPlanRepository.delete(id);
			LOG.info("Deleted WorkoutPlan with id {}", id);
			return true;
		}
		LOG.warn("Could not delete WorkoutPlan without id");
		return false;
	}

}
