package model.jpa;

import java.util.List;

import javax.persistence.Query;

import model.dao.MessageDAO;
import model.entities.Message;

public class JPAMessageDAO extends JPAGenericDAO<Message, Integer> implements MessageDAO{

	public JPAMessageDAO() {
		super(Message.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getMessagesByOwnersEmails(String ownerEmailA, String ownerEmailB) {
		List<Message> messages = null;
		
		String sentence = "SELECT m FROM message m WHERE (m.sender.email= :emailA AND m.receiver.email= :emailB) OR (m.sender.email= :emailB AND m.receiver.email= :emailA)";
		Query query = this.entityManager.createQuery(sentence);
		query.setParameter("emailA", ownerEmailA);
		query.setParameter("emailB", ownerEmailB);
		
		try {
			messages = (List<Message>) query.getResultList();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return messages;
	}
	
}
