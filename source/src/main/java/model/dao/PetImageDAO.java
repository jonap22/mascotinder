package model.dao;

import java.io.File;
import java.util.List;

import model.entities.Pet;
import model.entities.PetImage;

public interface PetImageDAO extends GenericDAO<PetImage, Integer> {
	/* Methods */
	public PetImage getFirstPetImage(Integer petId);

	public void createNewPetImages(String image1, String image2, String image3, Pet newPet);
	
	public List<File> getImages(String[] imagesPath);
	
	public String fileToBase64String(File file);
}
