/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanmay.simple3.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.tanmay.simple3.domain.User;

/**
 *
 * @author didin
 */
public interface UserRepository extends MongoRepository<User, String> {
    
    User findByEmail(String email);
    
}
