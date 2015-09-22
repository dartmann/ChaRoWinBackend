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

import de.davidartmann.charowinbackend.dto.UserDto;
import de.davidartmann.charowinbackend.model.User;
import de.davidartmann.charowinbackend.model.constants.ActivityIndex;
import de.davidartmann.charowinbackend.service.UserService;

@Rollback(value=true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:spring/servlet-test-context.xml")
public class TestUserController {

	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private UserService userService;
	
	private MockMvc mockMvc;
	private UserDto dto;
	private String userApi = "/api/user";
	private ObjectMapper mapper;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		mapper = new ObjectMapper();
		mapper.setSerializationInclusion(
				com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
		
		dto = new UserDto();
		dto.setActive(true);
		dto.setActivityIndex(ActivityIndex.EXTREME);
		dto.setAge(25);
		dto.setBodyHeight(185.0);
		dto.setBodyWeight(90.0);
		dto.setName("testuser");
	}
	
	@Test
	public void getAll() {
		try {
			mockMvc.perform(get(userApi+"/all")
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void create() {
		try {
			mockMvc.perform(post(userApi+"/")
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.content(mapper.writeValueAsBytes(dto))
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					)
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteById() {
		User user = userService.create(dto);
		try {
			mockMvc.perform(delete(userApi+"/"+user.getId())
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
	public void getById() {
		User user = userService.create(dto);
		try {
			mockMvc.perform(get(userApi+"/"+user.getId())
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
					//TODO: implement jsonpath expression
//					.andExpect(jsonPath("$.active", is))
					.andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getByIds() {
		User user = userService.create(dto);
		User user2 = userService.create(dto);
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(user.getId());
		userIds.add(user2.getId());
		try {
			mockMvc.perform(get(userApi+"/")
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.content(mapper.writeValueAsBytes(userIds))
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
		User user = userService.create(dto);
		dto.setActive(false);
		try {
			mockMvc.perform(post(userApi+"/"+user.getId())
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
