package de.davidartmann.fitnessbackend.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.davidartmann.fitnessbackend.dto.UserDto;
import de.davidartmann.fitnessbackend.model.User;
import de.davidartmann.fitnessbackend.repository.UserRepository;

@Service
public class UserService implements IService<UserDto, User> {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DietplanService dietplanService;
	@Autowired
	private WorkoutService workoutService;
	
	/**
	 * Method to create a new {@link User} by a given {@link UserDto}.
	 * @param userDto
	 * @return the created {@link User} or null
	 */
	public User create(UserDto userDto) {
		User user = null;
		if (userDto != null) {
			user = new User();
			user.setActive(userDto.getActive());
			user.setActivityIndex(userDto.getActivityIndex());
			user.setAge(userDto.getAge());
			user.setBodyHeight(userDto.getBodyHeight());
			user.setBodyWeight(userDto.getBodyWeight());
			if (userDto.getDietplanIds() != null &&
					!userDto.getDietplanIds().isEmpty()) {
				user.setDietplans(dietplanService.getByIds(userDto.getDietplanIds()));
			} else {
				LOG.debug("UserDto without dietplanIds");
			}
			user.setName(userDto.getName());
			if (userDto.getWorkoutIds() != null &&
					!userDto.getWorkoutIds().isEmpty()) {
				user.setWorkouts(workoutService.getByIds(userDto.getWorkoutIds()));
			} else {
				LOG.debug("UserDto without workoutIds");
			}
			user = userRepository.save(user);
			LOG.info("Created User with id {}", user.getId());
		} else {
			LOG.warn("Could not create User without UserDto");
		}
		return user;
	}
	
	/**
	 * Method to get a {@link User} by a given id.
	 * @param userId
	 * @return User or null
	 */
	public User getById(Long userId) {
		User user = null;
		if (userId != null) {
			user = userRepository.findOne(userId);
		} else {
			LOG.warn("Could not return User without id");
		}
		return user;
	}
	
	/**
	 * Method to delete a {@link User} by a given id.
	 * @param userId
	 * @return <code>true</code> if user could be deleted or <code>false</code> otherwise
	 */
	public Boolean deleteById(Long userId) {
		if (userId != null) {
			User user = userRepository.findOne(userId);
			if (user != null) {
				userRepository.delete(user);
				LOG.info("Deleted User with id {}", user.getId());
				return true;
			} else {
				LOG.warn("Could not delete User with invalid id");
			}
		} else {
			LOG.warn("Could not delete User without id");
		}
		return false;
	}
	
	/**
	 * Method to update a {@link User} by a given id and {@link UserDto}.
	 * @param userId
	 * @param userDto
	 * @return the updated {@link User} or null
	 */
	public User updateById(Long userId, UserDto userDto) {
		User user = null;
		if (userId != null && userDto != null) {
			user = userRepository.findOne(userId);
			if (user != null) {
				user.setActive(userDto.getActive());
				user.setActivityIndex(userDto.getActivityIndex());
				user.setAge(userDto.getAge());
				user.setBodyHeight(userDto.getBodyHeight());
				user.setBodyWeight(userDto.getBodyWeight());
				if (userDto.getDietplanIds() != null &&
						!userDto.getDietplanIds().isEmpty()) {
					user.setDietplans(dietplanService.getByIds(userDto.getDietplanIds()));
				} else {
					LOG.debug("UserDto without dietplanIds");
				}
				user.setName(userDto.getName());
				if (userDto.getWorkoutIds() != null &&
						!userDto.getWorkoutIds().isEmpty()) {
					user.setWorkouts(workoutService.getByIds(userDto.getWorkoutIds()));
				} else {
					LOG.debug("UserDto without workoutIds");
				}
				user = userRepository.save(user);
				LOG.info("Updated User with id {}", user.getId());
			} else {
				LOG.warn("Could not update User with invalid userId");
			}
		} else {
			LOG.warn("Could not update User without parameters");
		}
		return user;
	}

	/**
	 * Method to get all {@link User}s
	 * @return List of Users or empty list
	 */
	public List<User> getAll() {
		return userRepository.findAll();
	}

	/**
	 * Method to get all {@link User}s by a given {@link List} of ids
	 * @param ids
	 * @return List of Users or empty list
	 */
	public List<User> getByIds(List<Long> ids) {
		List<User> users = new ArrayList<User>();
		if (ids != null && !ids.isEmpty()) {
			Iterator<User> userIterator = userRepository.findAll(ids).iterator();
			while(userIterator.hasNext()) {
				users.add(userIterator.next());
			}
		} else {
			LOG.warn("Could not return Users without ids");
		}
		return users;
	}
}
