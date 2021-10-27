
package com.tanmay.simple3.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.tanmay.simple3.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
    
    User findByEmail(String email);
    
}
