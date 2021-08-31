package com.pluralsight.conference.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 4 join types 1. @OnetoOne 2. @OneoMany 3. @ManyToOne 4. @ManyToMany
 *
 * Various Configuration 1. Unidirectional 2. Bidirectional 3. Cascade
 * 
 *
 */

@Entity
@Table(name = "COURSE")
public class Course {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @JsonBackReference
    @ManyToOne
    private Registration registration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", registration=" + registration
				+ "]";
	}
}
