package com.voyvas.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sec_authority")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;
}
