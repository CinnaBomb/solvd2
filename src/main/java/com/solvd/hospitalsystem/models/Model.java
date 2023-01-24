package com.solvd.hospitalsystem.models;

abstract public class Model {

	private long id;
	
	public Model() {}

	public Model(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

}
