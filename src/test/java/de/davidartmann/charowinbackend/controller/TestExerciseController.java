package de.davidartmann.charowinbackend.controller;

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

import de.davidartmann.charowinbackend.dto.ExerciseDto;
import de.davidartmann.charowinbackend.model.Exercise;
import de.davidartmann.charowinbackend.service.ExerciseService;

@Rollback(value=true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring/servlet-test-context.xml")
public class TestExerciseController {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private ExerciseService exerciseService;
	
	private MockMvc mockMvc;
	private ExerciseDto dto;
	private String exerciseApi = "/api/exercise/";
	private ObjectMapper mapper;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(
				com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
		
		dto = new ExerciseDto();
		dto.setActive(true);
		dto.setName("test exercise");
	}
	
	@Test
	public void create() {
		try {
			mockMvc.perform(post(exerciseApi)
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
		Exercise exercise = exerciseService.create(dto);
		try {
			mockMvc.perform(delete(exerciseApi+exercise.getId())
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE))
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
			mockMvc.perform(get(exerciseApi+"all")
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
		Exercise exercise = exerciseService.create(dto);
		try {
			mockMvc.perform(get(exerciseApi+exercise.getId())
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
		Exercise exercise = exerciseService.create(dto);
		dto.setName("test exercise 2");
		Exercise exercise2 = exerciseService.create(dto);
		List<Long> ids = new ArrayList<Long>();
		ids.add(exercise.getId());
		ids.add(exercise2.getId());
		try {
			mockMvc.perform(get(exerciseApi)
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
		Exercise exercise = exerciseService.create(dto);
		dto.setActive(false);
		try {
			mockMvc.perform(post(exerciseApi+exercise.getId())
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
