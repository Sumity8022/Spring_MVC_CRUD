package com.demos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demos.entities.MyUser;

@Repository
public interface MyUserRepo extends JpaRepository<MyUser, Integer> {

}
