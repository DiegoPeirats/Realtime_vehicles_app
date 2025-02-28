package com.realtime_vehicles.users.application.response;

public class UserDTO {
	
	private Long id;
	private String name;
	private String email;
	
	public UserDTO(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public UserDTO(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public UserDTO() {
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
		return "UserDTO [id=" + id + ", nombre=" + name + ", email=" + email + "]";
	}
}
