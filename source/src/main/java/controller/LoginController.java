package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.DAOFactory;
import model.entities.Owner;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	/* Attributes */
	private static final long serialVersionUID = 1L;
	
	/* Constructor */
    public LoginController() {

    }
    
    /* Methods */
    
    /* doGet method */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Get parameters
		// 2. Talk with the model
		// 3. Send data to the view
		response.sendRedirect("jsp/Login.jsp");
	}

    /* doPost method */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Owner authorizedOwner = null;
		
		// 1. Get parameters
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		
		// 2. Talk with the model
		authorizedOwner = DAOFactory.getFactory().getOwnerDAO().authorizeOwner(email, password);
		
		// 3. Send data to the view
		if (authorizedOwner != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loggedOwner", authorizedOwner);
			
			request.getRequestDispatcher("/ListPetsController").forward(request, response);
		}
		else {
			response.sendRedirect("jsp/Login.jsp");
		}
	}

}
