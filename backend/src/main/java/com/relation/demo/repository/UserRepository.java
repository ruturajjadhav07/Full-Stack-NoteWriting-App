package com.relation.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.relation.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
