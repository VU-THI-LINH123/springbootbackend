package com.example.demo.rest;
 
import java.util.List;
 
import javax.transaction.Transactional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
 
@Service
@Transactional
public class UserServices {
     
    @Autowired
    private UserRepository repo;
     
    public List<User> listAll() {
        return repo.findAll(Sort.by("email").ascending());
    }
     
}