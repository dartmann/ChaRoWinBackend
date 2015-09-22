package de.davidartmann.charowinbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.davidartmann.charowinbackend.dto.WorkoutDto;
import de.davidartmann.charowinbackend.model.Workout;
import de.davidartmann.charowinbackend.service.WorkoutService;

@RestController
@RequestMapping(value="/api/workout")
public class WorkoutController implements IController<WorkoutDto, Workout> {

	@Autowired
	private WorkoutService workoutService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Workout getById(@PathVariable("id") Long id) {
		return workoutService.getById(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Workout create(@RequestBody WorkoutDto workoutDto) {
		return workoutService.create(workoutDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean deleteById(@PathVariable("id") Long id) {
		return workoutService.deleteById(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Workout updateById(@PathVariable("id") Long id, 
			@RequestBody WorkoutDto workoutDto) {
		return workoutService.updateById(id, workoutDto);
	}

	@RequestMapping(value="/all", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Workout> getAll() {
		return workoutService.getAll();
	}

	@RequestMapping(value="/", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Workout> getByIds(@RequestBody List<Long> ids) {
		return workoutService.getByIds(ids);
	}
}
