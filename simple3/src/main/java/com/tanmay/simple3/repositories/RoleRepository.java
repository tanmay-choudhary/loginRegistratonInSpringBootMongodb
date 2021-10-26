
package com.tanmay.simple3.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.tanmay.simple3.domain.Role;


public interface RoleRepository extends MongoRepository<Role, String> {
    
    Role findByRole(String role);
    
}
