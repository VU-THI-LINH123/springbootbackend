package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Khachhang;
@Repository
public interface KhachhangRepository extends   JpaRepository<Khachhang, Long>{

}
