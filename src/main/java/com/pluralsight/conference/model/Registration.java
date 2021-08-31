package com.pluralsight.conference.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "REGISTRATION")
@NamedQueries({
        @NamedQuery(name = Registration.REGISTRATION_REPORT,query = Registration.REGISTRATION_REPORT_JPQL)
}
)
public class Registration {
	/*
	 * Projection: -> A way to present objects to the UI -> Objects added using JPQL
	 * 
	 * 
	 * For Named query
	 */
	public static final String REGISTRATION_REPORT = "Registration.registrationReport";

	// Table join Registration & Course
    public static final String REGISTRATION_REPORT_JPQL =
            "Select new com.pluralsight.conference.model.RegistrationReport" +
            "(r.name, c.name, c.description) " +
            "from Registration r, Course c " +
            "where r.id = c.registration.id";


	/**
	 * IDENTITY - postgres will generate ID
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

	/**
	 * Table Schema: registration_id will be a foreign key on the course table
	 * 
	 * Fetch : at what time we want to fetch the data 1. LAZY : DB query when
	 * property is called. 2. EAGER: DB query when object is created. In this
	 * context, It will wait until we call getCourses on our registration object
	 * before it queries the DB.
	 * 
	 */
    @JsonManagedReference
    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Registration [id=" + id + ", name=" + name + ", courses=" + courses + "]";
	}

}
