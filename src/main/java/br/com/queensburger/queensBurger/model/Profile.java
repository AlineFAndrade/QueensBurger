package br.com.queensburger.queensBurger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile {

	@Id
	@Column(name = "id")
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column
	private EProfile name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EProfile getName() {
		return name;
	}

	public void setName(EProfile name) {
		this.name = name;
	}
	
	
}
