package model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name = "message")
public class Message implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "content")
	private String content;
	
	@JoinColumn(name = "sender")
	@ManyToOne
	private Owner sender;
	
	@JoinColumn(name = "receiver")
	@ManyToOne
	private Owner receiver;
	
	public Message() {
		super();
	}

	public Message(String content, Owner sender, Owner receiver) {
		super();
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Owner getSender() {
		return sender;
	}

	public void setSender(Owner sender) {
		this.sender = sender;
	}

	public Owner getReceiver() {
		return receiver;
	}

	public void setReceiver(Owner receiver) {
		this.receiver = receiver;
	}
	
}
