package test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import javax.imageio.ImageIO;
import model.dao.DAOFactory;
import model.entities.Match;
import model.entities.Message;
import model.entities.Owner;
import model.entities.Pet;
import model.entities.PetImage;
import model.entities.Preference;

public class JPATableCreation {

	public static void main(String[] args) {

		ArrayList<ArrayList<File>> allFiles = new ArrayList<ArrayList<File>>();

		File directory = new File("img/benjamin 4");
		allFiles.add(getFiles(directory));

		directory = new File("img/cheems 8");
		allFiles.add(getFiles(directory));

		directory = new File("img/pugzilla 6");
		allFiles.add(getFiles(directory));

		directory = new File("img/milanesa 2");
		allFiles.add(getFiles(directory));

		directory = new File("img/zoe 6");
		allFiles.add(getFiles(directory));
		
		directory = new File("img/Simurdiera 4");
		allFiles.add(getFiles(directory));
		
		directory = new File("img/Chester 3");
		allFiles.add(getFiles(directory));
		
		directory = new File("img/Zeus 4");
		allFiles.add(getFiles(directory));
		
		directory = new File("img/Negra 11");
		allFiles.add(getFiles(directory));
		
		directory = new File("img/Zanahoria 1");
		allFiles.add(getFiles(directory));
		
		directory = new File("img/CamelCase");
		allFiles.add(getFiles(directory));
		
		ArrayList<Owner> owners = new ArrayList<>();

		// owners.add(new Owner("", "", ""));
		owners.add(new Owner("francisco.encalada01@epn.edu.ec", "Francisco", "rafa123"));
		owners.add(new Owner("jonathan.puglla@epn.edu.ec", "Jonathan", "jonathan123"));
		owners.add(new Owner("andres.lozano@epn.edu.ec", "Andrés", "andres123"));
		owners.add(new Owner("pollito@gmail.com", "Gaby", "gaby123"));
		owners.add(new Owner("carlosiniguez@epn.edu.ec", "Carlos", "carlos123"));

		for (Owner owner : owners) {
			DAOFactory.getFactory().getOwnerDAO().create(owner);
		}

		ArrayList<Pet> pets = new ArrayList<>();

		// PETS
		// pets.add(new Pet(null, "", "dog", "", , owners.get()));
		pets.add(new Pet(null, "Benjamin", "dog", "male", 4, owners.get(4)));
		pets.add(new Pet(null, "Cheems", "dog", "male", 8, owners.get(2)));
		pets.add(new Pet(null, "Ojitos", "dog", "female", 6, owners.get(0)));
		pets.add(new Pet(null, "Milanesa", "dog", "female", 2, owners.get(3)));
		pets.add(new Pet(null, "Zoe", "dog", "female", 2, owners.get(3)));
		pets.add(new Pet(null, "Simurdiera", "dog", "male", 4, owners.get(2)));
		pets.add(new Pet(null, "Chester", "dog", "male", 3, owners.get(1)));
		pets.add(new Pet(null, "Zeus", "dog", "male", 4, owners.get(1)));
		pets.add(new Pet(null, "Negra", "dog", "Female", 11, owners.get(2)));
		
		// Auto setting the preferences
		for (Pet pet : pets) {
			DAOFactory.getFactory().getPetDAO().create(pet);
			Preference preference = new Preference(null, "dog", changeSex(pet.getSex()), 1, 20, pet);
			DAOFactory.getFactory().getPreferenceDAO().create(preference);
		}
		
		pets.add(new Pet(null, "Zanahoria", "rabbit", "Female", 1, owners.get(4)));
		DAOFactory.getFactory().getPetDAO().create(pets.get(9));
		Preference preference = new Preference(null, "rabbit", "Male", 1, 20, pets.get(9));
		DAOFactory.getFactory().getPreferenceDAO().create(preference);
		
		pets.add(new Pet(null, "CamelCase", "camel", "Male", 3, owners.get(4)));
		DAOFactory.getFactory().getPetDAO().create(pets.get(10));
		preference = new Preference(null, "camel", "Female", 1, 20, pets.get(10));
		DAOFactory.getFactory().getPreferenceDAO().create(preference);

		// Adding the images

		int i = 0;
		for (ArrayList<File> arrayList : allFiles) {
			for (File file : arrayList) {
				PetImage image = new PetImage(null, fileToBase64String(file), pets.get(i));
				DAOFactory.getFactory().getPetImageDAO().create(image);
			}
			i++;
		}
		
		Match match = new Match(true, pets.get(2), pets.get(0));
		DAOFactory.getFactory().getMatchDAO().create(match);
		
		Message message1 = new Message("Hi", owners.get(0), owners.get(4));
		Message message2 = new Message("Hi :D", owners.get(4), owners.get(0));
		DAOFactory.getFactory().getMessageDAO().create(message1);
		DAOFactory.getFactory().getMessageDAO().create(message2);		

	}

	public static String fileToBase64String(File file) {

		BufferedImage bi;
		try {
			bi = ImageIO.read(file);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", output);
			String imageAsBase64 = Base64.getEncoder().encodeToString(output.toByteArray());
			return imageAsBase64;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public static ArrayList<File> getFiles(final File directory) {
		ArrayList<File> files = new ArrayList<>();
		for (final File fileEntry : directory.listFiles()) {
			files.add(fileEntry);
		}
		return files;
	}

	public static String changeSex(String sex) {
		if (sex == "male") {
			return "female";
		} else {
			return "male";
		}
	}

}
