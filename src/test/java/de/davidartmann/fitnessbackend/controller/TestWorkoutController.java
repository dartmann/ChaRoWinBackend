package de.davidartmann.fitnessbackend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.davidartmann.fitnessbackend.dto.WorkoutDto;
import de.davidartmann.fitnessbackend.model.Workout;
import de.davidartmann.fitnessbackend.model.constants.Weekday;
import de.davidartmann.fitnessbackend.service.WorkoutService;

@Rollback(value=true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring/servlet-test-context.xml")
public class TestWorkoutController {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private WorkoutService workoutService;
	
	private MockMvc mockMvc;
	private WorkoutDto dto;
	private String workoutApi = "/api/workout/";
	private ObjectMapper mapper;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(
				com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
		
		dto = new WorkoutDto();
		dto.setActive(true);
		dto.setName("test workout");
		dto.setNumberOfDay(Weekday.FRIDAY_NUMERIC);
		dto.setWeekday(Weekday.FRIDAY);
	}
	
	@Test
	public void create() {
		try {
			mockMvc.perform(post(workoutApi)
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.content(mapper.writeValueAsBytes(dto))
					.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		Workout workout = workoutService.create(dto);
		try {
			mockMvc.perform(delete(workoutApi+workout.getId())
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(content().string("true"))
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAll() {
		try {
			mockMvc.perform(get(workoutApi+"all")
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getById() {
		Workout workout = workoutService.create(dto);
		try {
			mockMvc.perform(get(workoutApi+workout.getId())
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getByIds() {
		Workout workout = workoutService.create(dto);
		dto.setActive(false);
		Workout workout2 = workoutService.create(dto);
		List<Long> ids = new ArrayList<Long>();
		ids.add(workout.getId());
		ids.add(workout2.getId());
		try {
			mockMvc.perform(get(workoutApi)
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.content(mapper.writeValueAsBytes(ids))
					.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateById() {
		Workout workout = workoutService.create(dto);
		dto.setActive(false);
		try {
			mockMvc.perform(post(workoutApi+workout.getId())
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.content(mapper.writeValueAsBytes(dto))
					.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
