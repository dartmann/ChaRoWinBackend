package de.davidartmann.charowinbackend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * The base class for all models, with relevant information for every model class.
 * It holds an {@link Id} and {@link GeneratedValue} annotated {@link #id}, 
 * the {@link Version} annotated {@link #version} and an {@link #active} attribute.
 * @author David Artmann
 */
@MappedSuperclass
public class BaseModel implements Serializable {
	
	private static final long serialVersionUID = -5463251175289790917L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable=false, nullable=false)
	private Long id;
	
	@Version
	@Column(nullable=false)
	private Long version;
	
	//TODO: do we need attributes to check models for their creation time or last update time?
	
	@Column(nullable=false)
	private Boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
