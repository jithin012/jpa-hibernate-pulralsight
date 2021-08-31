package com.pluralsight.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.conference.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
