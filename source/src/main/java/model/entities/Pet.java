package model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity (name = "pet")
public class Pet implements Serializable {
	/* Attributes */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "age")
	private int age;
	
	@JoinColumn(name = "owner")
	@ManyToOne
	private Owner owner;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pet")
	private List<PetImage> images;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "pet")
	private Preference preference;	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "requester")
	private List<Match> matches;
	
	//@JoinColumn(name = "rejectedOwner")
	//@ManyToMany
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rejectedPets")
	private List<Owner> rejectedOwners;
	
	/* Constructor */
	public Pet() {
		super();
	}

	public Pet(Integer id, String name, String type, String sex, int age, Owner owner) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.sex = sex;
		this.age = age;
		this.owner = owner;
	}

	/* Methods */
	public List<PetImage> getImages() {
		return images;
	}
	
	public Preference getPreference() {
		return preference;
	}

	public List<Match> getMatches() {
		return matches;
	}	
	
	/* Set */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public void setImages(List<PetImage> images) {
		this.images = images;
	}
	
	public void setPreference(Preference preference) {
		this.preference = preference;
	}
	
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public void setRejectedOwners(List<Owner> rejectedOwners) {
		this.rejectedOwners = rejectedOwners;
	}

	/* Get */
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}	
	
	public String getSex() {
		return sex;
	}	
	
	public int getAge() {
		return age;
	}
	
	public Owner getOwner() {
		return owner;
	}
	
	public List<Owner> getRejectedOwners() {
		return rejectedOwners;
	}
}
