package com.example.users.repository;

import com.example.users.models.tokens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface tokenRepository extends JpaRepository<tokens, Integer>
{

    tokens findTokenByUserId(int id);
    
}
