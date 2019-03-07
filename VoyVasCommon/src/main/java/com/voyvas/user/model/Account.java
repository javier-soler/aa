package com.voyvas.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = { "tenant_id", "username" }))
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tenant_id")
	private Long tenantId;
	
	@Column(name="profile_id")
	private Long profileId;

	@Column(name = "username")
	private String userName;

	@JsonIgnore
	@Column(name = "password")
	private String password;

	@Transient
	private String plainTextPassword;

	@Column(name = "name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "age")
	private Integer age;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profile_id", insertable=false, updatable=false)
	private Profile profile;

	@ManyToOne
	@JoinColumn(name = "tenant_id", updatable = false, insertable = false)
	private Tenant tenant;

	@LastModifiedBy
	@Column(name = "modified_by")
	private String modifiedBy;

	@CreatedDate
	@Column(name = "created_at")
	private Date createdAt;

	@LastModifiedDate
	@Column(name = "modified_at")
	private Date modifiedAt;

	public Account(Long tenantId, String userName, String password, String firstName, String lastName, String email,
			Integer age, Profile profile) {
		super();
		this.tenantId = tenantId;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.profile = profile;
	}

}
