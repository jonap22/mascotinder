package model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name = "petmatch")
public class Match implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "confirmation")
	private Boolean confirmation;
	
	@JoinColumn(name = "requester")
	@ManyToOne
	private Pet requester;
	
	@JoinColumn(name = "applicant")
	@ManyToOne
	private Pet applicant;
	
	public Match() {
		super();
	}
	
	public Match(Boolean confirmation, Pet requester, Pet applicant) {
		super();
		this.confirmation = confirmation;
		this.requester = requester;
		this.applicant = applicant;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}	
	
	public void setRequester(Pet requester) {
		this.requester = requester;
	}	

	public void setApplicant(Pet applicant) {
		this.applicant = applicant;
	}
	
	public void setConfirmation(Boolean confirmation) {
		this.confirmation = confirmation;
	}

	public Integer getId() {
		return id;
	}
	
	public Boolean getConfirmation() {
		return confirmation;
	}
	
	public Pet getRequester() {
		return requester;
	}
	
	public Pet getApplicant() {
		return applicant;
	}
	
}
