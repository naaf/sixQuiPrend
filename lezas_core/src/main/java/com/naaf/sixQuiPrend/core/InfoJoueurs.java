package com.naaf.sixQuiPrend.core;

import java.io.Serializable;

public class InfoJoueurs implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int score;

	public InfoJoueurs(String name) {
		super();
		this.name = name;
		this.score = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void add(int score2) {
		this.score += score2;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", score=" + score + "]";
	}

}
