package com.naaf.sixQuiPrend.core;

import java.io.Serializable;

public class Carte implements Comparable<Carte>, Serializable {
	private static final long serialVersionUID = 1L;
	private final int id;
	private final int nbTeteBoeuf;

	public Carte(int id, int nbTeteBoeuf) {
		super();
		this.id = id;
		this.nbTeteBoeuf = nbTeteBoeuf;
	}

	public int getId() {
		return id;
	}

	public int getNbTeteBoeuf() {
		return nbTeteBoeuf;
	}

	@Override
	public String toString() {
		return "[" + id + ", " + nbTeteBoeuf + "]";
	}

	@Override
	public int compareTo(Carte o) {
		if (this == o) {
			return 0;
		}
		if (this.id > o.id) {
			return 1;
		} else if (this.id < o.id) {
			return -1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carte other = (Carte) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
