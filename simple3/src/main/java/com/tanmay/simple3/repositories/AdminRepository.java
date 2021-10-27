package com.tanmay.simple3.repositories;




import org.springframework.data.mongodb.repository.MongoRepository;

import com.tanmay.simple3.domain.Admin;



public interface AdminRepository extends MongoRepository<Admin, String> {
    
   Admin findByEmail(String admin);
    
}