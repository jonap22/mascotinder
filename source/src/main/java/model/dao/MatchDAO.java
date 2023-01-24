package model.dao;

import java.util.List;

import model.entities.Match;
import model.entities.Pet;

public interface MatchDAO extends GenericDAO<Match, Integer> {
	
	public List<Match> getMatchesByPetId(Integer id);
	
	public Match getMatchBetweenPets(Integer requesterId, Integer applicantId);
	
	public void processLikeBetweenPets(Pet requester, Pet applicant);
	
	public void processDislikeBetweenPets(Pet requester, Pet applicant);

}
