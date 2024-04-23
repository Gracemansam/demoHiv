package com.hiv.demohiv.repository;

import com.hiv.demohiv.model.Users;
import org.apache.el.lang.ELArithmetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO app_users (name, email, password) VALUES (:name, :email, :password)", nativeQuery = true)
    void customSave(@Param("name") String name, @Param("email") String email, @Param("password") String password);

}
