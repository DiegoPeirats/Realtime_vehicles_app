package com.realtime_vehicles.gateway.infrastructure.client.user;

public class User {
	
	private Long id;
	private String name;
	private String email;
	
	public User(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nombre=" + name + ", email=" + email + "]";
	}

}
