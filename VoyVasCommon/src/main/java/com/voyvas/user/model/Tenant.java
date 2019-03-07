package com.voyvas.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tenant", uniqueConstraints = @UniqueConstraint(columnNames = { "short_name" }))
public class Tenant {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "short_name")
	private String shortName;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	public Tenant(String shortName, String name, String description) {
		super();
		this.shortName = shortName;
		this.name = name;
		this.description = description;
	}

}
