package model.jpa;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.Query;

import model.dao.PetImageDAO;
import model.entities.Pet;
import model.entities.PetImage;

public class JPAPetImageDAO extends JPAGenericDAO<PetImage, Integer> implements PetImageDAO {
	/* Constructor */
	public JPAPetImageDAO() {
		super(PetImage.class);
	}

	/* Methods */
	@Override
	public PetImage getFirstPetImage(Integer petId) {
		PetImage image = null;

		String sentenceJPQL = "SELECT i FROM pet_image i WHERE i.pet.id= :pet_id AND i.pet.petImage.id= :image_id";
		Query query = entityManager.createQuery(sentenceJPQL);
		query.setParameter("pet_id", petId);
		query.setParameter("image_id", 1);

		try {
			image = (PetImage) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return image;
	}

	@Override
	public void createNewPetImages(String image1, String image2, String image3, Pet newPet) {
		/*
		 * Method that adds images to directory "new_img_pet", creates a new directory
		 * with image1, image2, image3
		 */
		String path = "C:\\Users\\Confectus\\git\\mascotinder\\source\\img_new_pets\\";
		String pathImage1 = path + image1;
		String pathImage2 = path + image2;
		String pathImage3 = path + image3;

		String[] imagesPath = { pathImage1, pathImage2, pathImage3 };

		/* Get files of "pathsImages" */
		List<File> images = getImages(imagesPath);
		/* Create images of the pet */
		for (int i = 0; i < images.size(); i++) {
			PetImage image = new PetImage(null, fileToBase64String(images.get(i)), newPet);
			create(image);
		}
	}
	
	@Override
	public List<File> getImages(String[] imagesPath) {
		ArrayList<File> images = new ArrayList<File>();
		
		for (int i = 0; i < imagesPath.length; i++) {
			File image = new File(imagesPath[i]);
			
			if (image.exists()) {
				images.add(image);
			} else {
				System.out.println("The image doesn't exist");
			}
		}
		return images;
	}
	
	@Override
	public String fileToBase64String(File file) {

		BufferedImage bufferedImage;
		
		try {
			bufferedImage = ImageIO.read(file);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", output);
			String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
			
			return imageAsBase64;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}

}
