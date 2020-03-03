package com.wanchopi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Team entity
 * @author Wanchopi
 *
 */
@Entity
@Table(name = "TEAMS")
@NoArgsConstructor
@AllArgsConstructor
public class Team {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter @Getter
	private long id;
	
	@Column(name = "NAME")
	@Setter @Getter
	private String name;
	
	@Column(name = "CITY")
	@Setter @Getter
	private String city;
	
	@OneToMany(mappedBy = "team")
	@Setter @Getter
	private List<Player> players = new ArrayList<Player>();
	
	public Team(String name, String city) {
		this.name = name;
		this.city = city;
	}

}
