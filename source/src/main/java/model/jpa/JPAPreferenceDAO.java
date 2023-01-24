package model.jpa;

import javax.persistence.Query;

import model.entities.Pet;
import model.entities.Preference;
import model.dao.PreferenceDAO;

public class JPAPreferenceDAO extends JPAGenericDAO<Preference, Integer> implements PreferenceDAO {

	public JPAPreferenceDAO() {
		super(Preference.class);
	}

	@Override
	public Preference getPreferenceByPetId(Integer id) {
		Preference preference = null;

		String sentence = "SELECT p FROM preference p WHERE p.pet.id= :pet_id";
		Query query = this.entityManager.createQuery(sentence);
		query.setParameter("pet_id", id);

		try {
			preference = (Preference) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return preference;
	}

	@Override
	public void setPreferenceByPetId(Integer id, Preference preference) {
		Preference oldPreference = getPreferenceByPetId(id);
		delete(oldPreference);
		create(preference);
	}

	@Override
	public void setPreferenceNewPet(Pet newPet) {
		// Pet newPet = new Pet(0, name, type, sex, age, sessionOwner);
		// public Preference(Integer id, String type, String sex, Integer minimumAge,
		// Integer maximumAge, Pet pet) {
		Preference preference = new Preference(null, newPet.getType(), changeSex(newPet.getSex()), 1, 20, newPet);
		newPet.setPreference(preference);
		create(preference);
	}

	private String changeSex(String sex) {
		if (sex.equalsIgnoreCase("male")) {
			return "female";
		} 
			
		return "male";
	}

}
