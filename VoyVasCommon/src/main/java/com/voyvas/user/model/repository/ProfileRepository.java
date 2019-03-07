package com.voyvas.user.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voyvas.user.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
