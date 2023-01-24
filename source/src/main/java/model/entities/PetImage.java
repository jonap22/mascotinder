package model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity (name = "petimage")
public class PetImage implements Serializable{
	/* Attributes */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Lob
	@Column(name = "base64image")
	private String base64Image;
	
	@JoinColumn(name = "pet")
	@ManyToOne
	private Pet pet;	
	
	/* Constructor */
	public PetImage() {
		super();
	}

	public PetImage(Integer id, String base64Image, Pet pet) {
		super();
		this.id = id;
		this.base64Image = base64Image;
		this.pet = pet;
	}
	
	/* Methods */
	/* Set */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
	public void setPet(Pet pet) {
		this.pet = pet;
	}	
	
	/* Get */
	public Integer getId() {
		return id;
	}	
	
	public String getBase64Image() {
		return base64Image;
	}

	public Pet getPet() {
		return pet;
	}
	
}
