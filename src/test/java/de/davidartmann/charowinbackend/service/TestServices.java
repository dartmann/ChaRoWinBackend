package de.davidartmann.charowinbackend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.davidartmann.charowinbackend.dto.DietplanDto;
import de.davidartmann.charowinbackend.dto.ExerciseDto;
import de.davidartmann.charowinbackend.dto.FoodDto;
import de.davidartmann.charowinbackend.dto.MealDto;
import de.davidartmann.charowinbackend.dto.MuscleDto;
import de.davidartmann.charowinbackend.dto.UserDto;
import de.davidartmann.charowinbackend.dto.WorkoutDto;
import de.davidartmann.charowinbackend.dto.WorkoutPlanDto;
import de.davidartmann.charowinbackend.dto.WorkoutSessionDto;
import de.davidartmann.charowinbackend.model.Dietplan;
import de.davidartmann.charowinbackend.model.Exercise;
import de.davidartmann.charowinbackend.model.Food;
import de.davidartmann.charowinbackend.model.Meal;
import de.davidartmann.charowinbackend.model.Muscle;
import de.davidartmann.charowinbackend.model.User;
import de.davidartmann.charowinbackend.model.Workout;
import de.davidartmann.charowinbackend.model.WorkoutPlan;
import de.davidartmann.charowinbackend.model.WorkoutSession;
import de.davidartmann.charowinbackend.model.constants.ActivityIndex;
import de.davidartmann.charowinbackend.model.constants.Weekday;

