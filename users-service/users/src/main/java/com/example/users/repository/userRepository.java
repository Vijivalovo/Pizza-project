package com.example.users.repository;

import com.example.users.models.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<users, Integer>
{

    users findByPhoneNumber(String number);

    users findByName(String name);
    
}
