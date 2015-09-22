package de.davidartmann.fitnessbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.davidartmann.fitnessbackend.dto.DietplanDto;
import de.davidartmann.fitnessbackend.model.Dietplan;
import de.davidartmann.fitnessbackend.model.User;
import de.davidartmann.fitnessbackend.repository.DietplanRepository;

@Service
public class DietplanService implements IService<DietplanDto, Dietplan> {

	private static final Logger LOG = LoggerFactory.getLogger(DietplanService.class);
	
	@Autowired
	private DietplanRepository dietplanRepository;
	@Autowired
	private MealService mealService;
	@Autowired
	private UserService userService;
	
	/**
	 * Returns {@link Dietplan} by given id
	 * @param id
	 * @return Dietplan or null
	 */
	public Dietplan getById(Long id) {
		Dietplan dietplan = null;
		if (id != null) {
			dietplan = dietplanRepository.findOne(id);
		} else {
			LOG.warn("Could not return Dietplan without id");
		}
		return dietplan;
	}
	
	/**
	 * Method to return all {@link Dietplan}s by the given {@link List} of ids.
	 * @param ids
	 * @return {@link List} of {@link Dietplan} or empty {@link List}.
	 */
	public List<Dietplan> getByIds(List<Long> ids) {
		List<Dietplan> dietplans = new ArrayList<Dietplan>();
		if (ids != null && !ids.isEmpty()) {
			Iterator<Dietplan> dietplanIterator = dietplanRepository.findAll(ids).iterator();
			while(dietplanIterator.hasNext()) {
				dietplans.add(dietplanIterator.next());
			}
		} else {
			LOG.warn("Could not return Dietplans without ids");
		}
		return dietplans;
	}
	
	/**
	 * Creates {@link Dietplan} by given {@link DietplanDto}
	 * @param dto
	 * @return Created Dietplan or null
	 */
	public Dietplan create(DietplanDto dto) {
		Dietplan dietplan = null;
		if (dto != null) {
			dietplan = new Dietplan();
			dietplan.setActive(dto.getActive());
			if (dto.getMealIds() != null 
					&& !dto.getMealIds().isEmpty()) {
				dietplan.setMeals(mealService.getByIds(dto.getMealIds()));
			} else {
				LOG.debug("DietplanDto without mealIds");
			}
			dietplan.setName(dto.getName());
			if (dto.getUserId() != null) {
				User user = userService.getById(dto.getUserId());
				if (user != null) {
					dietplan.setUser(user);
				} else {
					LOG.debug("DietplanDto with invalid userId");
				}
			} else {
				LOG.debug("DietplanDto without userId");
			}
			dietplan = dietplanRepository.save(dietplan);
			LOG.info("Created Dietplan with id {}", dietplan.getId());
		} else {
			LOG.warn("Could not create Dietplan without DietplanDto");
		}
		return dietplan;
	}
	
	/**
	 * Updates {@link Dietplan} by given id and {@link DietplanDto}
	 * @param dietplanId
	 * @param dietplanDto
	 * @return Dietplan or null
	 */
	public Dietplan updateById(Long dietplanId, DietplanDto dietplanDto) {
		Dietplan dietplan = null;
		if (dietplanId != null && dietplanDto != null) {
			dietplan = getById(dietplanId);
			if (dietplan != null) {
				dietplan.setActive(dietplanDto.getActive());
				if (dietplanDto.getMealIds() != null && !dietplanDto.getMealIds().isEmpty()) {
					dietplan.setMeals(mealService.getByIds(dietplanDto.getMealIds()));
				} else {
					LOG.debug("DietplanDto without mealIds");
				}
				dietplan.setName(dietplanDto.getName());
				if (dietplanDto.getUserId() != null) {
					User user = userService.getById(dietplanDto.getUserId());
					if (user != null) {
						dietplan.setUser(user);
					} else {
						LOG.debug("DietplanDto with invalid userId");
					}
				} else {
					LOG.debug("DietplanDto without userId");
				}
				dietplan = dietplanRepository.save(dietplan);
				LOG.info("Updated Dietplan with id {}", dietplan.getId());
			} else {
				LOG.warn("Could not update Dietplan with invalid id {}", dietplanId);
			}
		} else {
			LOG.warn("Could not update Dietplan without parameters");
		}
		return dietplan;
	}
	
	/**
	 * Deletes {@link Dietplan} by given id
	 * @param id
	 * @return true if Dietplan could be deleted or false otherwise
	 */
	public Boolean deleteById(Long id) {
		if (id != null) {
			dietplanRepository.delete(id);
			LOG.info("Deleted Dietplan with id {}", id);
			return true;
		}
		LOG.warn("Could not delete Dietplan without id");
		return false;
	}
	
	/**
	 * Deletes {@link Dietplan}s by given userId
	 * @param userId
	 * @return true if Dietplans could be deleted or false otherwise
	 */
	public Boolean deleteByUserId(Long userId) {
		if (userId != null) {
			User user = userService.getById(userId);
			if (user != null) {
				Integer count = 0;
				List<Dietplan> dietplans = dietplanRepository.findByUser(user);
				for(Dietplan d : dietplans) {
					dietplanRepository.delete(d);
					count++;
				}
				if (count.equals(dietplans.size())) {
					LOG.info("Deleted {} Dietplan(s) by userId {}", count, userId);
				} else {
					LOG.debug("Deleted only {} Dietplan(s) "
							+ "although there were {} ids to delete", count, dietplans.size());
				}				
				return true;
			} else {
				LOG.warn("Could not delete Dietplan with invalid userId");
			}
		} else {
			LOG.warn("Could not delete Dietplan without userId");
		}
		return false;
	}

	/**
	 * Method to get all {@link Dietplan}s
	 * @return List of Dietplans or empty list
	 */
	public List<Dietplan> getAll() {
		return dietplanRepository.findAll();
	}
}
