package model.dao;

import java.util.List;

import model.entities.Match;
import model.entities.Pet;
import model.entities.Preference;

public interface PetDAO extends GenericDAO<Pet, Integer> {
	/* Methods */
	public List<Pet> getPetsByOwnerEmail(String ownerEmail);

	public List<String> getTypes();
	
	public List<Pet> getPetsByPreference(Preference preference);
	
	public List<Pet> getPetsFromConfirmedMatches(Integer id, List<Match> matches);
	
}
