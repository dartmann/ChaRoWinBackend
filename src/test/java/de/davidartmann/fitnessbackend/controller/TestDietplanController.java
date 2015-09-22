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

import de.davidartmann.charowinbackend.dto.DietplanDto;
import de.davidartmann.charowinbackend.model.Dietplan;
import de.davidartmann.charowinbackend.service.DietplanService;

@Rollback(value=true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring/servlet-test-context.xml")
public class TestDietplanController {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private DietplanService dietplanService;
	
	private MockMvc mockMvc;
	private String dietplanApi = "/api/dietplan/";
	private ObjectMapper mapper;
	private DietplanDto dto;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(
				com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
		
		dto = new DietplanDto();
		dto.setActive(true);
		dto.setName("test dietplan");
	}
	
	@Test
	public void create() {
		try {
			mockMvc.perform(post(dietplanApi)
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.content(mapper.writeValueAsBytes(dto))
					.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		Dietplan dietplan = dietplanService.create(dto);
		try {
			mockMvc.perform(delete(dietplanApi+dietplan.getId())
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
			mockMvc.perform(get(dietplanApi+"all")
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
		Dietplan dietplan = dietplanService.create(dto);
		try {
			mockMvc.perform(get(dietplanApi+dietplan.getId())
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getByIds() {
		Dietplan dietplan = dietplanService.create(dto);
		dto.setName("test dietplan2");
		Dietplan dietplan2 = dietplanService.create(dto);
		List<Long> dietplanIds = new ArrayList<Long>();
		dietplanIds.add(dietplan.getId());
		dietplanIds.add(dietplan2.getId());
		try {
			mockMvc.perform(get(dietplanApi)
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.content(mapper.writeValueAsBytes(dietplanIds))
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
		Dietplan dietplan = dietplanService.create(dto);
		dto.setActive(false);
		try {
			mockMvc.perform(post(dietplanApi+dietplan.getId())
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.content(mapper.writeValueAsBytes(dto))
					.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
