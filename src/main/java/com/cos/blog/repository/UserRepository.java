package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.cos.blog.model.User;


public interface UserRepository  extends JpaRepository<User, Integer>{

}
