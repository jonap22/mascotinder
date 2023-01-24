package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DAOFactory;
import model.entities.Match;
import model.entities.Pet;

@WebServlet("/MatchesController")
public class MatchesController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public MatchesController() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Get parameters
		// 2. Talk with the model
		// 3. Send data to the view
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// 1. Get parameters
		Integer requesterId = Integer.parseInt(request.getParameter("requester_id"));
		Integer applicantId = Integer.parseInt(request.getParameter("applicant_id"));
		Boolean isLike = Boolean.parseBoolean(request.getParameter("is_like"));
		
		// 2. Talk with the model
		Pet requester = DAOFactory.getFactory().getPetDAO().read(requesterId);
		Pet applicant = DAOFactory.getFactory().getPetDAO().read(applicantId);
		
		if (isLike) {
			DAOFactory.getFactory().getMatchDAO().processLikeBetweenPets(requester, applicant);
		}
		else {
			DAOFactory.getFactory().getMatchDAO().processDislikeBetweenPets(requester, applicant);
			DAOFactory.getFactory().getOwnerDAO().addRejectedPet(requester.getOwner().getEmail(), applicant);
			DAOFactory.getFactory().getOwnerDAO().addRejectedPet(applicant.getOwner().getEmail(), requester);
		}
		
		// 3. Send data to the view
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Get parameters
		Integer petId = Integer.parseInt(request.getParameter("pet_id"));
		
		// 2. Talk with the model
		Pet requesterPet = DAOFactory.getFactory().getPetDAO().read(petId);
		List<Match> petMatches = DAOFactory.getFactory().getMatchDAO().getMatchesByPetId(petId);
		List<Pet> pets = DAOFactory.getFactory().getPetDAO().getPetsFromConfirmedMatches(petId, petMatches);		
		
		// 3. Send data to the view
		request.setAttribute("requester_pet", requesterPet);
		request.setAttribute("pets", pets);
		
		getServletContext().getRequestDispatcher("/jsp/ListMatches.jsp").forward(request, response);
	}

}
