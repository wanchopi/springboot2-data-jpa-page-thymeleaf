package com.wanchopi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

/**
 * Player entity
 * @author Wanchopi
 *
 */
@Entity
@Table(name = "PLAYERS")
@NoArgsConstructor
@AllArgsConstructor
public @Data class Player {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NAME")	
	@NotNull
    @Size(min=4, max=24)
	private String name;
	
	@Column(name = "EMAIL")
	@NotNull
	@Email
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TEAM")
	@NotNull
	private Team team;

}
