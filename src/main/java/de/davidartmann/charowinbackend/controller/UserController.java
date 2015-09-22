package de.davidartmann.charowinbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.davidartmann.charowinbackend.dto.UserDto;
import de.davidartmann.charowinbackend.model.User;
import de.davidartmann.charowinbackend.service.UserService;

@RestController
@RequestMapping(value="/api/user")
public class UserController implements IController<UserDto, User> {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getById(@PathVariable("id") Long userId) {
		return userService.getById(userId);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User create(@RequestBody UserDto userDto) {
		return userService.create(userDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Boolean deleteById(@PathVariable("id") Long userId) {
		return userService.deleteById(userId);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User updateById(@PathVariable("id") Long userId, 
			@RequestBody UserDto userDto) {
		return userService.updateById(userId, userDto);
	}
	
	@RequestMapping(value="/temp", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	public User tempCreateUserForDebugging() {
		UserDto userDto = new UserDto();
		userDto.setActive(true);
		userDto.setActivityIndex(1.5);
		userDto.setAge(25);
		userDto.setBodyHeight(RandomUtils.nextDouble(150.0, 200.0));
		userDto.setBodyWeight(RandomUtils.nextDouble(60.0, 110.0));
		List<Long> dietplanIds = new ArrayList<Long>();
		dietplanIds.add(RandomUtils.nextLong(1, 5));
		dietplanIds.add(RandomUtils.nextLong(6, 10));
		userDto.setDietplanIds(dietplanIds);
		userDto.setName(RandomStringUtils.randomAlphabetic(8));
		List<Long> workoutIds = new ArrayList<Long>();
		workoutIds.add(RandomUtils.nextLong(1, 5));
		workoutIds.add(RandomUtils.nextLong(6, 10));
		userDto.setWorkoutIds(workoutIds);
		return userService.create(userDto);
	}

	@RequestMapping(value="/all", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getAll() {
		return userService.getAll();
	}

	@RequestMapping(value="/", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getByIds(@RequestBody List<Long> ids) {
		return userService.getByIds(ids);
	}
}
