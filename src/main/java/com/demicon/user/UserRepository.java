package com.demicon.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLocationCountryIgnoreCase(String country);

    List<User> findTopByOrderByIdDesc();

}
