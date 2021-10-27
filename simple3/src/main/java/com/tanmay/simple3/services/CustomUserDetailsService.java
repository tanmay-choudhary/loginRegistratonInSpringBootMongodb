
package com.tanmay.simple3.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tanmay.simple3.domain.User;

import com.tanmay.simple3.domain.Admin;
import com.tanmay.simple3.repositories.AdminRepository;
import com.tanmay.simple3.repositories.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AdminRepository adminRepository;

    public void saveAdmin(Admin admin) {
        //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  
          adminRepository.save(admin);
      }
    public Admin findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public void saveUser(User user) {
      //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setEnabled(true);
        userRepository.save(user);
    }
    
    
	/*
	 * public List<User> getAllStudents() { return UserRepository.findAll(); }
	 */
    
    public void updateUser(User user) {
        //  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
          userRepository.save(user);
      }
    
    public Void deleteUser (String id) 
    {
    	userRepository.deleteById(id);
	    return null;
    }

    
    
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
   
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

   @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getAdmin());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

   private List<GrantedAuthority> getUserAuthority(Set<com.tanmay.simple3.domain.Admin> set) {
        Set<GrantedAuthority> roles = new HashSet<>();
       /* set.forEach((role) -> {
            admins.add(new SimpleGrantedAuthority(admins.getAdmin()));
        });*/

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

}
