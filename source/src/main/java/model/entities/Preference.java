package model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "preference")
public class Preference implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "minimumage")
	private Integer minimumAge;
	
	@Column(name = "maximumage")
	private Integer maximumAge;
	
	@OneToOne
	@JoinColumn(name = "pet")
	private Pet pet;
	
	public Preference() {
		super();
	}	
	
	public Preference(Integer id, String type, String sex, Integer minimumAge, Integer maximumAge, Pet pet) {
		super();
		this.id = id;
		this.type = type;
		this.sex = sex;
		this.minimumAge = minimumAge;
		this.maximumAge = maximumAge;
		this.pet = pet;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setMinimumAge(Integer minimumAge) {
		this.minimumAge = minimumAge;
	}
	
	public void setMaximumAge(Integer maximumAge) {
		this.maximumAge = maximumAge;
	}
	
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	public Integer getId() {
		return id;
	}	

	public String getType() {
		return type;
	}	
	
	public String getSex() {
		return sex;
	}
	
	public Integer getMinimumAge() {
		return minimumAge;
	}
	
	public Integer getMaximumAge() {
		return maximumAge;
	}
	
	public Pet getPet() {
		return pet;
	}

}
