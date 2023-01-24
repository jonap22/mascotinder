package model.dao;

import model.jpa.JPADAOFactory;

public abstract class DAOFactory {
	/* Attributes */
	protected static DAOFactory factory = new JPADAOFactory();
	
	/* Methods */
	public static DAOFactory getFactory () {
		return factory;
	}
	
	public abstract PetDAO getPetDAO();

	public abstract OwnerDAO getOwnerDAO();
	
	public abstract PetImageDAO getPetImageDAO();
	
	public abstract PreferenceDAO getPreferenceDAO();
	
	public abstract MatchDAO getMatchDAO();
	
	public abstract MessageDAO getMessageDAO();
	
}
