package de.davidartmann.charowinbackend.dto;

import java.util.List;

import de.davidartmann.charowinbackend.model.Muscle;

/**
 * Data transfer object class for the {@link Muscle} model class.
 * @author David Artmann
 */
public class MuscleDto extends BaseDto {

	private String name;
	
	private List<Long> exerciseIds;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getExerciseIds() {
		return exerciseIds;
	}

	public void setExerciseIds(List<Long> exerciseIds) {
		this.exerciseIds = exerciseIds;
	}
}
