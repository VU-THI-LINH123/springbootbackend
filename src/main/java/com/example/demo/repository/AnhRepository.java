package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Anh;

@Repository
public interface AnhRepository extends   JpaRepository<Anh, Long>{

}
