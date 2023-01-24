package model.dao;

import model.entities.Owner;
import model.entities.Pet;

public interface OwnerDAO extends GenericDAO<Owner, String> {
	/* Methods */
	public Owner authorizeOwner(String email, String password);
	
	public void addRejectedPet(String ownerEmail, Pet rejectedPet);

}
