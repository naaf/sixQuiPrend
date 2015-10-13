package com.naaf.sixQuiPrend.exception;

public class SixQuiPrendException extends Exception {
	private static final long serialVersionUID = 1L;
	private int ligne;
	private String msg;

	public SixQuiPrendException(int ligne, String msg) {
		super(msg);
		this.ligne = ligne;
		this.msg = msg;
	}

	public SixQuiPrendException(String msg) {
		super();
		this.msg = msg;
	}

	public SixQuiPrendException(int ligne) {
		this.ligne = ligne;
	}

	@Override
	public String toString() {
		return "SixQuiPrendException [nb=" + ligne + ", msg=" + msg + "]";
	}

}
