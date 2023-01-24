package controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DAOFactory;
import model.entities.Owner;
import model.entities.Pet;

@WebServlet("/RegisterController")

public class RegisterController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Get parameters
		// 2. Talk with the model
		// 3. Send data to the view
		List<String> types = DAOFactory.getFactory().getPetDAO().getTypes();
		request.setAttribute("types", types);
		getServletContext().getRequestDispatcher("/jsp/Register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. Get parameters
		String name = request.getParameter("pet_name");
		String type = request.getParameter("pet_type");
		String sex = request.getParameter("pet_sex");
		int age = Integer.parseInt(request.getParameter("pet_age"));
		Owner sessionOwner = (Owner) request.getSession().getAttribute("loggedOwner");
		String image1 = request.getParameter("pet_image_1");
		String image2 = request.getParameter("pet_image_2");
		String image3 = request.getParameter("pet_image_3");
		Pet newPet = new Pet(0, name, type, sex, age, sessionOwner);
		// 2. Talk with the model
		DAOFactory.getFactory().getPetDAO().create(newPet);
		DAOFactory.getFactory().getPreferenceDAO().setPreferenceNewPet(newPet);
		DAOFactory.getFactory().getPetImageDAO().createNewPetImages(image1, image2, image3, newPet);
		// 3. Send data to the view
		response.sendRedirect(request.getContextPath() + "/ListPetsController");
	}

}
