package model.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import model.dao.PetDAO;
import model.entities.Match;
import model.entities.Pet;
import model.entities.Preference;

public class JPAPetDAO extends JPAGenericDAO<Pet, Integer> implements PetDAO {
	/* Constructor */
	public JPAPetDAO() {
		super(Pet.class);
	}
	
	/* Methods */
	@SuppressWarnings("unchecked")
	@Override
	public List<Pet> getPetsByOwnerEmail(String ownerEmail) {
		List<Pet> pets = null;
		
		String sentence = "SELECT p FROM pet p WHERE p.owner.email= :email";
		Query query = this.entityManager.createQuery(sentence);
		query.setParameter("email", ownerEmail);
		
		try {
			pets = (List<Pet>) query.getResultList();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return pets;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTypes() {
		List<String> types = null;
		
		String sentence = "SELECT DISTINCT p.type FROM pet p";
		Query query = this.entityManager.createQuery(sentence);
		
		try {
			types = (List<String>) query.getResultList();			
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		
		return types;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pet> getPetsByPreference(Preference preference) {
		List<Pet> pets = null;
		
		String sentence = "SELECT * FROM pet p WHERE p.owner<> '" + preference.getPet().getOwner().getEmail() +
				"' AND p.type= '"+ preference.getType() + "' AND p.sex= '" + preference.getSex() + 
				"' AND p.age BETWEEN " + preference.getMinimumAge() + " AND " + preference.getMaximumAge() + " AND p.id NOT IN " + 
				"(SELECT oe.rejectedPets_ID FROM owner_pet oe WHERE oe.rejectedOwners_EMAIL= '" + preference.getPet().getOwner().getEmail() + "')" + 
				
				" AND p.id NOT IN (SELECT pm.applicant FROM petmatch pm WHERE pm.requester= " + preference.getPet().getId() + 
				" UNION (SELECT pm.requester FROM petmatch pm WHERE (pm.requester= " + preference.getPet().getId() + 
				" OR pm.applicant= " + preference.getPet().getId() + ") AND pm.requester<> " + preference.getPet().getId() + 
				" AND pm.confirmation= 1" + 
				" UNION SELECT pm.applicant FROM petmatch pm WHERE (pm.requester= " + preference.getPet().getId() + 
				" OR pm.applicant= " + preference.getPet().getId() + ") AND pm.applicant<> " + preference.getPet().getId() + 
				" AND pm.confirmation= 1))";
		
		Query query = this.entityManager.createNativeQuery(sentence, Pet.class);
		
		try {
			pets = (List<Pet>) query.getResultList();			
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		
		return pets;
	}

	@Override
	public List<Pet> getPetsFromConfirmedMatches(Integer id, List<Match> matches) {
		List<Pet> pets = new ArrayList<>();
		List<Integer> petsIds = new ArrayList<>();
		
		for (Match match : matches) {
			if (match.getRequester().getId() == id) {
				petsIds.add(match.getApplicant().getId());
			}
			else {
				petsIds.add(match.getRequester().getId());
			}
		}
		
		for (Integer petId : petsIds) {
			String sentence = "SELECT p FROM pet p WHERE p.id= :pet_id";
			Query query = this.entityManager.createQuery(sentence);
			query.setParameter("pet_id", petId);
			
			try {
				pets.add((Pet) query.getSingleResult());			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return pets;
	}


}
