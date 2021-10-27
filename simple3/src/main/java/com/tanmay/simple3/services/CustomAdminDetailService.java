/*package com.tanmay.simple3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.tanmay.simple3.domain.Admin;
import com.tanmay.simple3.repositories.AdminRepository;


@Service
public class CustomAdminDetailService implements UserDetailsService {


    @Autowired
    private AdminRepository adminRepository;

    public void saveAdmin(Admin user) {
        //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  
          adminRepository.save(user);
      }
}
*/