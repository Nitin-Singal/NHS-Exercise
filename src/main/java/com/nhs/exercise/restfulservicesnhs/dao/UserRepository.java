package com.nhs.exercise.restfulservicesnhs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhs.exercise.restfulservicesnhs.model.UserBean;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Long> {

}
