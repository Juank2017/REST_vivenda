package com.melilla.REST_vivienda.service.impl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.melilla.REST_vivienda.repository.UserRepository;
import com.melilla.REST_vivienda.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository userRepository;
 
	
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
        	
            @Override
            public UserDetails loadUserByUsername(String username) {
               return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
           
            }
        };
    }
}
