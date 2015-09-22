package de.davidartmann.fitnessbackend.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.davidartmann.fitnessbackend.model.Dietplan;
import de.davidartmann.fitnessbackend.model.Exercise;
import de.davidartmann.fitnessbackend.model.Food;
import de.davidartmann.fitnessbackend.model.Meal;
import de.davidartmann.fitnessbackend.model.Muscle;
import de.davidartmann.fitnessbackend.model.User;
import de.davidartmann.fitnessbackend.model.Workout;
import de.davidartmann.fitnessbackend.model.WorkoutSession;
import de.davidartmann.fitnessbackend.model.constants.ActivityIndex;
import de.davidartmann.fitnessbackend.model.constants.Weekday;

@Rollback(value=true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/servlet-test-context.xml")
public class TestRepositories {

	@Autowired
	private DietplanRepository dietplanRepository;
	@Autowired
	private ExerciseRepository exerciseRepository;
	@Autowired
	private FoodRepository foodRepository;
	@Autowired
	private MealRepository mealRepository;
	@Autowired
	private MuscleRepository muscleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private WorkoutRepository workoutRepository;
	@Autowired
	private WorkoutSessionRepository workoutSessionRepository;
	
	private String name;
	
	@Before
	public void preTesting() {
		name = "test";
	}
	
	@Test
	public void testDietplanRepository() {
		Dietplan dietplan = new Dietplan();
		dietplan.setName(name);
		dietplan.setActive(true);
		dietplan = dietplanRepository.save(dietplan);
		assertNotNull(dietplanRepository.findOne(dietplan.getId()));
		assertEquals(true, dietplanRepository.findByName(name).size() > 0);
		Meal meal = new Meal();
		meal.setActive(true);
		meal = mealRepository.save(meal);
		List<Meal> meals = new ArrayList<Meal>();
		meals.add(meal);
		dietplan.setMeals(meals);
		List<Dietplan> dietplans = new ArrayList<Dietplan>();
		dietplans.add(dietplan);
		meal.setDietplans(dietplans);
		assertEquals(true, dietplanRepository.findByMeals(meal).size() > 0);
		User user = new User();
		user.setActive(true);
		user = userRepository.save(user);
		user.setDietplans(dietplans);
		dietplan.setUser(user);
		assertEquals(true, dietplanRepository.findByUser(user).size() > 0);
	}
	
	@Test
	public void testExerciseRepository() {
		Workout workout = new Workout();
		workout.setActive(true);
		workout.setName(name);
		Exercise exercise = new Exercise();
		exercise.setActive(true);
		exercise.setName(name);
		List<Workout> workouts = new ArrayList<Workout>();
		workouts.add(workout);
		exercise.setWorkouts(workouts);
		Muscle muscle = new Muscle();
		muscle.setActive(true);
		muscle.setName(name);
		List<Exercise> exercises = new ArrayList<Exercise>();
		exercises.add(exercise);
		muscle.setExercises(exercises);
		workout.setExercises(exercises);
		List<Muscle> muscles = new ArrayList<Muscle>();
		muscles.add(muscle);
		exercise.setMuscles(muscles);
		workout = workoutRepository.save(workout);
		exercise = exerciseRepository.save(exercise);
		muscle = muscleRepository.save(muscle);
		assertNotNull(exerciseRepository.findAll());
		assertNotNull(exerciseRepository.findOne(exercise.getId()));
		assertNotNull(exerciseRepository.findByMuscles(muscle));
		assertEquals(true, exerciseRepository.findByMuscles(muscle).size() > 0);
		for(Exercise e : exerciseRepository.findByMuscles(muscle)) {
			assertEquals(exercise.getId(), e.getId());
		}
		assertNotNull(exerciseRepository.findByWorkouts(workout));
		assertEquals(true, exerciseRepository.findByWorkouts(workout).size() > 0);
		for(Exercise e : exerciseRepository.findByWorkouts(workout)) {
			assertEquals(exercise.getId(), e.getId());
		}
		exerciseRepository.delete(exercise);
		assertNull(exerciseRepository.findOne(exercise.getId()));
		workoutRepository.delete(workout);
		assertNull(workoutRepository.findOne(workout.getId()));
	}
	
	@Test
	public void testFoodRepository() {
		Double foodWeight = 100D;
		Food food = new Food();
		food.setActive(true);
		food.setName(name);
		food.setWeight(foodWeight);
		Meal meal = new Meal();
		meal.setActive(true);
		List<Food> foods = new ArrayList<Food>();
		foods.add(food);
		meal.setFoods(foods);
		meal = mealRepository.save(meal);
		food = foodRepository.save(food);
		assertEquals(true, foodRepository.findByName(name).size() > 0);
		assertEquals(true, foodRepository.findByWeight(foodWeight).size() > 0);
		assertEquals(true, foodRepository.findByMeals(meal).size() > 0);
	}
	
	@Test
	public void testMealRepository() {
		Meal meal = new Meal();
		meal.setActive(true);
		meal.setWeekday(Weekday.MONDAY);
		meal = mealRepository.save(meal);
		assertEquals(true, mealRepository.findByWeekday(Weekday.MONDAY).size() > 0);
		Long mealTime = new Date().getTime();
		meal.setMealtime(mealTime);
		assertEquals(true, mealRepository.findByMealtime(mealTime).size() > 0);
		meal.setName(name);
		assertEquals(true, mealRepository.findByName(name).size() > 0);
		Dietplan dietplan = new Dietplan();
		dietplan.setActive(true);
		List<Meal> meals = new ArrayList<Meal>();
		meals.add(meal);
		dietplan = dietplanRepository.save(dietplan);
		dietplan.setMeals(meals);
		List<Dietplan> dietplans = new ArrayList<Dietplan>();
		dietplans.add(dietplan);
		meal.setDietplans(dietplans);
		assertEquals(true, mealRepository.findByDietplans(dietplan).size() > 0);
		Food food = new Food();
		food.setActive(true);
		food.setMeals(meals);
		food = foodRepository.save(food);
		List<Food> foods = new ArrayList<Food>();
		foods.add(food);
		meal.setFoods(foods);
		assertEquals(true, mealRepository.findByFoods(food).size() > 0);
	}
	
	@Test
	public void testMuscleRepository() {
		Workout workout = new Workout();
		workout.setActive(true);
		workout.setName(name);
		workout = workoutRepository.save(workout);
		Exercise exercise = new Exercise();
		exercise.setActive(true);
		exercise.setName(name);
		//we need to do it this way, because Arrays.asList() returns an unmodifiable list
		List<Workout> workouts = new ArrayList<Workout>();
		workouts.add(workout);
		exercise.setWorkouts(workouts);
		Muscle muscle = new Muscle();
		muscle.setActive(true);
		muscle.setName(name);
		List<Exercise> exercises = new ArrayList<Exercise>();
		exercises.add(exercise);
		muscle.setExercises(exercises);
		muscle = muscleRepository.save(muscle);
		List<Muscle> muscles = new ArrayList<Muscle>();
		muscles.add(muscle);
		exercise.setMuscles(muscles);
		exercise = exerciseRepository.save(exercise);
		assertNotNull(muscleRepository.findOne(muscle.getId()));
		assertNotNull(muscleRepository.findByExercises(exercise));
		assertEquals(true, muscleRepository.findByExercises(exercise).size() > 0);
		//first remove the relations then save it to the db
		muscle.getExercises().remove(exercise);
		exercise.getMuscles().remove(muscle);
		muscle = muscleRepository.save(muscle);
		exercise = exerciseRepository.save(exercise);
		//then we can delete the objects
		muscleRepository.delete(muscle);
		assertNull(muscleRepository.findOne(muscle.getId()));
		exerciseRepository.delete(exercise);
		assertNull(exerciseRepository.findOne(exercise.getId()));
		workoutRepository.delete(workout);
		assertNull(workoutRepository.findOne(workout.getId()));
	}
	
	@Test
	public void testUserRepository() {
		assertNotNull(userRepository.findAll());
		User user = new User();
		user.setActive(true);
		user.setName(name);
		user = userRepository.save(user);
		assertNotNull(userRepository.findOne(user.getId()));
		assertEquals(true, userRepository.findByName(name).size() > 0);
		userRepository.delete(user);
		assertNull(userRepository.findOne(user.getId()));
	}
	
	@Test
	public void testWorkoutRepository() {
		assertNotNull(workoutRepository.findAll());
		User user = new User();
		user.setActive(true);
		user.setActivityIndex(ActivityIndex.MIDDLE);
		user.setAge(25);
		user.setBodyHeight(185D);
		user.setBodyWeight(90D);
		user.setName(name);
		WorkoutSession workoutSession = new WorkoutSession();
		workoutSession.setActive(true);
		Long date = new Date().getTime();
		workoutSession.setBeginOfWorkout(date);
		workoutSession.setEndOfWorkout(date+1);
		Workout workout = new Workout();
		workoutSession.setWorkout(workout);
		workout.setActive(true);
		workout.setName(name);
		workout.setNumberOfDay(Weekday.MONDAY_NUMERIC);
		workout.setWeekday(Weekday.MONDAY);
		workout.setUser(user);
		Exercise exercise = new Exercise();
		exercise.setActive(true);
		exercise.setName(name);
		List<Workout> workouts = new ArrayList<Workout>();
		workouts.add(workout);
		exercise.setWorkouts(workouts);
		user.setWorkouts(workouts);
		List<Exercise> exercises = new ArrayList<Exercise>();
		exercises.add(exercise);
		workout.setExercises(exercises);
		workout = workoutRepository.save(workout);
		exercise = exerciseRepository.save(exercise);
		workoutSession = workoutSessionRepository.save(workoutSession);
		user = userRepository.save(user);
		assertNotNull(workoutRepository.findOne(workout.getId()));
		assertNotNull(workoutRepository.findByName(name));
		assertEquals(true, workoutRepository.findByName(name).size() > 0);
		assertNotNull(workoutRepository.findByWeekday(Weekday.MONDAY));
		assertEquals(true, workoutRepository.findByWeekday(Weekday.MONDAY).size() > 0);
		assertNotNull(workoutRepository.findByNumberOfDay(Weekday.MONDAY_NUMERIC));
		assertEquals(true, workoutRepository.findByNumberOfDay(Weekday.MONDAY_NUMERIC).size() > 0);
		assertNotNull(workoutRepository.findByExercises(exercise));
		assertEquals(true, workoutRepository.findByExercises(exercise).size() > 0);
		assertNotNull(workoutRepository.findByWorkoutSessions(workoutSession));
		assertEquals(workout.getId(), workoutRepository.findByWorkoutSessions(workoutSession).getId());
		assertNotNull(workoutRepository.findByUser(user));
		assertEquals(true, workoutRepository.findByUser(user).size() > 0);
		workoutRepository.delete(workout);
		assertNull(workoutRepository.findOne(workout.getId()));
	}

	@Test
	public void testWorkoutSessionRepository() {
		assertNotNull(workoutSessionRepository.findAll());
		WorkoutSession workoutSession = new WorkoutSession();
		workoutSession.setActive(true);
		Long dateLong = new Date().getTime();
		workoutSession.setBeginOfWorkout(dateLong);
		workoutSession.setEndOfWorkout(dateLong);
		Workout workout = new Workout();
		workout.setActive(true);
		workoutSession.setWorkout(workout);
		workoutSession = workoutSessionRepository.save(workoutSession);
		workout = workoutRepository.save(workout);
		assertEquals(true, workoutSessionRepository.findByWorkout(workout).size() > 0);
		assertNotNull(workoutSessionRepository.findOne(workoutSession.getId()));
		assertEquals(true, workoutSessionRepository
					.findByBeginOfWorkoutGreaterThanEqual(dateLong).size() > 0);
		assertEquals(true, workoutSessionRepository
					.findByEndOfWorkoutLessThanEqual(dateLong).size() > 0);
		workoutSessionRepository.delete(workoutSession);
		assertNull(workoutSessionRepository.findOne(workoutSession.getId()));
	}
}
