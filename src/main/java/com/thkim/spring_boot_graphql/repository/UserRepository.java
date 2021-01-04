package com.thkim.spring_boot_graphql.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thkim.spring_boot_graphql.datas.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Page<User> findAll(Pageable pageabl);
}