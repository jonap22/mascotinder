package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.DAOFactory;
import model.entities.Message;
import model.entities.Owner;

@WebServlet("/ChatController")
public class ChatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChatController() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Get parameters
		String receiverEmail = request.getParameter("receiver_owner_email");
		HttpSession sessionOwner = request.getSession();
		Owner loggedOwner = (Owner) sessionOwner.getAttribute("loggedOwner");
		String senderEmail = loggedOwner.getEmail();
		// 2. Talk with the model
		List<Message> messages = DAOFactory.getFactory().getMessageDAO().getMessagesByOwnersEmails(senderEmail, receiverEmail);
		
		// 3. Send data to the view
		request.setAttribute("sender_owner_email", senderEmail);
		request.setAttribute("receiver_owner_email", receiverEmail);
		request.setAttribute("messages", messages);
		getServletContext().getRequestDispatcher("/jsp/Chat.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Get parameters
		Boolean sendFlag = Boolean.parseBoolean(request.getParameter("send_flag"));
		String content = request.getParameter("content");
		String senderEmail = request.getParameter("sender_email");
		String receiverEmail = request.getParameter("receiver_email");
		
		// 2. Talk with the model
		if (sendFlag) {
			Owner sender = DAOFactory.getFactory().getOwnerDAO().read(senderEmail);
			Owner receiver = DAOFactory.getFactory().getOwnerDAO().read(receiverEmail);
			Message message = new Message(content,sender,receiver);
			DAOFactory.getFactory().getMessageDAO().create(message);
		}
		
		List<Message> messages = DAOFactory.getFactory().getMessageDAO().getMessagesByOwnersEmails(senderEmail, receiverEmail);
		
		// 3. Send data to the view
		request.setAttribute("messages", messages);
		request.setAttribute("sender_owner_email", senderEmail);
		request.setAttribute("receiver_owner_email", receiverEmail);
		getServletContext().getRequestDispatcher("/jsp/Chat.jsp").forward(request, response);
	}
	
	

}
