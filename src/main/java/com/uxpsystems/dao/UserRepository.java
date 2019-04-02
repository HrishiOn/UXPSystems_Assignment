package com.uxpsystems.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uxpsystems.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{}
