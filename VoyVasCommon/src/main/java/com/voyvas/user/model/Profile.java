package com.voyvas.user.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sec_profile")
@Data
@NoArgsConstructor
public class Profile {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "profile_id")
    private List<Account> accounts;
    
	@ManyToOne
	@JoinColumn(name = "tenant_id", updatable=false, insertable = false)
	private Tenant tenant;

    @ManyToMany
    @JoinTable(name = "sec_profile_to_authority")
    private List<Authority> authorities;
    
    

}
