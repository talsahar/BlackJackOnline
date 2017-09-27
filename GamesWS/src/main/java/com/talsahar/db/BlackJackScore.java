package com.talsahar.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "BlackJackScore")
public class BlackJackScore {
	@Id
	@Column(name = "Username")
	private String username;
	@Column(name = "Score")
	private double score;

	public BlackJackScore() {
	}

	public BlackJackScore(String username, double score) {
		super();
		this.username = username;
		this.score = score;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getScore() {
		return score;
	}

	public void setScore() {

		this.score = score;
	}

}