@Rollback(value=true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/servlet-test-context.xml")
public class TestServices {

	@Autowired
	private DietplanService dietplanService;
	@Autowired
	private MealService mealService;
	@Autowired
	private ExerciseService exerciseService;
	@Autowired
	private UserService userService;
	@Autowired
	private FoodService foodService;
	@Autowired
	private MuscleService muscleService;
	@Autowired
	private WorkoutService workoutService;
	@Autowired
	private WorkoutSessionService workoutSessionService;
	@Autowired
	private WorkoutPlanService workoutPlanService;
	
	@Test
	public void testDietplanService() {
		DietplanDto dietplanDto = new DietplanDto();
		dietplanDto.setActive(true);
		List<Long> mealIds = new ArrayList<Long>();
		Long randMealId1 = RandomUtils.nextLong(1, 5);
		mealIds.add(randMealId1);
		Long randMealId2 = RandomUtils.nextLong(5, 11);
		mealIds.add(randMealId2);
		dietplanDto.setMealIds(mealIds);
		dietplanDto.setName(RandomStringUtils.randomAlphabetic(10));
		Dietplan dietplan = dietplanService.create(dietplanDto);
		assertNotNull(dietplan);
		UserDto userDto = new UserDto();
		userDto.setActive(true);
		List<Long> dIds = new ArrayList<Long>();
		dIds.add(dietplan.getId());
		userDto.setDietplanIds(dIds);
		User user = userService.create(userDto);
		dietplanDto.setUserId(user.getId());
		dietplan = dietplanService.updateById(dietplan.getId(), dietplanDto);
		assertNotNull(dietplanService.getById(dietplan.getId()));
		Dietplan dietplan2 = dietplanService.create(dietplanDto);
		List<Long> dietplanIds = new ArrayList<Long>();
		dietplanIds.add(dietplan.getId());
		dietplanIds.add(dietplan2.getId());
		List<Dietplan> dietplans = dietplanService.getByIds(dietplanIds);
		assertEquals(dietplanIds.size(), dietplans.size());
		dietplanDto.setName(RandomStringUtils.randomAlphabetic(10));
		Dietplan dietplan3 = 
				dietplanService.updateById(dietplan.getId(), dietplanDto);
		assertNotNull(dietplan3);
		assertEquals(dietplanDto.getActive(), dietplan3.getActive());
		assertEquals(true, dietplanService.deleteById(dietplan.getId()));
		assertEquals(true, 
				dietplanService.deleteByUserId(dietplan.getUser().getId()));
		assertNull(dietplanService.getById(dietplan.getId()));
	}
	
	@Test
	public void testExerciseService() {
		ExerciseDto exerciseDto = new ExerciseDto();
		exerciseDto.setActive(true);
		List<Long> muscleIds = new ArrayList<Long>();
		muscleIds.add(RandomUtils.nextLong(1, 6));
		muscleIds.add(RandomUtils.nextLong(6, 11));
		exerciseDto.setMuscleIds(muscleIds);
		exerciseDto.setName(RandomStringUtils.randomAlphabetic(10));
		//just for testing
		exerciseDto.setWorkoutIds(muscleIds);
		Exercise exercise = exerciseService.create(exerciseDto);
		assertNotNull(exercise);
		assertEquals(exercise.getId(), 
				exerciseService.getById(exercise.getId()).getId());
		Exercise exercise2 = exerciseService.create(exerciseDto);
		List<Long> exerciseIds = new ArrayList<Long>();
		exerciseIds.add(exercise.getId());
		exerciseIds.add(exercise2.getId());
		assertEquals(exerciseIds.size(), 
				exerciseService.getByIds(exerciseIds).size());
		exerciseDto.setActive(false);
		Exercise updatedExercise = 
				exerciseService.updateById(exercise.getId(), exerciseDto);
		assertEquals(exerciseDto.getActive(), 
				exerciseService.getById(updatedExercise.getId()).getActive());
		assertEquals(true, exerciseService.deleteById(updatedExercise.getId()));
		assertNull(exerciseService.getById(updatedExercise.getId()));
	}
	
	@Test
	public void testFoodService() {
		FoodDto foodDto = new FoodDto();
		foodDto.setActive(true);
		foodDto.setName(RandomStringUtils.randomAlphabetic(10));
		Double weight = RandomUtils.nextDouble(50, 500);
		foodDto.setWeight(weight);
		foodDto.setWeightCarbohydrates(weight*0.5);
		foodDto.setWeightFat(weight*0.2);
		foodDto.setWeightProtein(weight*0.3);
		Food food = foodService.create(foodDto);
		assertNotNull(food);
		assertEquals(food, foodService.getById(food.getId()));
		foodDto.setName(RandomStringUtils.randomAlphabetic(10));
		Food food2 = foodService.create(foodDto);
		Food updatedFood = foodService.updateById(food.getId(), foodDto);
		assertNotNull(updatedFood);
		List<Long> foodIds = new ArrayList<Long>();
		foodIds.add(food.getId());
		foodIds.add(food2.getId());
		assertEquals(foodIds.size(), foodService.getByIds(foodIds).size());
		assertEquals(true, foodService.deleteById(food2.getId()));
		assertNull(foodService.getById(food2.getId()));
	}
	
	@Test
	public void testMealService() {
		MealDto mealDto = new MealDto();
		mealDto.setActive(true);
		mealDto.setMealtime(new DateTime().getMillis());
		mealDto.setName(RandomStringUtils.randomAlphabetic(10));
		mealDto.setWeekday(Weekday.FRIDAY);
		Meal meal = mealService.create(mealDto);
		assertNotNull(meal);
		assertNotNull(mealService.getById(meal.getId()));
		mealDto.setWeekday(Weekday.MONDAY);
		Meal meal2 = mealService.create(mealDto);
		List<Long> ids = new ArrayList<Long>();
		ids.add(meal.getId());
		ids.add(meal2.getId());
		assertEquals(ids.size(), mealService.getByIds(ids).size());
		Meal updatedMeal = mealService.updateById(meal.getId(), mealDto);
		assertNotNull(updatedMeal);
		assertEquals(true, mealService.deleteById(meal.getId()));
		assertNull(mealService.getById(meal.getId()));
	}
	
	@Test
	public void testMuscleService() {
		MuscleDto muscleDto = new MuscleDto();
		muscleDto.setActive(true);
		muscleDto.setName(RandomStringUtils.randomAlphabetic(10));
		Muscle muscle = muscleService.create(muscleDto);
		assertNotNull(muscle);
		assertNotNull(muscleService.getById(muscle.getId()));
		Muscle muscle2 = muscleService.create(muscleDto);
		List<Long> ids = new ArrayList<Long>();
		ids.add(muscle.getId());
		ids.add(muscle2.getId());
		assertEquals(ids.size(), muscleService.getByIds(ids).size());
		muscleDto.setActive(false);
		Muscle updatedMuscle = 
				muscleService.updateById(muscle.getId(), muscleDto);
		assertNotNull(updatedMuscle);
		assertEquals(muscleDto.getActive(), updatedMuscle.getActive());
		assertEquals(true, muscleService.deleteById(muscle2.getId()));
		assertNull(muscleService.getById(muscle2.getId()));
	}
	
	@Test
	public void testUserService() {
		UserDto userDto = new UserDto();
		userDto.setActive(true);
		userDto.setActivityIndex(ActivityIndex.EXTREME);
		userDto.setAge(RandomUtils.nextInt(18, 75));
		userDto.setBodyHeight(RandomUtils.nextDouble(150, 200));
		userDto.setBodyWeight(RandomUtils.nextDouble(60000, 110000));
		userDto.setName(RandomStringUtils.randomAlphabetic(10));
		User user = userService.create(userDto);
		assertNotNull(user);
		assertEquals(user, userService.getById(user.getId()));
		userDto.setActive(false);
		User updatedUser = userService.updateById(user.getId(), userDto);
		assertEquals(false, updatedUser.getActive());
		assertEquals(true, userService.deleteById(user.getId()));
		assertNull(userService.getById(user.getId()));
	}
	
	@Test
	public void testWorkoutService() {
		WorkoutDto workoutDto = new WorkoutDto();
		workoutDto.setActive(true);
		workoutDto.setName(RandomStringUtils.randomAlphabetic(10));
		workoutDto.setNumberOfDay(Weekday.MONDAY_NUMERIC);
		workoutDto.setWeekday(Weekday.MONDAY);
		Workout workout = workoutService.create(workoutDto);
		assertNotNull(workout);
		assertEquals(workout, workoutService.getById(workout.getId()));
		Workout workout2 = workoutService.create(workoutDto);
		List<Long> ids = new ArrayList<Long>();
		ids.add(workout.getId());
		ids.add(workout2.getId());
		assertEquals(ids.size(), workoutService.getByIds(ids).size());
		workoutDto.setActive(false);
		Workout workout3 = workoutService.updateById(workout.getId(), workoutDto);
		assertNotNull(workout3);
		assertEquals(workoutDto.getActive(), workout3.getActive());
		assertEquals(true, workoutService.deleteById(workout2.getId()));
		assertNull(workoutService.getById(workout2.getId()));
	}
	
	@Test
	public void testWorkoutSessionService() {
		WorkoutSessionDto sessionDto = new WorkoutSessionDto();
		sessionDto.setActive(true);
		WorkoutSession session = workoutSessionService.create(sessionDto);
		assertNotNull(session);
		assertNotNull(workoutSessionService.getById(session.getId()));
		WorkoutSession session2 = 
				workoutSessionService.create(sessionDto);
		List<Long> ids = new ArrayList<Long>();
		ids.add(session.getId());
		ids.add(session2.getId());
		assertEquals(ids.size(), 
				workoutSessionService.getByIds(ids).size());
		sessionDto.setActive(false);
		WorkoutSession updatedSession = 
				workoutSessionService.updateById(session.getId(), sessionDto);
		assertEquals(sessionDto.getActive(), updatedSession.getActive());
		assertEquals(true, 
				workoutSessionService.deleteById(session2.getId()));
	}
	
	@Test
	public void testWorkoutPlanService() {
		WorkoutPlanDto workoutPlanDto = new WorkoutPlanDto();
		workoutPlanDto.setActive(true);
		workoutPlanDto.setAmountDays(RandomUtils.nextInt(1, 7));
		workoutPlanDto.setCurrent(true);
		workoutPlanDto.setDescription(RandomStringUtils.randomAlphabetic(20));
		workoutPlanDto.setName(RandomStringUtils.randomAlphabetic(10));
		WorkoutPlan workoutPlan = workoutPlanService.create(workoutPlanDto);
		assertNotNull(workoutPlan);
		assertNotNull(workoutPlanService.getById(workoutPlan.getId()));
		WorkoutPlan workoutPlan2 = workoutPlanService.create(workoutPlanDto);
		List<Long> ids = new ArrayList<Long>();
		ids.add(workoutPlan.getId());
		ids.add(workoutPlan2.getId());
		assertEquals(ids.size(), workoutPlanService.getByIds(ids).size());
		workoutPlanDto.setActive(false);
		WorkoutPlan updatedWorkoutPlan = 
				workoutPlanService.updateById(workoutPlan.getId(), workoutPlanDto);
		assertEquals(workoutPlanDto.getActive(), updatedWorkoutPlan.getActive());
		assertTrue(workoutPlanService.deleteById(workoutPlan.getId()));
	}
}
