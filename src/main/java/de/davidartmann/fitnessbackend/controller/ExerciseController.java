package de.davidartmann.fitnessbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.davidartmann.fitnessbackend.dto.ExerciseDto;
import de.davidartmann.fitnessbackend.model.Exercise;
import de.davidartmann.fitnessbackend.service.ExerciseService;

@RestController
@RequestMapping(value="/api/exercise")
public class ExerciseController implements IController<ExerciseDto, Exercise> {

	@Autowired
	private ExerciseService exerciseService;
	
	@RequestMapping(value="/", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Exercise create(@RequestBody ExerciseDto exerciseDto) {
		return exerciseService.create(exerciseDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean deleteById(@PathVariable("id") Long id) {
		return exerciseService.deleteById(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Exercise getById(@PathVariable("id") Long id) {
		return exerciseService.getById(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Exercise> getByIds(@RequestBody List<Long> exerciseIds) {
		return exerciseService.getByIds(exerciseIds);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Exercise> getAll() {
		return exerciseService.getAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Exercise updateById(@PathVariable("id") Long id, 
			@RequestBody ExerciseDto exerciseDto) {
		return exerciseService.updateById(id, exerciseDto);
	}
}
