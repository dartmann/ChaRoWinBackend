package de.davidartmann.fitnessbackend.dto;

/**
 * The base class for all data transfer objects.
 * It just holds a {@link #active} attribute, that every dto needs to show soft deletion status.
 * @author David Artmann
 */
public abstract class BaseDto {

	private Boolean active;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
