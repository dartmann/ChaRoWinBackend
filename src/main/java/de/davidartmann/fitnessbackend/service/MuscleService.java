package de.davidartmann.fitnessbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.davidartmann.fitnessbackend.dto.MuscleDto;
import de.davidartmann.fitnessbackend.model.Muscle;
import de.davidartmann.fitnessbackend.repository.MuscleRepository;

@Service
public class MuscleService implements IService<MuscleDto, Muscle> {
	
	private static final Logger LOG = LoggerFactory.getLogger(MuscleService.class);

	@Autowired
	private MuscleRepository muscleRepository;
	@Autowired
	private ExerciseService exerciseService;
	
	/**
	 * Returns {@link Muscle} by given id
	 * @param id
	 * @return Muscle or null
	 */
	public Muscle getById(Long id) {
		Muscle muscle = null;
		if (id != null) {
			muscle = muscleRepository.findOne(id);
		} else {
			LOG.warn("Could not return Muscle without id");
		}
		return muscle;
	}
	
	/**
	 * Method to return a {@link List} of {@link Muscle}s by a given {@link List} of ids
	 * @param ids
	 * @return List of Muscles or empty List
	 */
	public List<Muscle> getByIds(List<Long> ids) {
		List<Muscle> muscles = new ArrayList<Muscle>();
		if (ids != null && !ids.isEmpty()) {
			Iterator<Muscle> muscleIterator = muscleRepository.findAll(ids).iterator();
			while(muscleIterator.hasNext()) {
				muscles.add(muscleIterator.next());
			}
		} else {
			LOG.warn("Could not return Muscles without ids");
		}
		return muscles;
	}
	
	/**
	 * Method to create a {@link Muscle} by a given id
	 * @param muscleDto
	 * @return Muscle or null
	 */
	public Muscle create(MuscleDto muscleDto) {
		Muscle muscle = null;
		if (muscleDto != null) {
			muscle = new Muscle();
			muscle.setActive(true);
			if (muscleDto.getExerciseIds() != null 
					&& !muscleDto.getExerciseIds().isEmpty()) {
				muscle.setExercises(
						exerciseService.getByIds(muscleDto.getExerciseIds()));
			} else {
				LOG.debug("MuscleDto without exerciseIds");
			}
			muscle.setName(muscleDto.getName());
			muscle = muscleRepository.save(muscle);
			LOG.info("Created Muscle with id {}", muscle.getId());
		} else {
			LOG.warn("Could not create Muscle without id");
		}
		return muscle;
	}
	
	/**
	 * Updates {@link Muscle} by given id and {@link MuscleDto}
	 * @param id
	 * @param muscleDto
	 * @return Muscle or null
	 */
	public Muscle updateById(Long id, MuscleDto muscleDto) {
		Muscle muscle = null;
		if (id != null && muscleDto != null) {
			muscle = getById(id);
			if (muscle != null) {
				muscle.setActive(muscleDto.getActive());
				if (muscleDto.getExerciseIds() != null 
						&& !muscleDto.getExerciseIds().isEmpty()) {
					muscle.setExercises(
							exerciseService.getByIds(muscleDto.getExerciseIds()));
				} else {
					LOG.debug("MuscleDto without exerciseIds");
				}
				muscle.setName(muscleDto.getName());
				muscle = muscleRepository.save(muscle);
				LOG.info("Updated Muscle with id {}", id);
			} else {
				LOG.warn("Could not update Muscle with invalid id {}", id);
			}
		} else {
			LOG.warn("Could not update Muscle without id");
		}
		return muscle;
	}
	
	/**
	 * Deletes {@link Muscle} by given id
	 * @param id
	 * @return true if Muscle could be deleted or false otherwise
	 */
	public Boolean deleteById(Long id) {
		if (id != null) {
			muscleRepository.delete(id);
			LOG.info("Deleted Muscle with id {}", id);
			return true;
		}
		LOG.warn("Could not delete Muscle without id");
		return false;
	}
	
	/**
	 * Method to return all {@link Muscle}s
	 * @return List of Muscles or empty list
	 */
	public List<Muscle> getAll() {
		return muscleRepository.findAll();
	}
}
