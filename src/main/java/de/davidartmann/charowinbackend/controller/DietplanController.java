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

import de.davidartmann.charowinbackend.dto.DietplanDto;
import de.davidartmann.charowinbackend.model.Dietplan;
import de.davidartmann.charowinbackend.service.DietplanService;

@RestController
@RequestMapping(value="/api/dietplan")
public class DietplanController implements IController<DietplanDto, Dietplan> {

	@Autowired
	private DietplanService dietplanService;
	
	@RequestMapping(value="/all", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Dietplan> getAll() {
		return dietplanService.getAll();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Dietplan getById(@PathVariable("id") Long id) {
		return dietplanService.getById(id);
	}

	@RequestMapping(value="/", method=RequestMethod.GET, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Dietplan> getByIds(@RequestBody List<Long> ids) {
		return dietplanService.getByIds(ids);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Boolean deleteById(@PathVariable("id") Long id) {
		return dietplanService.deleteById(id);
	}

	@RequestMapping(value="/", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Dietplan create(@RequestBody DietplanDto dto) {
		return dietplanService.create(dto);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Dietplan updateById(@PathVariable("id") Long id, 
			@RequestBody DietplanDto dto) {
		return dietplanService.updateById(id, dto);
	}

}
